/**
 * @author Le Zhang and Sayantani Ghosh
 */
package test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tests how accurately MinHash matrix can be used to estimate Jaccard similarity.
 */
public class MinHashAccuracy {
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args){
		String folder;
		int numPermutations;
		double error;
		//input parameters
		Scanner s = new Scanner(System.in);
//		System.out.println("Input folder directory: ");
//		folder = s.nextLine();
//		System.out.println("Input # of permutations: ");
//		numPermutations = s.nextInt();
//		System.out.println("Input error parameter: ");
//		error = s.nextDouble();
//		
		
		folder = "/Users/Shubham/Documents/space";
		numPermutations = 1000;
		error = 0.9;
		
		MinHash min = new MinHash(folder,numPermutations);
		String[] list= min.getFileList();
		//get all the exact similarities 
		ArrayList<Double> approx = new ArrayList<Double>();
		ArrayList<Double> exact = new ArrayList<Double>();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				exact.add(min.exactJaccard(list[i], list[j]));
			}
		}
		
		//get approximate similarities
		int[][] matrix = min.minHashMatrix();
		for(int i=0; i<list.length-1; i++){
			for(int j=i+1; j<list.length; j++){
				int l = 0;
				for(int p=0; p<numPermutations; p++){
					if(matrix[p][i] == matrix[p][j]){
						l++;
					}
				}
				approx.add(l*1.0/numPermutations);
			}
		}
		int count=0;
		for(int i=0; i<approx.size(); i++){
			double diff = approx.get(i)-exact.get(i);
			if(diff < -error ||diff > error){
				count++;
			}
		}
		s.close();
		System.out.println("# permutations: \t" + numPermutations);
		System.out.println("Error parameter: \t" + error);
		System.out.println("# Error results : \t" + count);
		System.out.println("# total pairs: \t\t" + approx.size());
	}
}
