package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * https://leetcode.com/problems/single-number/
 */
public class SingleNonDuplicateNumber {

	public static void main(String[] args) {

		int [] arr = new int[21];
		for (int i = 0; i < arr.length-1; i++) {
			arr[i] = i;
			arr[i+1] = i;
		}
		arr[20] = 100;
		System.out.println(singleNumber(arr));
	}

	
	private static int singleNumber(int[] nums) {
		if(nums.length == 0) return -1;
		if(nums.length == 1) return nums[0];

		int x = nums[0];
		int len = nums.length;
		//Taking XOR, all repeated elements will cancel out each other
		for(int i = 1; i< len; i++){
			x ^= nums[i];
		}
		return x;
	}
}
