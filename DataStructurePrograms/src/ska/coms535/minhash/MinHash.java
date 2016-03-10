/**
 * 
 */
package ska.coms535.minhash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Shubham and Swati
 *
 */
public class MinHash {

	private int numPermutations,p;
	private String folder;
	private String[] fileList;
	private String[] wordSet;
	private int[][] terms;
	private int[] RandA, RandB;
	/**
	 * 
	 * @param folder
	 * @param numPermutations
	 */
	public MinHash(String folder, int numPermutations){  

		this.folder = folder;
		this.numPermutations = numPermutations;

		this.fileList = allDocs();
		BufferedReader br;
		ArrayList<String> text = new ArrayList<String>();
		for(String file:fileList){
			try {
				br = new BufferedReader(new FileReader(folder+"/"+file));
				String line = br.readLine().toLowerCase();
				while (line != null) {
					if(!text.contains(line)){
						text.add(line);
					}
					line = br.readLine();
				}
				br.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		wordSet = text.toArray(new String[text.size()]);
		setTerms();
		setPrimeNumber();
		Randomize();
	}

	public String[] allDocs(){
		File file = new File(folder);   
		// get the folder list   
		File[] list = file.listFiles(); 
		String[] fileNamesList = new String[list.length];
		int i=0;
		for(File fl : list){
			fileNamesList[i++] = fl.getName();   
		}
		return fileNamesList;
	}

	/**
	 * Get names of two files (in the document collection) file1 and file2 as parameters and returns the exact Jaccard Similarity of the files
	 * @param file1
	 * @param file2
	 * @return Exact Jaccard similarity
	 */
	public double exactJaccard(String file1, String file2){
		double LT1, LT2, dotProd;

		int T1 = getIndex(file1);
		int T2 = getIndex(file2);

		//calculate L(T1) and L(T2)
		dotProd = 0;
		LT1 = 0;
		LT2 = 0;
		for(int i=0; i<wordSet.length; i++){
			dotProd += terms[T1][i] * terms[T2][i];
			LT1 += terms[T1][i] * terms[T1][i];
			LT2 += terms[T2][i] * terms[T2][i];
		}

		// Jaccard Similarity between Da and Db, Jac(Da, Db) = Ta · Tb / [L2(Ta)]^2 + [L2(Tb)]^2 − Ta·Tb
		return dotProd/(LT1+LT2-dotProd);
	}

	private int getIndex(String s){
		int i;
		for(i=0; i<fileList.length; i++){
			if(fileList[i].equalsIgnoreCase(s)){
				break;
			}
		}
		return i;
	}

	/**
	 * Returns the MinHash the Min hash signature of the document named fileName, which is an array of ints.
	 * @param fileName
	 * @return min hash signature - minHashSig
	 */
	public int[] minHashSig(String fileName){
		int[] minHashSig = new int[numPermutations];
		int ind = getIndex(fileName);
		int minHash;
		int hash;
		int num = numberOfTerms();
		for(int j=0; j<numPermutations; j++){
			minHash = -1;
			for(int i=0; i<num; i++){
				if(terms[ind][i] == 1){
					hash = (i*RandA[j]+RandB[j])%p;
					if(minHash == -1){ 
						minHash = hash;
					}else if(hash<minHash){
						minHash = hash;
					}
				}
			}
			minHashSig[j] = minHash;
		}
		return minHashSig;

	}

	/**
	 * Estimates and returns the Jaccard similarity of documents file1 and file2 by comparing the MinHash signatures of file1 and file2.
	 * @param file1
	 * @param file2
	 * @return Approximate Jaccard similarity of 2 files
	 */
	public double approximateJaccard(String file1, String file2){
		int[] HS1 = minHashSig(file1);
		int[] HS2 = minHashSig(file2);
		int count = 0;
		
		for(int i=0; i<numPermutations; i++){
			if(HS1[i] == HS2[i]){
				count++;
			}
		}
		return (double)count/numPermutations;
	}


	public int[][] minHashMatrix(){

		int[][] mat = new int[numPermutations][fileList.length];
		for(int i=0; i< fileList.length;i++){
			int[] t = minHashSig(fileList[i]);
			for(int j=0; j<numPermutations; j++){
				mat[j][i] = t[j];
			}
		}
		return mat;

	}

	public int numberOfTerms(){
		return wordSet.length;

	}

	public int numPermutations(){
		return numPermutations;
	}

	private void setTerms(){
		System.out.println(fileList.length+" "+ wordSet.length);
		terms = new int[fileList.length][wordSet.length];
		BufferedReader br;
		ArrayList<String> text = new ArrayList<String>();
		for(int i=0; i<fileList.length; i++){
			try {
				br = new BufferedReader(new FileReader(folder+"/"+fileList[i]));
				String line = br.readLine().toLowerCase();
				while (line != null) {
					text.add(line);
					line = br.readLine();
				}
				for(int j=0; j<wordSet.length; j++){
					if(text.contains(wordSet[j])){
						terms[i][j] = 1;
					}else{
						terms[i][j] = 0;
					}
				}
				br.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
	}

	private void Randomize(){
		RandA = new int[numPermutations];
		Random rand = new Random();
		for(int i=0; i<numPermutations; i++){
			RandA[i] = rand.nextInt(wordSet.length-1)+1;
		}

		RandB = new int[numPermutations];
		rand = new Random();
		for(int i=0; i<numPermutations; i++){
			RandB[i] = rand.nextInt(wordSet.length-1)+1;
		}
	}

	private void setPrimeNumber(){
		Random rand = new Random();
		while(true){
			int num = wordSet.length + rand.nextInt(wordSet.length);
			if(isPrimeNumber(num)){
				p = num;
				break;
			}
		}
	}
	
	
	private boolean isPrimeNumber(int num){
		if(num < 2){
			return false;
		}
		int sqrt = (int)Math.sqrt(num);
		for(int i = 2; i <= sqrt; i++){
			if(num%i == 0){
				return false;
			}
		}
		return true;
	}

}
