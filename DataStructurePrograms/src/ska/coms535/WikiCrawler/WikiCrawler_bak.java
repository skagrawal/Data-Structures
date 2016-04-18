package ska.coms535.WikiCrawler;

/**
 * @author Shubham and Swatie
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class WikiCrawler_bak {

	static final String BASE_URL = "https://en.wikipedia.org";
	private String seedUrl;
	private String fileName;
	private String[] keywords;
	private int max, count;
	private ArrayList<String> visited;
	private ArrayList<String> queue;
	private HashMap<String,StringBuffer> pageContentMap;
	private int requestCount;
	private ArrayList<String> checkUrlIndex;
	private ArrayList<Boolean> checkUrlBoolean;
	private ArrayList<String> edges,fullPgIdx;
	private ArrayList<String> disAllowedLinks;
	private HashMap<String,StringBuffer> rawPagesMap;
	private ArrayList<StringBuffer> fullPgContent;
	
	WikiCrawler_bak(String seed, String[] topics, int max, String fileName){
		this.count = 0;
		this.seedUrl = seed;
		this.keywords = topics;
		this.max = max;
		this.fileName = fileName;
		this.visited = new ArrayList<String>();
		this.pageContentMap = new HashMap<String, StringBuffer>();
		this.checkUrlBoolean = new ArrayList<Boolean>();
		this.checkUrlIndex = new ArrayList<String>();
		this.queue = new ArrayList<String>();
		this.edges = new ArrayList<String>();
		this.disAllowedLinks = readRobotTextFile();
		this.rawPagesMap = new HashMap<String, StringBuffer>();
		this.fullPgIdx = new ArrayList<String>();
		this.fullPgContent = new ArrayList<StringBuffer>();
	}


	public void crawl() throws IOException, InterruptedException{
		//BFS
		queue.add(seedUrl);
		visited.add(seedUrl);
		count++;
		while(queue.size()>0){
			String cur = queue.remove(0);
			StringBuffer curPageContent = getFullPage(cur);
			if(curPageContent == null){
				continue;
			}
			//pageContentMap.put(cur,curPageContent);
			fullPgIdx.add(cur);
			fullPgContent.add(curPageContent);
			String[] out = outLink(curPageContent);
			System.out.println("Going in i for loop :"+out.length);
			for(int i=0; i<out.length; i++){
				
				boolean foundInPage = keyValueCheckInPage(out[i],keywords);

				if(!checkUrlIndex.contains(out[i])){
					checkUrlIndex.add(out[i]);
					checkUrlBoolean.add(foundInPage);
				}
				if(!visited.contains(out[i]) && foundInPage){
					queue.add(out[i]);
					visited.add(out[i]);
					count++;
				}
				if(count==max){
					break;
				}
			}
			if(count==max){
				break;
			}
			//queue.remove(0);
		}


		int counter = 0;
		for(int j=0; j<visited.size(); j++){
			System.out.println("Inside j for loop-----------------: "+j);
			if(counter == max){
				break;
			}
			StringBuffer sb;
			String[] link;
			System.out.println("(visited.get(j) = "+visited.get(j));
//			if(pageContentMap.keySet().contains(visited.get(j))){
//				System.out.println("Inside if");
//				sb = pageContentMap.get(visited.get(j));
//			}else{
//				sb = getRawPage(visited.get(j));
//			}
			
			if(fullPgIdx.contains(visited.get(j))){
				sb = fullPgContent.get(fullPgIdx.indexOf(visited.get(j)));
			}else{
				sb = getFullPage(visited.get(j));
			}
			
			
			link = outLink(sb);
			//			System.out.println(link);
			for(int i=0; i<link.length;i++){
				if(visited.contains(link[i])){
					if(checkUrlIndex.contains(link[i])){
						int index = checkUrlIndex.indexOf(link[i]);
						if(checkUrlBoolean.get(index) && visited.contains(link[i]) && !edges.contains(visited.get(j) + " " + link[i])){
							counter++;
							edges.add(visited.get(j) + " " + link[i]);
						}
					}else{
						if(keyValueCheckInPage(link[i], keywords) && visited.contains(link[i])&&!edges.contains(visited.get(j) + " " + link[i])){
							checkUrlIndex.add(link[i]);
							checkUrlBoolean.add(true);
							counter++;
							edges.add(visited.get(j) + " " + link[i]);
						}else{
							checkUrlIndex.add(link[i]);
							checkUrlBoolean.add(false);
						}
					}
				}	
				if(counter == max){
					break;
				}
			}
		}
		//output file
		fileWrite(edges.toArray(new String[edges.size()]));
		System.out.println(edges.size()+" "  +requestCount);	
	}


	//Return a list of all out going links of a particular page
	private String[] outLink(StringBuffer bf){
		String fullText = bf.toString().toLowerCase();
		int length = fullText.indexOf("<p>") + "<p>".length();
		fullText = fullText.substring(length);
		ArrayList<String> link = new ArrayList<String>();
		while(true){
			int beginIdx = fullText.indexOf("/wiki/");
			int endIdx = fullText.indexOf("\"", beginIdx);
			if(fullText.indexOf("/wiki/") <0){
				break;
			}
			String outUrl = fullText.substring(beginIdx, endIdx);

			if(!outUrl.contains(":") && !outUrl.contains("#")){
				if(!link.contains(outUrl)){
					link.add(fullText.substring(beginIdx, endIdx));
				}
			}
			fullText = fullText.substring(endIdx + 1);
		}
		return link.toArray(new String[link.size()]);
	}




	//Check if page has all the keywords, return true or false
	private boolean keyValueCheckInPage(String url, String[] topics) throws InterruptedException{
		StringBuffer sb = getRawPage(url);
		if(sb == null){ // in case page does not load
			return false;
		}
		String str = sb.toString().toLowerCase();
		boolean flag = true;
		for(int i=0; i<topics.length; i++){
			if(!str.contains(topics[i].toLowerCase())){
				flag = false;
				break;
			}
		}
		return flag;
	}

	//write String array in a file
	private void fileWrite(String[] arr) throws IOException{
		File file = new File(fileName);
		System.out.println("In file write fn");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
		bw.write("" + count);
		bw.newLine();
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if(count++==max){
				break;
			}
			bw.write(arr[i]);
			bw.newLine();
		}
		bw.close();
	}

	//return full page address based on relative address
	private String fullAddress(String relativeAddress){
		if(disAllowedLinks.contains(relativeAddress)){
			System.out.println("Sorry crawling for this page is not allowed");
			return null;
		}
		return  BASE_URL + relativeAddress;
	}

	//return raw page address based on relative address
	private String rawAddr(String relativeAddress){
		if(disAllowedLinks.contains(relativeAddress)){
			System.out.println("Sorry crawling for this page is not allowed");
			return null;
		}
		return BASE_URL + "/w/index.php?title=" + relativeAddress.substring(relativeAddress.indexOf("/", 1)+1)+"&action=raw";
	}


	public StringBuffer getRawPage(String relativeAddress) throws InterruptedException{
//		System.out.println("In Get raw page");
		if(requestCount >0 && requestCount%100 == 0){
			Thread.sleep(3000);
		}
		requestCount++;
		StringBuffer rawPage = null;
		if(rawPagesMap.containsKey(relativeAddress)){
			System.out.println("Ye Ye using buffer map !!!!!!!!!!!!!!!!!!!!!!!!!");
			return rawPagesMap.get(relativeAddress);
		}
		try{
			String rawAddr = rawAddr(relativeAddress);
			if(rawAddr == null){
				return rawPage;
			}
			URL url = new URL(rawAddr);
			HttpURLConnection urlconnection = (HttpURLConnection)url.openConnection();
			urlconnection.connect();    
			int code = urlconnection.getResponseCode();
			if(code != 200){
				//System.out.println(code);
				return rawPage;
			}
			InputStream is = urlconnection.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String line = null;
			rawPage = new StringBuffer();

			while((line=bf.readLine())!=null){
				rawPage.append(line).append("/n");
			}
			
			rawPagesMap.put(relativeAddress,rawPage);
			return rawPage;
		}catch(Exception e){
			e.printStackTrace();
			return rawPage;
		}
	}
	private StringBuffer getFullPage(String relativeAddress) throws InterruptedException{
		System.out.println("In Get Full page :::::");
		if(requestCount >0 && requestCount%100 == 0){
			Thread.sleep(3000);
		}
		requestCount++;
		StringBuffer sb = null;
		try{
			String fullAddress = fullAddress(relativeAddress);
			if(fullAddress == null){
				return sb;
			}
			URL url = new URL(fullAddress);
			HttpURLConnection urlconnection = (HttpURLConnection)url.openConnection();
			urlconnection.connect();         
			InputStream is = urlconnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			sb = new StringBuffer();
			String str = null;

			//read line by line
			while((str=br.readLine())!=null){
				sb.append(str).append("/n");
			}
			return sb;
		}catch(IOException e){
			e.printStackTrace();
			return sb;
		}
	}

	public ArrayList<String> readRobotTextFile(){
		ArrayList<String> list = new ArrayList<String>();
		try{
//			System.out.println("Start of robot txt");
			URL url = new URL(BASE_URL + "/robots.txt");
			HttpURLConnection urlconnection = (HttpURLConnection)url.openConnection();
			urlconnection.connect();         
			InputStream is = urlconnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str;
			//read line by line
			while((str=br.readLine())!=null){
				if(str.contains("Disallow:") && str.contains("wiki")){
					//System.out.println(str);
					list.add(str.split("Disallow: ")[1]);
				}
			}
			return list;
		}catch(IOException e){
			e.printStackTrace();
			return list;
		}

	}
}
