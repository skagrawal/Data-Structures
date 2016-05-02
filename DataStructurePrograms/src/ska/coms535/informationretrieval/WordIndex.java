/**
 * 
 */
package ska.coms535.informationretrieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class WordIndex {


	private String folder;
	private String[] fileList, terms;
	static public HashMap<String,String> allFileText;
	private LinkedList<dictionary> invertedIndex;
	public WordIndex(String folder) {

		this.folder = folder;
		this.fileList = allDocs();
		WordIndex.allFileText = new HashMap<String, String>();
		this.invertedIndex = new LinkedList<dictionary>();

		ArrayList<String> temp = new ArrayList<String>();
		Scanner sc = null;
		for(int i=0; i < fileList.length;i++)
		{
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
				StringTokenizer st = new StringTokenizer(line);
				
				while (st.hasMoreTokens()){ 
					String str = st.nextToken();
					if(str.length()>2){
						if(!str.equals("the")){
							if(!temp.contains(str)){
								temp.add(str);
							}
						}    
					}
				}
			}
//			System.out.println(text.toString());
			WordIndex.allFileText.put(fileList[i],text.toString());
		}
		this.terms = temp.toArray(new String[temp.size()]);
		buildIndex();
	}

	private void buildIndex(){
		long start = System.currentTimeMillis( );
		for(int i=0;i<terms.length;i++){
			String term = terms[i];
			LinkedList<postings> postingList = new LinkedList<postings>();
			int count = 0;
			Set<String> keySet = allFileText.keySet();
			for (String key : keySet) {
				String text = allFileText.get(key);
				if(text.contains(term)){
					count++; // count of number of documents in which term appears
					int tftd = 0;
					StringTokenizer st = new StringTokenizer(text);
					while(st.hasMoreTokens()){
						String s = st.nextToken();
						if(s.equals(term)){
							tftd++;
						}
					}
					if(tftd>0){
						postings posting = new postings(key, tftd);
						postingList.add(posting);
					}
				}
			}
			invertedIndex.add(new dictionary(term, count, postingList));
		}
		long end = System.currentTimeMillis( );
        System.out.println("Time in  buildIndex: " + (end - start)/1000+ " sec");
	}

	private LinkedList<postings> postingsList(String t){
		for(dictionary dic : invertedIndex){
			if(dic.t.equals(t)){
				return dic.postings;
			}
		}
		System.out.println("postingsList not found for - "+t);
		return null;
	}

	public void printPostingsList(String t){
		LinkedList<postings> postingsList = postingsList(t);
		if(postingsList == null){
			System.out.println("postingsList is null");
			return;
		}
		for(postings posting : postingsList){
			System.out.println("Term - "+ t+ ", Document - "+posting.document + ", TFTD - "+posting.tftd);
		}
	}

	public double weight(String t, String d)
	{
		LinkedList<postings> postingsList = postingsList(t);
		int dfti = postingsList.size();
		int N = fileList.length;
		for(postings posting : postingsList){
			if(posting.document.equals(d)){
				return Math.log(1 + posting.tftd)*Math.log10(N*1.0/dfti);
			}
		}
		return -1;
	}

	private int indexOf(String[] arr, String str)
	{
		for(int i=0; i<arr.length; i++)
		{
			if(arr[i].equals(str))
			{
				return i;
			}
		}
		return -1;
	}

	private int dfti(String t)
	{
		int count = 0;
		Scanner sc = null;
		for(int i=0; i<this.fileList.length;i++)
		{
			try 
			{
				sc = new Scanner(new File(folder+"/"+fileList[i]));
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();  
			}
			while (sc.hasNextLine())
			{
				String line = sc.nextLine().toLowerCase();
				if (line.contains(t))
				{
					count++;
					break;
				}
			}
		}
		return count;
	}

	private String[] allDocs()
	{
		File file = new File(folder);   
		// get the folder list   
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

	private String symbolFilter(String str)
	{
		String regEx="[.,:;']";  
		Pattern p = Pattern.compile(regEx);     
		Matcher m = p.matcher(str);     
		return m.replaceAll(""); 
	}

}


class dictionary{
	String t;//term
	int dft;//number of documents in which t appears
	LinkedList<postings> postings;
	
	public dictionary(String t,int dft, LinkedList<postings> posting) {
		this.t = t;
		this.dft = dft;
		this.postings = posting;
	}
	
	
	@Override
	public String toString() {
		System.out.println(this.t + " " + this.dft + " " + this.postings.toString());
		return super.toString();
	}
	
}

class postings{
	String document;//list is a document d in which t appears
	int tftd;//denote the number of times term t appears in document d,
	
	public postings(String doc,int tftd) {
		this.document = doc;
		this.tftd = tftd;
	}
	
	@Override
	public String toString() {
		System.out.println("Postings - "+ document + " "+ tftd);
		return super.toString();
	}
}
