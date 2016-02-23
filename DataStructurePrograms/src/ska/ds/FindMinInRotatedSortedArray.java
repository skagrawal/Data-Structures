package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Time - O(LOG N) Space - O(1)
 */
public class FindMinInRotatedSortedArray {

	public static void main(String[] args) {

		int [] arr = {3,4,7,8,1,2};
		System.out.println(findMin(arr));

	}

	static int findMin(int[] nums) {
		if(nums.length < 1){
			return -1;
		}
		int start = 0; 
		int end = nums.length-1;
		while(nums[start] > nums[end]){
			int mid = start + (end-start)/2;
			if(nums[mid] > nums[end]){
				start = mid+1;
			}else{
				end = mid;
			}
		}
		return nums[start];
	}
}
