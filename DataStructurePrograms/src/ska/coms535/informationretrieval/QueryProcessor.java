/**
 * 
 */
package ska.coms535.informationretrieval;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shubham And Swatie
 *
 */
public class QueryProcessor {

	
	public static void main(String[] args) {

		String folder = "/Users/Shubham/Documents/pa4-data";
		WordIndex wordIndex = new WordIndex(folder);
		BiWordIndex biWordIndex = new BiWordIndex(folder);
//		System.exit(3);

		Scanner sc =  new Scanner(System.in);
		while(true){
			System.out.print("Enter Query : ");
			String query = sc.nextLine();
			System.out.print("Enter the value of k : ");
			int k = sc.nextInt();
			String[] lstBiWords = getBiWords(query);
			String[] tokens = getTokensFromQuery(query);
			double[] vq = getVectorForQuery(query, tokens);
			Set<DocCosineFreqTriple> orderedSet = new TreeSet<DocCosineFreqTriple>();
			String[] lstDocs = allDocs(folder);
			for(String docName : lstDocs ){
				double[] vi = new double[tokens.length];
				for(int termIter = 0 ; termIter < tokens.length; termIter++){
					vi[termIter] = wordIndex.weight(tokens[termIter], docName);
				}
				
				double cosineSimilarity = cosineSimilarity(vi, vq);
				int sd = getSd(lstBiWords, docName);
				orderedSet.add(new DocCosineFreqTriple(docName, cosineSimilarity, sd));
			}

			
			System.out.println(k +" Documents along with their cosine similarities are :");
			int iter = 0;
			for(DocCosineFreqTriple obj : orderedSet){
				System.out.println(obj.getDocName() +" ( Cosine Similarity : " +obj.getCosineSimilarity() +" )");
				iter++;
				if(iter == k){
					break;
				}
			}
			

			System.out.print("Enter q/Q to Quit. Else Press Enter:");
			String nextItr = sc.nextLine();
			if(nextItr.equalsIgnoreCase("q")){
				break;
			}
			System.out.println();
//			break;
		}


	}

	private static double cosineSimilarity(double[] vector1 , double[] vector2){
		double dotP = 1.0;
		double len1 = 0.0;
		double len2 = 0.0;

		if(vector1.length != vector2.length){
			// This should not be the case
			// But just in case, returning -1 implying docs are completely opposite
			return -1.0;
		}else{
			//do nothing
		}

		for(int i = 0; i < vector1.length; i++){
			dotP += vector1[i] * vector2[i];
			len1 += vector1[i] * vector1[i];
			len2 += vector2[i] * vector2[i];
		}

		return dotP / ( Math.sqrt(len1) * Math.sqrt(len2));

	}
	private static int getSd(String[] lstBiWords, String document){
		int sd = 0;
		String text = WordIndex.allFileText.get(document);
		for(String biWord : lstBiWords){
			String [] arr = biWord.split(" ");
			if(text.contains(arr[0]) &&text.contains(arr[1])){
				sd++;
			}
		}
		return sd;
	}
	
	private static String[] getBiWords(String query){
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
	
	private static double[] getVectorForQuery(String query, String[] arrTerms){
		double[] retVec = new double[arrTerms.length];
		int i = 0;
		for(String term : arrTerms){
			int freq = getFreq(query, term);
			retVec[i++] = Math.log(1 + freq);
		}
		return retVec;
	}

	private static int getFreq(String query, String token){
		query = query.toLowerCase();
		token = token.toLowerCase();
		int freq = 0;
		int startIndex = 0;
		int index= 0;
		while( startIndex <= query.length() && (index = query.indexOf(token, startIndex)) >= 0){
			freq++;
			startIndex = index + token.length();
		}
		return freq;
	}

	private static String[] getTokensFromQuery(String query){
		query = symbolFilter(query);
		Set<String> retList = new HashSet<String>();
		for(String token : query.split(" ")){
			if(token.length() < 3 || "the".equals(token)){
				continue;
			}else{
				retList.add(token);
			}
		}
		return retList.toArray(new String[retList.size()]);
	}

	private static String symbolFilter(String str)
	{
		String regEx="[.,:;']";  
		Pattern p = Pattern.compile(regEx);     
		Matcher m = p.matcher(str);     
		return m.replaceAll(""); 
	}

	private static String[] allDocs(String folder)
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
	

}


class DocCosineFreqTriple implements Comparable<DocCosineFreqTriple>{

	private String docName;
	private double cosineSimilarity;
	private int sd;

	public DocCosineFreqTriple(String docName, double cosineSimilartiy, int sd){
		this.docName = docName;
		this.cosineSimilarity = cosineSimilartiy;
		this.sd = sd;
	}

	@Override
	public int compareTo(DocCosineFreqTriple obj) {
		if(this.sd > obj.getSd()){
			return -1;
		}else if (this.sd < obj.getSd()){
			return 1;
		}else{
			if(this.cosineSimilarity > obj.getCosineSimilarity()){
				return -1;
			}else{
				return 1;
			}
		}
	}

	public String getDocName(){
		return docName;
	}

	public double getCosineSimilarity(){
		return cosineSimilarity;
	}

	public int getSd(){
		return sd;
	}

	@Override
	public String toString(){
		return this.getDocName() +" ( " +this.cosineSimilarity +" ) : " +this.sd;
	}
}