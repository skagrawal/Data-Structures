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
		int t = 0;
		int hash = 0;
		int len = minHashMatrix.length;
		System.out.println(len+" " +minHashMatrix[0].length );
		int r = (int) len/bands;
		for (int index= 0; index < len; index++){
			for(int b = 0; b < bands; b++){
				hash = 0;
				for(int i = 0; i < r ; i++){
					hash += minHashMatrix[b*r + i][index];
				}
				t = hash % n;
				if(T[b][t] == null){
					T[b][t] = new HashSet<String>();
				}
				T[b][t].add(docNames[index]);
			}
		}
	}

	/**
	 * 
	 * @param docName
	 * @return List
	 */
	public List<String> nearDuplciatesOf(String docName)
	{
		HashSet<Integer> bins = new HashSet<Integer>();
		HashSet<String> hashSet = new HashSet<String>();

		for(int i = 0 ; i < T.length; i++){
			for(int j = 0;  j < T[i].length; j++){
				if(T[i][j] != null && T[i][j].contains(docName)){
					bins.add(i);
				}
			}
		}

		for(int bin : bins){
			for(int j =0 ; j < T[bin].length; j++){
				HashSet<String> set = T[bin][j];
				if(set != null && !docName.equals(set)){
					hashSet.addAll(set);
				}
			}
		}
		
		return Arrays.asList(hashSet.toArray(new String[hashSet.size()]));
	}

}
