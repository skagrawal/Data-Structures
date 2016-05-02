/**
 * 
 */
package ska.coms535.informationretrieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shubham And Swatie
 *
 */
public class BiWordIndex {


	private String folder;
	private String[] fileList;
	static HashMap<String,String> allFileText;
	private HashMap<String,LinkedList<String>> invertedIndexMap ;
	public BiWordIndex(String folder) {

		this.folder = folder;
		this.fileList = allDocs();
		BiWordIndex.allFileText = new HashMap<String, String>();
		this.invertedIndexMap = new HashMap<String, LinkedList<String>>();

		Scanner sc = null;
		for(int i=0; i < fileList.length;i++){
			try{
				sc = new Scanner(new File(folder+"/"+fileList[i]));
			} 
			catch (FileNotFoundException e){
				e.printStackTrace();  
			}
			StringBuilder text = new StringBuilder();
			while (sc.hasNextLine()){
				String line = sc.nextLine().toLowerCase();
				line = symbolFilter(line);
				text = text.append(line).append("\n");
			}	
			BiWordIndex.allFileText.put(fileList[i],text.toString());
		}
		buildIndex();
	}

	private void buildIndex()
	{
		long start = System.currentTimeMillis( );
		Set<String> keySet = allFileText.keySet();
		invertedIndexMap = new HashMap<String, LinkedList<String>>();
		for (String key : keySet) {
			String[] biWords = getBiWords(allFileText.get(key));

			for(String biWord : biWords){
				LinkedList<String> temp = invertedIndexMap.get(biWord);
				if(temp == null){
					temp = new LinkedList<String>();
				}
				if(!temp.contains(key)){
					temp.add(key);
				}
				invertedIndexMap.put(biWord, temp);
			}
		}
		long end = System.currentTimeMillis( );
		System.out.println("Time in  buildIndex in biword: " + (end - start)/1000 + " sec");
	}


	private  String[] getBiWords(String query){
		ArrayList<String> temp = new ArrayList<String>();
		String first = "";
		String next = "";
		StringTokenizer st = new StringTokenizer(query);
		while(st.hasMoreTokens() && (first.length() < 3 || "the".equalsIgnoreCase(first))){
			first = st.nextToken();
		}

		while (st.hasMoreTokens()){
			next = st.nextToken();
			if(next.length() < 3 || "the".equalsIgnoreCase(next)){
				//continue
			}
			else{
				String biword = first + " " + next;
				if(!temp.contains(biword))
				{
					temp.add(biword);
					first = next;
				}
			}    
		}
		return temp.toArray(new String[temp.size()]);
	}
	private LinkedList<String> postingsList(String t){
		Set<String> words = invertedIndexMap.keySet();
		for(String word : words){
			if(word.equals(t)){
				return invertedIndexMap.get(word);
			}
		}
		System.out.println("postingsList not found for - "+t);
		return null;
	}

	public void printPostingsList(String t){
		LinkedList<String> postingsList = postingsList(t);
		if(postingsList == null){
			System.out.println("postingsList is null");
			return;
		}
		for(String posting : postingsList){
			System.out.println("Term - "+ t+ ", Document - "+posting);
		}

	}

	private String[] allDocs(){
		File file = new File(folder);   
		File[] array = file.listFiles(); 
		String[] fileNames = new String[array.length];
		for(int i=0;i<array.length;i++)
		{   
			if(array[i].isFile()){     
				fileNames[i] = array[i].getName();   
			}
		}
		return fileNames;
	}

	private String symbolFilter(String str){
		String regEx="[.,:;']";  
		Pattern p = Pattern.compile(regEx);     
		Matcher m = p.matcher(str);     
		return m.replaceAll(""); 
	}

}



