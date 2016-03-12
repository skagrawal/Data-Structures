/**
 * 
 */
package ska.coms535.minhash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Shubham Agrawal And Swatie Bansal
 */
public class MinHash {

	private int numPermutations, p;
	private String folder;
	private String[] fileList;
	private Object[] wordSet;
	private int[][] terms;
	private int[] RandA, RandB;
	private HashMap<String, int[]> minSigRecords;
	ArrayList<String> textSet = new ArrayList<String>();

	/**
	 * 
	 * @param folder
	 * @param numPermutations
	 */
	public MinHash(String folder, int numPermutations) {

		this.folder = folder;
		this.numPermutations = numPermutations;
		this.fileList = allDocs();

		HashMap<String, Set<Integer>> mapping = generateHasMap(fileList);

		terms = new int[fileList.length][mapping.size()];
		wordSet = mapping.keySet().toArray();

		for (int i = 0; i < wordSet.length; i++) {
			for (int j = 0; j < fileList.length; j++) {
				if (mapping.get((String) wordSet[i]).contains(j)) {
					terms[j][i] = 1;
				}
			}
		}

		// ArrayList<String> text = new ArrayList<String>();
		// Scanner fileScanner = null;
		// for(int i=0; i< fileList.length;i++){
		// try {
		// fileScanner = new Scanner(new File(folder+"/"+fileList[i]));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// while (fileScanner.hasNextLine()) {
		// Scanner lineScanner = new Scanner(fileScanner.nextLine());
		// while (lineScanner.hasNext()) {
		// String s = lineScanner.next().toLowerCase();
		// s = s.replaceAll("[:.,;']", "");
		// s = s.replaceAll("\\s+", " ");
		// s = s.replaceAll("\\d","");
		// if(s.length() >= 3 && !s.equalsIgnoreCase("the")){
		// if(!text.contains(s)){
		// text.add(s);
		// }
		// textSet.add(s);
		// }
		// }
		// lineScanner.close();
		// }
		// }

		// wordSet = text.toArray(new String[text.size()]);
		// System.out.println("Size of word set = "+ wordSet.length + " and Size
		// of text set = "+ textSet.size());
		//
		// setTerms();
		setPrimeNumber();
		Randomize();
	}

