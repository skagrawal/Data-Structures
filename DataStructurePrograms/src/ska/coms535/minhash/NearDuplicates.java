/**
 * 
 */
package ska.coms535.minhash;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Shubham Agrawal And Swatie Bansal
 */
public class NearDuplicates {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.out.println("In Near Duplicates");
		String dirName = "/Users/Shubham/Documents/pa2"; 
		String docName = "space-0.txt.copy1";
		int numPermutations = 100;
		int numBands = 20;
		double simThreshold = .9;
		
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Name of the folder containing documents - ");
//		dirName = sc.nextLine();
//		System.out.println("Number of Permutations to be used for MinHash - ");
//		k = sc.nextInt();
//		System.out.println("Number of Bands to be used in locality sensitive hashing - ");
//		b = sc.nextInt();
//		System.out.println("Similarity threshold s - ");
//		s = sc.nextDouble();
//		System.out.println("Name of a document from the collection, docName. - ");
//		docName = sc.nextLine();
//		sc.close();
//		
		
		MinHash objMinHash = new MinHash(dirName, numPermutations);
		int[][] minHashMat = objMinHash.minHashMatrix();
		LSH objLSH = new LSH(minHashMat, objMinHash.allDocs(), numBands);
		List<String> listNearDuplicates = objLSH.nearDuplciatesOf(docName);
		
		List<String> result = new ArrayList<String>();
		for(String str : listNearDuplicates){
			if (objMinHash.approximateJaccard(str, docName) >= simThreshold){
				result.add(str);
			}
		}
		
		System.out.println("These documents are similar to : " + docName);
		for(String s1 : result){
			System.out.println(s1);
		}
		
		System.out.println("Number of False positive: "+ (listNearDuplicates.size()-result.size()));
	}
	
}
