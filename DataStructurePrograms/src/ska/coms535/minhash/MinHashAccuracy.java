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


	public static void main(String[] args){

		String folder;
		int numPermutations;
		double error = 0.02;
		//		Scanner sc = new Scanner(System.in);
		//		System.out.println("Enter Folder directory - ");
		//		folder = sc.nextLine();
		//		System.out.println("Enter Number of permutations - ");
		//		numPermutations = sc.nextInt();
		//		System.out.println("Input error parameter: ");
		//		error = s.nextDouble();
		folder = "/Users/Shubham/Documents/space";
		numPermutations = 600;
		MinHash minHash = new MinHash(folder,numPermutations);
		String[] list= minHash.allDocs();
		ArrayList<Double> exactJaccardSim = new ArrayList<Double>();
		int count = 0;
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				exactJaccardSim.add(minHash.exactJaccard(list[i], list[j]));

				double exact = minHash.exactJaccard(list[i], list[j]);
				double aprox = minHash.approximateJaccard(list[i], list[j]);
				double diff = exact-aprox;
				if(diff < -error ||diff > error){
					count++;
				}
					
			}
		}

//		ArrayList<Double> approxJaccardSim = new ArrayList<Double>();
//		int[][] matrix = minHash.minHashMatrix();
//		for(int i=0; i<list.length-1; i++){
//			for(int j=i+1; j<list.length; j++){
//				int l = 0;
//				for(int p=0; p<numPermutations; p++){
//					if(matrix[p][i] == matrix[p][j]){
//						l++;
//					}
//				}
//				approxJaccardSim.add(l*1.0/numPermutations);
//			}
//		}
//		int count = 0;
//
//		for(int i=0; i<approxJaccardSim.size(); i++){
//			double diff = approxJaccardSim.get(i)-exactJaccardSim.get(i);
//			if(diff < -error ||diff > error){
//				count++;
//			}
//		}
		//sc.close();
		System.out.println("Number of permutations: " + numPermutations);
		System.out.println("Total Error parameter: " + error);
		System.out.println("Number of pairs for which exact and approximate similarities differ by more then given error value: " + count);
//		System.out.println("Number of total pairs: " + approxJaccardSim.size());
	}
}
