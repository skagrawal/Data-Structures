/**
 * @author Le Zhang and Sayantani Ghosh
 */
package pa2;

import java.util.ArrayList;
import java.util.List;

/**
 * Puts together MinHash and LSH to detect near duplicates of a document
 */
public class NearDuplicates {
	
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args){
		if(args.length != 5){
			System.err.println(getUsage());
			return;
		}else{
			//Let's do some hashing.
		}
		
		String dirName = args[0];
		String docName = args[4];
		int k = -1;
		int b = -1;
		double s = -1.0;
		try {
			k = Integer.parseInt(args[1]);
			b = Integer.parseInt(args[2]);
			s = Double.parseDouble(args[3]);
		} catch (NumberFormatException e) {
			System.err.println(getUsage());
			System.err.println("Illegal Argument passed for "
					+ "Number of Permutations or Number of bands or "
					+ "Similarity Threshold. Expected an integer for number of permutations and "
					+ "number of bands and a double value for similarity threshold.\n"
					+ "\tError Message : " +e.getMessage());
			return;
		}
		
		MinHash objMinHash = new MinHash(dirName, k);
		int[][] minHashMat = objMinHash.minHashMatrix();
		LSH objLSH = new LSH(minHashMat, objMinHash.getFileList(), b);
		List<String> lstNearDuplicates = objLSH.nearDuplicatesOf(docName);
		
		//Removing False positives
		List<String> finalList = new ArrayList<String>();
		for(String str : lstNearDuplicates){
			if ( objMinHash.approximateJaccard(str, docName) >= s){
				finalList.add(str);
			}
		}
		System.out.println("Documents similar to : " +docName);
		for(String str : finalList){
			System.out.println(str);
		}
	}
	
	
	private static String getUsage(){
		return "USAGE : NearDuplicates <Directory Name> <Num of Permutations> <Num of Bands> <Similarity Threshold> <Document Name>\n"
				+ "\tDirectory Name : Name of folder containing documents\n"
				+ "\tNum of Permutations : Number of permutations to be used for min hash\n"
				+ "\tNum of Bands : Number of bands to be used for locality sensitive hashing\n"
				+ "\tSimilarity Threshold : Similarity Threshold\n"
				+ "\tDocument Name : Name of a document from the collection";
	}

}
