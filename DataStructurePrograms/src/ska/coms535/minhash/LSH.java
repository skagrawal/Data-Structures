/**
 * 
 */
package ska.coms535.minhash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Shubham Agrawal And Swatie Bansal
 */
public class LSH {

	private HashSet<String>[][] T;


	/**
	 * 
	 * @param minHashMatrix
	 * @param docNames
	 * @param bands
	 */
	public LSH(int[][] minHashMatrix, String[] docNames, int bands)
	{
		int n = docNames.length;
		T = new HashSet[bands][n];
		int len = minHashMatrix.length;
		System.out.println(len+" " +minHashMatrix[0].length );
		int r = (int) len/bands;
		for (int index= 0; index < len; index++){
			for(int b = 0; b < bands; b++){
				int hashValue = 0;
				for(int i = 0; i < r ; i++){
					hashValue += minHashMatrix[b*r + i][index];
				}
				int t = hashValue % n;
				if(T[b][t] == null){
					T[b][t] = new HashSet<String>();
				}
				T[b][t].add(docNames[index]);
			}
		}
	}

	public List<String> nearDuplciatesOf(String docName)
	{
		HashSet<Integer> bins = new HashSet<Integer>();
		HashSet<String> set = new HashSet<String>();

		for(int i = 0 ; i < T.length; i++){
			for(int j = 0;  j < T[i].length; j++){
				if(T[i][j] != null && T[i][j].contains(docName)){
					bins.add(i);
				}
			}
		}

		for(int bin : bins){
			for(int j =0 ; j < T[bin].length; j++){
				HashSet<String> name = T[bin][j];
				if(name != null && !docName.equals(name) && !name.isEmpty() ){
					set.addAll(name);
				}
			}
		}
		
		String[] arrList = set.toArray(new String[set.size()]);
		return Arrays.asList(arrList);
	}

}
