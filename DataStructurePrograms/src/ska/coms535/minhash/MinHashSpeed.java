/**
 * 
 */
package ska.coms535.minhash;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Shubham Agrawal And Swatie Bansal 
 */
/* 
 * I am testing javadoc 
 */


public class MinHashSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("In Min hash speed");
		String folder = "/Users/Shubham/Documents/space";
		int numPermutations = 400;
		HashMap<String, int[]> minHashRecords = new HashMap<String, int[]>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Folder directory - ");
		folder = sc.nextLine();
		System.out.println("Enter Number of permutations - ");
		numPermutations = sc.nextInt();
		sc.close();

		MinHash min = new MinHash(folder,numPermutations);
		String[] list= min.allDocs();

		long exactTime1 = System.currentTimeMillis();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				min.exactJaccard(list[i], list[j]);
			}
		}
		long exactTime2 = System.currentTimeMillis();

		System.out.println("exacttime:"+(exactTime2-exactTime1));
		
		long approxTime1 = System.currentTimeMillis();

		
	
		
		min.recordAllMinSigs(list);
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				min.approximateJaccard(list[i], list[j]);
			}
		}
		long approxTime2 = System.currentTimeMillis();

		System.out.println("Time [Exact Jaccard Similarities]: " + (exactTime2-exactTime1)+ " ms");
		System.out.println("Time [Estimated Jaccard Similarities]: " + (approxTime2-approxTime1)+ " ms");
	}

}
