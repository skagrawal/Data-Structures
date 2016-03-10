/**
 * @author Le Zhang and Sayantani Ghosh
 */
package pa2;

import java.util.Scanner;

/**
 * Tests whether it is faster to estimate Jaccard similarities
 * using MinHash matrix or to compute the similarities exactly.
 */
public class MinHashSpeed {
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args){
		String folder;
		int numPermutations;
		Scanner s = new Scanner(System.in);
		System.out.println("Input folder directory: ");
		folder = s.nextLine();
		System.out.println("Input # of permutations: ");
		numPermutations = s.nextInt();
		MinHash min = new MinHash(folder,numPermutations);
		String[] list= min.getFileList();
		//time of exact Jaccard
		long t1 = System.currentTimeMillis();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				min.exactJaccard(list[i], list[j]);
			}
		}
		long t2 = System.currentTimeMillis();
		
		//time of MinHashTable
		long t3 = System.currentTimeMillis();
		int[][] matrix = min.minHashMatrix();
		double sim;
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				int l = 0;
				for(int p=0; p<numPermutations; p++){
					if(matrix[p][i] == matrix[p][j]){
						l++;
					}
				}sim = l*1.0/numPermutations;
			}
		}
		long t4 = System.currentTimeMillis();
		s.close();
		System.out.println("Time of Exact Jaccard Similarities: " + (t2-t1)+ " ms");
		System.out.println("Time of Estimated Jaccard Similarities: " + (t4-t3)+ " ms");
	}
}
