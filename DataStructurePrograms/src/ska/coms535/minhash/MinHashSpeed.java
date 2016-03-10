/**
 * 
 */
package ska.coms535.minhash;

import java.util.Scanner;

/**
 * @author Shubham and Swati 
 *
 */
public class MinHashSpeed {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String folder;
		int numPermutations;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Folder directory - ");
		folder = sc.nextLine();
		System.out.println("Enter Number of permutations - ");
		numPermutations = sc.nextInt();
		sc.close();
		MinHash min = new MinHash(folder,numPermutations);
		String[] list= min.allDocs();
		
		long t1 = System.currentTimeMillis();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				min.exactJaccard(list[i], list[j]);
			}
		}
		long t2 = System.currentTimeMillis();
		
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
		
		
		System.out.println("Time [Exact Jaccard Similarities]: " + (t2-t1)+ " ms");
		System.out.println("Time [Estimated Jaccard Similarities]: " + (t4-t3)+ " ms");
		
	}

}
