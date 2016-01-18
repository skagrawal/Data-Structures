/**
 * 
 */
package ska.ds;

/**
 * @author Shubham
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * https://leetcode.com/problems/rotate-array/
 * Time complexity = O(N)
 * Space complexity = O(1)
 */
public class RotateArrayInPlace {

	public static void main(String[] args) {

		int[] arr = {1,2,3,24,11,12,13,14};
		System.out.println("Original Array");
		for(int i : arr){
			System.out.print(i + " ");
		}
		int k = 4;
		int[] rotate = rotate(arr, k);
		System.out.println("\n\nRotated Array by '"+ k+"'");
		for(int i : rotate){
			System.out.print(i + " ");
		}
		
	}
	public static int[] rotate(int[] nums, int k) {
		
		//If k is more than length of array then it take modulus to make k in range.
		if(nums.length <= k){
            k = k%nums.length;
        }
		
		//It simply do 3 reverse operation. 1st for whole array, 2nd for array before k and 3rd for remaining.
		nums = reverse(nums,0,nums.length - 1);
		nums = reverse(nums,0,k-1);
		nums = reverse(nums,k,nums.length - 1);

		return nums;
	}

	//Simple reverse using temp variable.
	public static int[] reverse(int[] nums, int start , int end){
		int temp = 0;
		while(start<end){
			temp = nums[start];
			nums[start++] = nums[end];
			nums[end--] = temp;
		}
		return nums;
	}
}
