/**
 * @author Le Zhang and Sayantani Ghosh
 */
package pa2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements locality sensitive hashing to detect detect near duplicates of a document.
 */
public class LSH {
	/*
	 * Matrix T_j : that stores the doc names if the jth band of hash signature of doc hashes to t
	 * then document is stored at arrDocs[j][t]
	 */
	private Set<String>[][] arrDocs;
	
	/**
	 * Constructs an instance of LSH, where docNames is an array of Strings consisting of names 
	 * of documents/files in the document collection, and minHashMatrix is the MinHash matrix of 
	 * the document collection and bands is the number of bands to be used to perform locality sensitive hashing.
	 * @param minHashMatrix
	 * @param docNames
	 * @param bands
	 */
	public LSH(int[][] minHashMatrix, String[] docNames, int bands)
	{
		int n = docNames.length;
		//Allocating memory to bins that would store the doc names according to calculated hash values.
		arrDocs = new HashSet[bands][n];
		// r : number of rows in each band.
		int r = minHashMatrix.length / bands;
		//Looping through all docs
		for (int index= 0; index < minHashMatrix.length; index++){
			//Loop for all bands per document's signature
			for(int b = 0; b < bands; b++){
				//Stores the hash value for each band in document's signature
				int hashValue = 0;
				for(int i = 0; i < r ; i++){
					hashValue += minHashMatrix[b*r + i][index];
				}
				//Using Modular Hashing : h(x) = x mod m
				// n can be taken as prime for better results.
				// But in that case the column size of arrDocs needs to be modified as well.
				int t = hashValue % n;
				//If jth band has hash value of t, then the doc name is stored at T_j[t]
				if(arrDocs[b][t] == null){
					arrDocs[b][t] = new HashSet<String>();
				}
				arrDocs[b][t].add(docNames[index]);
			}
		}
	}
	
	/**
	 * Takes name of a document as a parameter and returns an array list
	 * of names of near duplicate documents.
	 * @param docName
	 * @return array list of names of near duplicate documents
	 */
	public List<String> nearDuplicatesOf(String docName)
	{
		/*
		 * Using set to store the doc names so that they are not duplicated
		 * In the end, we will return the list.
		 */
		Set<String> retSet = new HashSet<String>();
		Set<Integer> bins = new HashSet<Integer>();
		/*
		 * Checking for presence of document name in different bins.
		 * The Array ( arrDocs ) has all the bins along the rows and has the size b x n,
		 * where b : number of bands = number of bins
		 * n = number of documents. [Since hash value is modulo n, the column size would 
		 * never be > n. And If jth band has hash value of t, then the doc name is stored at T_j[t]]
		 * Therefore, looking for all bins containing the docName.
		 */
		//Loop through all the bins
		for(int i = 0 ; i < arrDocs.length; i++){
			//Loop through all the columns, as any column might contain the bin name
			for(int j = 0;  j < arrDocs[i].length; j++){
				if(arrDocs[i][j] != null && arrDocs[i][j].contains(docName)){
					//Storing the index of the bin containing the doc name
					// ith bin contains docName.
					bins.add(i);
				}
			}
		}
		//Looping through all the bins containing docName
		for(int i : bins){
			//Gathering all the other document's names which are there in bin
			// and not same as docName
			for(int j =0 ; j < arrDocs[i].length; j++){
				Set<String> name = arrDocs[i][j];
				if(name != null && !docName.equals(name) && !name.isEmpty() ){
					retSet.addAll(name);
				}
			}
		}
		//Converting the set to an array to convert it to list
		String[] arrList = retSet.toArray(new String[retSet.size()]);
		//converting the array to list
		return Arrays.asList(arrList);
	}
	
	
	

}
