/**
 * 
 */
package ska.ds;

import java.util.HashMap;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * https://leetcode.com/problems/two-sum/
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 */
public class TwoSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = {3,2,4};
		int[] res = twoSum(arr,6);
		System.out.println(res[0]+ " " +res[1]);

	}

	public static int[] twoSum(int[] nums, int target) {

		HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
		for(int i = 0; i < nums.length; i++){

			int dif = target - nums[i];
			if(hash.containsKey(dif)){
				int [] ret = {hash.get(dif)+1, i+1};
				return ret;
			}else{
				hash.put(nums[i],i);
			}
		}
		return null;
	}

}