	private HashMap<String, Set<Integer>> generateHasMap(String[] dir) {
		Set<Integer> docs = new HashSet<Integer>();
		HashMap<String, Set<Integer>> mapping = new HashMap<String, Set<Integer>>();
		Scanner fileScanner = null;
		for (int i = 0; i < fileList.length; i++) {
			try {
				fileScanner = new Scanner(new File(folder + "/" + fileList[i]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (fileScanner.hasNextLine()) {
				Scanner lineScanner = new Scanner(fileScanner.nextLine());
				while (lineScanner.hasNext()) {
					String s = lineScanner.next().toLowerCase();
					s = s.replaceAll("[:.,;']", "");
					s = s.replaceAll("\\s+", " ");
					s = s.replaceAll("\\d", "");
					if (s.length() >= 3 && !s.equalsIgnoreCase("the")) {
						Set<Integer> temp = mapping.get(s);
						if (temp == null) {
							temp = new HashSet<Integer>();
							temp.add(i);
							mapping.put(s, temp);
						} else {
							temp.add(i);
							mapping.put(s, temp);
						}
					}
				}
				lineScanner.close();
			}
		}
		return mapping;
	}

	public String[] allDocs() {
		File file = new File(folder);
		// get the folder list
		File[] list = file.listFiles();
		String[] fileNamesList = new String[list.length];
		int i = 0;
		for (File fl : list) {
			fileNamesList[i++] = fl.getName();
		}
		return fileNamesList;
	}

	/**
	 * Get names of two files (in the document collection) file1 and file2 as
	 * parameters and returns the exact Jaccard Similarity of the files
	 * 
	 * @param file1
	 * @param file2
	 * @return Exact Jaccard similarity
	 */
	public double exactJaccard(String file1, String file2) {
		double LT1, LT2, dotProd;

		int T1 = getIndex(file1);
		int T2 = getIndex(file2);

		// calculate L(T1) and L(T2)
		dotProd = 0;
		LT1 = 0;
		LT2 = 0;
		for (int i = 0; i < wordSet.length; i++) {
			dotProd += terms[T1][i] * terms[T2][i];
			LT1 += terms[T1][i] * terms[T1][i];
			LT2 += terms[T2][i] * terms[T2][i];
		}

		// Jaccard Similarity between Da and Db, Jac(Da, Db) = Ta · Tb /
		// [L2(Ta)]^2 + [L2(Tb)]^2 − Ta·Tb
		return dotProd / (LT1 + LT2 - dotProd);
	}

	private int getIndex(String s) {
		int i;
		for (i = 0; i < fileList.length; i++) {
			if (fileList[i].equalsIgnoreCase(s)) {
				break;
			}
		}
		return i;
	}

	/**
	 * Returns the MinHash the Min hash signature of the document named
	 * fileName, which is an array of ints.
	 * 
	 * @param fileName
	 * @return min hash signature - minHashSig
	 */
	public int[] minHashSig(String fileName) {
		int[] minHashSig = new int[numPermutations];
		int ind = getIndex(fileName);
		int minHash;
		int hash;
		int num = numberOfTerms();
		for (int j = 0; j < numPermutations; j++) {
			minHash = -1;
			for (int i = 0; i < num; i++) {
				if (terms[ind][i] == 1) {
					hash = (i * RandA[j] + RandB[j]) % p;
					if (minHash == -1) {
						minHash = hash;
					} else if (hash < minHash) {
						minHash = hash;
					}
				}
			}
			minHashSig[j] = minHash;
		}
		return minHashSig;

	}

	/**
	 * Estimates and returns the Jaccard similarity of documents file1 and file2
	 * by comparing the MinHash signatures of file1 and file2.
	 * 
	 * @param file1
	 * @param file2
	 * @return Approximate Jaccard similarity of 2 files
	 */
	public double approximateJaccard(String file1, String file2) {

		int[] HS1 = this.getMinSigOf(file1);
		int[] HS2 = this.getMinSigOf(file2);
		int count = 0;
		for (int i = 0; i < numPermutations; i++) {
			if (HS1[i] == HS2[i]) {
				count++;
			}
		}
		return (double) count / numPermutations;
	}

	public void recordAllMinSigs(String[] list) {
		minSigRecords = new HashMap<String, int[]>();
		for (int i = 0; i < list.length; i++) {
			minSigRecords.put(list[i], this.minHashSig(list[i]));
		}
	}

	public int[] getMinSigOf(String fileName) {
		if (!minSigRecords.containsKey(fileName)) {
			System.out.println("I have not found:" + fileName);
		}
		return minSigRecords.get(fileName);
	}

	public int[][] minHashMatrix() {

		int[][] mat = new int[numPermutations][fileList.length];
		for (int i = 0; i < fileList.length; i++) {
			int[] t = minHashSig(fileList[i]);
			for (int j = 0; j < numPermutations; j++) {
				mat[j][i] = t[j];
			}
		}
		return mat;
	}

	public int numberOfTerms() {
		return wordSet.length;
	}

	public int numPermutations() {
		return numPermutations;
	}

	private void setTerms() {
		System.out.println("In set terms");
		long t1 = System.currentTimeMillis();
		System.out.println(fileList.length + " " + wordSet.length);
		// terms = new int[fileList.length][wordSet.length];

		for (int i = 0; i < fileList.length; i++) {
			for (int j = 0; j < wordSet.length; j++) {
				if (textSet.contains(wordSet[j])) {
					terms[i][j] = 1;
				} else {
					terms[i][j] = 0;
				}
			}
		}

		long t2 = System.currentTimeMillis();
		System.out.println("Time in set terms:" + (t2 - t1) + " ms");
	}

	private void Randomize() {
		RandA = new int[numPermutations];
		Random rand = new Random();
		for (int i = 0; i < numPermutations; i++) {
			RandA[i] = rand.nextInt(wordSet.length - 1) + 1;
		}

		RandB = new int[numPermutations];
		rand = new Random();
		for (int i = 0; i < numPermutations; i++) {
			RandB[i] = rand.nextInt(wordSet.length - 1) + 1;
		}
	}

	private void setPrimeNumber() {
		Random rand = new Random();
		while (true) {
			int num = wordSet.length + rand.nextInt(wordSet.length);
			if (isPrimeNumber(num)) {
				p = num;
				break;
			}
		}
	}

	private boolean isPrimeNumber(int num) {
		if (num < 2) {
			return false;
		}
		int sqrt = (int) Math.sqrt(num);
		for (int i = 2; i <= sqrt; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
