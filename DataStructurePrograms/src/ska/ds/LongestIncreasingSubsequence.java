/**
 * 
 */
package ska.ds;

import java.util.LinkedList;

/**
 * @author Shubham
 *
 */
public class LongestIncreasingSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] arr = { 10,9,2,5,3,4};
		
		LinkedList<LinkedList<Integer>> calcLIS = calcLIS(arr);
		
		for(LinkedList<Integer> i : calcLIS){
			System.out.println(i.toString());
		}
	}

	private static LinkedList<LinkedList<Integer>> calcLIS(int[] arr) {
		
		LinkedList<LinkedList<Integer>> lists = new LinkedList<LinkedList<Integer>>();
		
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.add(arr[0]);
		lists.add(0,list);
		
		for (int i = 1; i < arr.length; i++) {
			lists.add(i, new LinkedList<Integer>());
		}
		
		for (int i = 1; i < arr.length; i++) {
			
			for (int j = 0; j < i; j++) {
				if((lists.get(j).getLast() < arr[i]) && (lists.get(i).size() < lists.get(j).size()+1)){
					lists.remove(i);
					lists.add (i, lists.get(j));
				}
				 
			}
			lists.get(i).add(arr[i]);
		}
		return lists;
	}

}
