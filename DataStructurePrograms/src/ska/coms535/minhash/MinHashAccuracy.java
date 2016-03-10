/**
 * 
 */
package ska.coms535.minhash;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Shubham and Swati
 *
 */
public class MinHashAccuracy {

	
	public static void main(String[] args){
		
		String folder;
		int numPermutations;
		double error = 0.90;
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Folder directory - ");
//		folder = sc.nextLine();
//		System.out.println("Enter Number of permutations - ");
//		numPermutations = sc.nextInt();
//		System.out.println("Input error parameter: ");
//		error = s.nextDouble();
		folder = "/Users/Shubham/Documents/space";
		numPermutations = 100;
		MinHash min = new MinHash(folder,numPermutations);
		String[] list= min.allDocs();
		ArrayList<Double> exactJaccardSim = new ArrayList<Double>();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				exactJaccardSim.add(min.exactJaccard(list[i], list[j]));
			}
		}
		
		ArrayList<Double> approxJaccardSim = new ArrayList<Double>();
		int[][] matrix = min.minHashMatrix();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				int l = 0;
				for(int p=0; p<numPermutations; p++){
					if(matrix[p][i] == matrix[p][j]){
						l++;
					}
				}
				approxJaccardSim.add(l*1.0/numPermutations);
			}
		}
		int count = 0;
		
		for(int i=0; i<approxJaccardSim.size(); i++){
			double diff = approxJaccardSim.get(i)-exactJaccardSim.get(i);
			if(diff < -error ||diff > error){
				count++;
			}
		}
		//sc.close();
		System.out.println("Number of permutations: " + numPermutations);
		System.out.println("Total Error parameter: " + error);
		System.out.println("Number of pairs for which exact and approximate similarities differ by more then given error value: " + count);
		System.out.println("Number of total pairs: " + approxJaccardSim.size());
	}
}
