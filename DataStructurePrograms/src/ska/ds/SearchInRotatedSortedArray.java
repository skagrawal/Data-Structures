/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Time - O(LOG N) Space - O(1)
 */
public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		int [] arr = {3,4,7,8,1,2};
		System.out.println(search(arr,7));
	}

	static int search(int[] nums, int target) {
		if(nums.length == 0) return -1;
		int start = 0;
		int end = nums.length -1;
		while(start<=end){
			int mid = start + (end-start)/2;
			if(nums[mid] == target){
				return mid;
			}
			//First half sorted
			if(nums[start]<=nums[mid]){
				if(nums[start] <= target && target < nums[mid]){
					end = mid-1;
				}else{
					start = mid+1;
				}
			}
			//Second half sorted
			else{
				if(nums[mid] < target && target <= nums[end]){
					start = mid+1;
				}else{
					end = mid-1;
				}
			}
		}

		return -1;
	}
}
