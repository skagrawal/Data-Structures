/**
 * 
 */
package ska.coms535.minhash;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Shubham Agrawal And Swatie Bansal
 */
public class MinHashAccuracy {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){

		String folder = "/Users/Shubham/Documents/space";;
		double error = 0.02;
		int numPermutations = 600;

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Folder directory - ");
		folder = sc.nextLine();
		System.out.println("Enter Number of permutations - ");
		numPermutations = sc.nextInt();
		System.out.println("Input error parameter: ");
		error = sc.nextDouble();


		MinHash minHash = new MinHash(folder,numPermutations);
		String[] list= minHash.allDocs();
		ArrayList<Double> exactJaccardSim = new ArrayList<Double>();
		int count = 0;
		
		minHash.recordAllMinSigs(list);
		System.out.println("calculated");
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				double exact = minHash.exactJaccard(list[i], list[j]);
				exactJaccardSim.add(exact);
				double aprox = minHash.approximateJaccard(list[i], list[j]);
				double diff = exact-aprox;
				if(Math.abs(diff) > error){
					count++;
				}
			}
		}
		System.out.println("Number of permutations: " + numPermutations);
		System.out.println("Total Error parameter: " + error);
		System.out.println("Number of pairs for which exact and approximate similarities differ by more then given error value: " + count);
	}
}
