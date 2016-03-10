/**
 * @author Le Zhang and Sayantani Ghosh
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * To construct a KxN minhash matrix that can be used to estimate the similarity
 * of any two documents, where K is the number of random permutations.
 */
public class MinHash {
	/**
	 * Folder address
	 */
	private String folder;
	/**
	 * Number of permutations
	 */
	private int numPermutations;
	/**
	 * All file names
	 */
	private String[] fileList;
	/**
	 * Word pool of all docs
	 */
	private String[] union;
	/**
	 * Prime number
	 */
	private int p;
	/**
	 * Random coefficients for permutation functions
	 */
	private int[] a, b;
	private int[][] terms;

	/**
	 * folder is the name of a folder containing our document collection for which we wish to construct MinHash matrix. numPermutations denotes the number of permutations to be used in creating the MinHash matrix
	 * @param folder
	 * @param numPermutations
	 */
	public MinHash(String folder, int numPermutations){  
		//Initialize values
		this.folder = folder;
		this.numPermutations = numPermutations;
		this.fileList = allDocs();

		//initialize word pool
		ArrayList<String> unionInitial = new ArrayList<String>();
		Scanner sc = null;
		for(int i=0; i<this.fileList.length;i++){
			try {
				sc = new Scanner(new File(folder+"/"+fileList[i]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();  
			}
			while (sc.hasNextLine()) {
				Scanner sc2 = new Scanner(sc.nextLine());
				while (sc2.hasNext()) {
					String s = sc2.next();
					if(!unionInitial.contains(s.toLowerCase())){
						unionInitial.add(s.toLowerCase());
					}
				}
				sc2.close();
			}
		}
		System.out.println("OUT OF LOOP...");
		this.union = unionInitial.toArray(new String[unionInitial.size()]);
		sc.close();

		//initialize random numbers
		setTerm();
		setPrime();
		setRandomA();
		setRandomB();
		System.out.println("OUT OF CONS");
	}

	/**
	 * Returns an array of String consisting of all the names of files in the document collection
	 * @return file names
	 */
	public String[] allDocs(){
		File file = new File(folder);   
		// get the folder list   
		File[] array = file.listFiles(); 
		String[] fileNames = new String[array.length];
		for(int i=0;i<array.length;i++){   
			if(array[i].isFile()){     
				fileNames[i] = array[i].getName();   
			}
		}
		return fileNames;
	}

	/**
	 * Get names of two files (in the document collection) file1 and file2 as parameters and returns the exact Jaccard Similarity of the files
	 * @param file1
	 * @param file2
	 * @return Jaccard similarity of files
	 */
	public double exactJaccard(String file1, String file2){
		double LT1, LT2, dotProd;
		//get terms index
		int T1 = getIndex(file1);
		int T2 = getIndex(file2);

		//calculate L(T1) and L(T2)
		dotProd = 0;
		LT1 = 0;
		LT2 = 0;
		for(int i=0; i<union.length; i++){
			dotProd += terms[T1][i]*terms[T2][i];
			LT1 += terms[T1][i]*terms[T1][i];
			LT2 += terms[T2][i]*terms[T2][i];
		}

		//calculate Jacard(D1,D2)
		return dotProd/(LT1+LT2-dotProd);
	}

	/**
	 * Returns the MinHash the minhash signature of the document named fileName, which is an array of ints.
	 * @param fileName
	 * @return min hash signature
	 */
	public int[] minHashSig(String fileName){
		int[] minHashSig = new int[numPermutations];
		int index = getIndex(fileName);
		int minHash;
		for(int j=0; j<numPermutations; j++){
			minHash = -1;
			for(int i=0; i<numTerms(); i++){
				if(terms[index][i] == 1){
					int h = (a[j]*i+b[j])%p;
					if(minHash == -1){ //if minHash is initial value, replace it
						minHash = h;
					}else if(h<minHash){ //if minHash is greater than new hash number, replace it
						minHash = h;
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
	 * @return Jaccard similarity of 2 files
	 */
	public double approximateJaccard(String file1, String file2){
		//get hash signatures
		int[] H1 = minHashSig(file1);
		int[] H2 = minHashSig(file2);

		//calculate approximate jaccard similarity
		int l = 0;
		for(int i=0; i<numPermutations; i++){
			if(H1[i] == H2[i]){
				l++;
			}
		}
		return (double)l/numPermutations;
	}

	/**
	 * Returns the MinHash Matrix of the collection
	 * @return min hash
	 */
	public int[][] minHashMatrix(){
		//transform hash signatures into matrix
		int[][] m = new int[numPermutations][fileList.length];
		for(int i=0; i< fileList.length;i++){
			int[] t = minHashSig(fileList[i]);
			for(int j=0; j<numPermutations; j++){
				m[j][i] = t[j];
			}
		}
		return m;
	}

	/**
	 * Generate binary term-frequency vector
	 */
	private void setTerm(){
		terms = new int[fileList.length][union.length];
		for(int i=0; i<fileList.length; i++){
			ArrayList<String> D = new ArrayList<String>();
			Scanner sc = null;
			try {
				sc = new Scanner(new File(folder+"/"+fileList[i]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();  
			}
			while (sc.hasNextLine()) {
				Scanner sc2 = new Scanner(sc.nextLine());
				while (sc2.hasNext()) {
					String s = sc2.next();
					D.add(s.toLowerCase());
				}
				sc2.close();
			}
			for(int j=0; j<union.length; j++){
				if(D.contains(union[j])){
					terms[i][j] = 1;
				}else{
					terms[i][j] = 0;
				}
			}
			sc.close();
		}
	}

	/**
	 * Get file index number
	 * @param file
	 * @return index number
	 */
	private int getIndex(String file){
		int i;
		for(i=0; i<fileList.length; i++){
			if(fileList[i].equalsIgnoreCase(file)){
				break;
			}
		}
		return i;
	}

	/**
	 * Returns the number of terms in the document collection.
	 * @return number of terms
	 */
	public int numTerms(){
		return union.length;
	}

	/**
	 * Returns the number of permutations used to construct the MinHash matrix.
	 * @return number of permutations
	 */
	public int numPermutations(){
		return numPermutations;
	}

	/**
	 * Initialize prime number p greater than M 
	 */
	private void setPrime(){
		Random ran = new Random();
		while(true){
			int prime = union.length + ran.nextInt(union.length);
			if(isPrime(prime)){
				p = prime;
				break;
			}
		}
	}

	/**
	 * Decides if p is prime
	 * @param p
	 * @return whether p is prime or not
	 */
	private boolean isPrime(int p){
		if(p < 2){
			return false;
		}
		int sqrt = (int)Math.sqrt(p);
		for(int i = 2; i <= sqrt; i++){
			if(p%i == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Initializes random coefficient a
	 */
	private void setRandomA(){
		a = new int[numPermutations];
		Random ran = new Random();
		for(int i=0; i<numPermutations; i++){
			a[i] = 1 + ran.nextInt(union.length-1);
		}
	}

	/**
	 * Initializes random coefficient b
	 */
	private void setRandomB(){
		b = new int[numPermutations];
		Random ran = new Random();
		for(int i=0; i<numPermutations; i++){
			b[i] = 1 + ran.nextInt(union.length-1);
		}
	}

	/**
	 * Gets the file list
	 * @return file list
	 */
	public String[] getFileList(){
		return fileList;
	}
}
