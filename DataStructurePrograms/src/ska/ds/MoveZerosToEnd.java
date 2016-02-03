/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given an array nums, write a function to move all 0's to the end of it 
 * while maintaining the relative order of the non-zero elements.
 * https://leetcode.com/problems/move-zeroes/
 */
public class MoveZerosToEnd {
	
	public static void main(String[] args) {
		int [] arr = {0,3,7,0,5,6,9,0,8,0};
		System.out.println(moveZeroes(arr));
	}


	public static int[] moveZeroes(int[] nums) {
		if(nums.length == 1){
			return nums;
		}

		int j = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] != 0){
				nums[j++] = nums[i];
			}
		}

		while(j< nums.length){
			nums[j++] = 0;
		}

		return nums;
	}
}
