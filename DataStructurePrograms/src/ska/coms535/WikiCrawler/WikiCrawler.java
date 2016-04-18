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

public class WikiCrawler {
	//initialize variables 
	static final String BASE_URL = "https://en.wikipedia.org";
	private String seedUrl, fileName;
	private String[] keywords;
	private ArrayList<String> edges, checkUrlIdx, fullPgIdx, queue, visited;
	private ArrayList<StringBuffer> fullPgContent;
	private ArrayList<Boolean> checkUrlBoolean;
	private int max, count, request;
	private ArrayList<String> disAllowedLinks;
	
	//Constructor
	WikiCrawler(String seed, String[] topics, int maxPage, String file){
		this.seedUrl = seed;
		this.keywords = topics;
		this.max = maxPage;
		this.fileName = file;
		this.edges = new ArrayList<String>();
		this.checkUrlIdx = new ArrayList<String>();
		this.checkUrlBoolean = new ArrayList<Boolean>();
		this.fullPgIdx = new ArrayList<String>();
		this.fullPgContent = new ArrayList<StringBuffer>();
		this.queue = new ArrayList<String>();
		this.visited = new ArrayList<String>();
		this.count = 0;
		this.disAllowedLinks = readRobotTextFile();
	}
	//crawler
	public void crawl() throws InterruptedException, IOException{
		queue.add(seedUrl);
		visited.add(seedUrl);
		count++;
		while(queue.size()>0){
			String current = queue.get(0);
			StringBuffer currentPage = getFullPage(current);
			fullPgIdx.add(current);
			fullPgContent.add(currentPage);
			String[] u = outLink(currentPage);
			for(int i=0; i<u.length; i++){
				boolean check = keyCheck(u[i],keywords);
				if(!checkUrlIdx.contains(u[i])){
					checkUrlIdx.add(u[i]);
					checkUrlBoolean.add(check);
				}
				if(!visited.contains(u[i])&&check){
					queue.add(u[i]);
					visited.add(u[i]);
					count++;
				}
				if(count==max){
					break;
				}
			}
			if(count==max){
				break;
			}
			queue.remove(0);
		}
		for(int j=0; j<visited.size(); j++){
			StringBuffer out;
			String[] link;
			if(fullPgIdx.contains(visited.get(j))){
				out = fullPgContent.get(fullPgIdx.indexOf(visited.get(j)));
			}else{
				out = getFullPage(visited.get(j));
			}	
			link = outLink(out);
			for(int i=0; i<link.length;i++){
				if(visited.contains(link[i])){
					if(checkUrlIdx.contains(link[i])){
						int index = checkUrlIdx.indexOf(link[i]);
						if(checkUrlBoolean.get(index) && visited.contains(link[i])&&!edges.contains(visited.get(j) + " " + link[i])){
							edges.add(visited.get(j) + " " + link[i]);
						}
					}else{
						if(keyCheck(link[i], keywords)&&visited.contains(link[i])&&!edges.contains(visited.get(j) + " " + link[i])){
							checkUrlIdx.add(link[i]);
							checkUrlBoolean.add(true);
							edges.add(visited.get(j) + " " + link[i]);
						}else{
							checkUrlIdx.add(link[i]);
							checkUrlBoolean.add(false);
						}
					}
				}		
			}
		}
		//output file
		fileWriter(edges.toArray(new String[edges.size()]));
		System.out.println(edges.size()+" "  +request);	
	}
	
	//Check if page has all the keywords, return true or false
	private boolean keyCheck(String url, String[] key) throws InterruptedException{
		StringBuffer buffer = getRawPage(url);
		if(buffer == null){ // in case page does not load
			return false;
		}
		String s = buffer.toString().toLowerCase();
		boolean flag = true;
		for(int i=0; i<key.length; i++){
			if(!s.contains(key[i].toLowerCase())){
	        	flag = false;
	        	break;
	        }
		}
		return flag;
	}
	
	//Return a list of all out going links of a particular page
	private String[] outLink(StringBuffer b){
		String txt = b.toString();
		int idx = txt.indexOf("<p>") + "<p>".length();
		txt = txt.substring(idx);
		ArrayList<String> link = new ArrayList<String>();
		while(true){
			int beginIdx = txt.indexOf("/wiki/");
		    int endIdx = txt.indexOf("\"", beginIdx);
		    if(txt.indexOf("/wiki/") <0){
		    	break;
		    }
		    String outUrl = txt.substring(beginIdx, endIdx);
		    
		    //skip urls contain : and #
		    if(!outUrl.contains(":")&&!outUrl.contains("#")){
		    	if(!link.contains(outUrl)){
		    		//if(srcUrls.contains(outUrl)||visited.contains(outUrl)){
				    	link.add(txt.substring(beginIdx, endIdx));
		    		//}
			    }
		    }
		    txt = txt.substring(endIdx + 1);
		}
		return link.toArray(new String[link.size()]);
	}
	
	//get wiki raw page from given url
	private StringBuffer getRawPage(String relaAddr) throws InterruptedException{
		
		//for every 100 requests, pause 3 sec
		if(request >0 && request%100 == 0){
			Thread.sleep(3000);
		}
		request++;
		StringBuffer bs = null;
		try{
			URL url = new URL(rawAddr(relaAddr));
	        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
	        urlcon.connect();         
	        InputStream is = urlcon.getInputStream();
	        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
	        bs = new StringBuffer();
	        String l = null;
	        
	        //read line by line
	        while((l=buffer.readLine())!=null){
	            bs.append(l).append("/n");
	        }
	        return bs;
		}catch(IOException e){
			System.out.println(e);
			return bs;
		}
	}
	
	//get wiki full page from given url
	private StringBuffer getFullPage(String relaAddr) throws InterruptedException{
		
		//for every 100 requests, pause 3 sec
		if(request >0 && request%100 == 0){
			Thread.sleep(3000);
		}
		request++;
		StringBuffer bs = null;
		try{
			URL url = new URL(fullAddr(relaAddr));
	        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
	        urlcon.connect();         
	        InputStream is = urlcon.getInputStream();
	        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
	        bs = new StringBuffer();
	        String l = null;
	        
	        //read line by line
	        while((l=buffer.readLine())!=null){
	            bs.append(l).append("/n");
	        }
	        return bs;
		}catch(IOException e){
			System.out.println(e);
			return bs;
		}
	}
	
	//return full page address based on relative address
	private String fullAddr(String relaAddr){
		if(disAllowedLinks.contains(relaAddr)){
			System.out.println("Sorry crawling for this page is not allowed");
			return null;
		}
		return BASE_URL + relaAddr;
	}
	
	//return raw page address based on relative address
	private String rawAddr(String relaAddr){
		if(disAllowedLinks.contains(relaAddr)){
			System.out.println("Sorry crawling for this page is not allowed");
			return null;
		}
		return BASE_URL +"/w/index.php?title=" + relaAddr.substring(relaAddr.indexOf("/", 1)+1)+"&action=raw";
	}
	
	//write String array in a file
	private void fileWriter(String[] s) throws IOException{
		File fout = new File(fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("" + count);
		bw.newLine();
		for (int i = 0; i < s.length; i++) {
			bw.write(s[i]);
			bw.newLine();
		}
		bw.close();
	}
	
	
	private ArrayList<String> readRobotTextFile(){
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
