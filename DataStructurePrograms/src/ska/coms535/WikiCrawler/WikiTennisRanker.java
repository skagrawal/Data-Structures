/**
 * 
 */
package ska.coms535.WikiCrawler;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
public class WikiTennisRanker 
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		System.out.println("\n________For epsilon = 0.1________\n");
		PageRank obj = new PageRank("WikiTennisGraph.txt", 0.1f);
		System.out.println("--------Highest Page Rank--------");
		for(String str : obj.topKPageRank(1))
			System.out.println(str +" --> " +obj.pageRankOf(str));
		System.out.println("\n--------Highest In Degree--------");
		for(String str : obj.topKInDegree(1))
			System.out.println(str +" --> " +obj.inDegreeOf(str));
		System.out.println("\n--------Highest Out Degree--------");
		for(String str : obj.topKOutDegree(1))
			System.out.println(str +" --> " +obj.outDegreeOf(str));
		int k=100;
		String[] topKRank = new String[k];
		String[] topKIn = new String[k];
		String[] topKOut = new String[k];
		System.out.println("\n--------Top K Page Rank--------");
		int i = 0;
		for(String str : obj.topKPageRank(k))
		{
			topKRank[i++] = str;
			System.out.println(str +" --> " +obj.pageRankOf(str));
		}
		System.out.println("\n--------Top K In Degree--------");
		i = 0;
		for(String str : obj.topKInDegree(k))
		{
			topKIn[i++] = str;
			System.out.println(str +" --> " +obj.inDegreeOf(str));
		}
		System.out.println("\n--------Top K Out Degree--------");
		i = 0;
		for(String str : obj.topKOutDegree(k))
		{
			topKOut[i++] = str;
			System.out.println(str +" --> " +obj.outDegreeOf(str));
		}
		System.out.println("\n--------Jaccard Similarities--------");
		System.out.println("Jaccard Similarity of topKPageRank and topKInDegree: " + jaccardSimilarity(topKRank, topKIn));
		System.out.println("Jaccard Similarity of topKPageRank and topKOutDegree: " + jaccardSimilarity(topKRank, topKOut));
		System.out.println("Jaccard Similarity of topKOutDegree and topKInDegree: " + jaccardSimilarity(topKOut, topKIn));
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("\n________For epsilon = 0.005________\n");
		obj = new PageRank("WikiTennisGraph.txt", 0.005f);
		System.out.println("--------Highest Page Rank--------");
		for(String str : obj.topKPageRank(1))
			System.out.println(str +" --> " +obj.pageRankOf(str));
		System.out.println("\n--------Highest In Degree--------");
		for(String str : obj.topKInDegree(1))
			System.out.println(str +" --> " +obj.inDegreeOf(str));
		System.out.println("\n--------Highest Out Degree--------");
		for(String str : obj.topKOutDegree(1))
			System.out.println(str +" --> " +obj.outDegreeOf(str));
		System.out.println("\n--------Top K Page Rank--------");
		i = 0;
		for(String str : obj.topKPageRank(k))
		{
			topKRank[i++] = str;
			System.out.println(str +" --> " +obj.pageRankOf(str));
		}
		System.out.println("\n--------Top K In Degree--------");
		i = 0;
		for(String str : obj.topKInDegree(k))
		{
			topKIn[i++] = str;
			System.out.println(str +" --> " +obj.inDegreeOf(str));
		}
		System.out.println("\n--------Top K Out Degree--------");
		i = 0;
		for(String str : obj.topKOutDegree(k))
		{
			topKOut[i++] = str;
			System.out.println(str +" --> " +obj.outDegreeOf(str));
		}
		System.out.println("\n--------Jaccard Similarities--------");
		System.out.println("Jaccard Similarity of topKPageRank and topKInDegree: " + jaccardSimilarity(topKRank, topKIn));
		System.out.println("Jaccard Similarity of topKPageRank and topKOutDegree: " + jaccardSimilarity(topKRank, topKOut));
		System.out.println("Jaccard Similarity of topKOutDegree and topKInDegree: " + jaccardSimilarity(topKOut, topKIn));
	}
	
	/**
	 * Gets two sets as parameters and returns the Jaccard Similarity of the files
	 * @param a[]
	 * @param b[]
	 * @return Jaccard similarity of files
	 */
	public static double jaccardSimilarity(String da[], String db[])
	{
		
		Set<String> objSet = new HashSet<String>();
		for(String str  : da){
			objSet.add(str);
		}
		int intersectionCount = 0;
		for(String str  : db){
			if(objSet.contains(str)){
				intersectionCount++;
				continue;
			}
			else{
				objSet.add(str);
			}
		}
		//System.out.println( (1.0 * intersectionCount) /  objSet.size());
		
		return (1.0 * intersectionCount) /  objSet.size();

	}

}
