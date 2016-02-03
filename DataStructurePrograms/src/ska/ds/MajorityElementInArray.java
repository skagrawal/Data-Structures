/**
 * 
 */
package ska.ds;

import java.util.HashMap;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given an array of size n, find the majority element. 
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * https://leetcode.com/problems/majority-element/
 */
public class MajorityElementInArray {

	public static void main(String[] args) {

		int [] arr = {10,10,20,20,20,10,10};
		System.out.println(majorityElement(arr));
	}


	public static int majorityElement(int[] nums) {

		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

		int len = nums.length;
		if(len == 1){
			return nums[0];
		}

		for(int i = 0; i< len; i++){
			if(map.containsKey(nums[i])){
				int ct = map.get(nums[i]);
				if(ct >= len/2){
					return nums[i];
				}
				map.put(nums[i], ++ct);
			}else{
				map.put(nums[i], 1);
			}
		}

		return -1;
	}
}
