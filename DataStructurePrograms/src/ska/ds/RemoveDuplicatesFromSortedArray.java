/**
 * 
 */
package ska.ds;

import java.util.Arrays;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int [] arr = {1,1,2,3,5,7,7,9,10};
		int x = removeDuplicates(arr);
		for (int i = 0; i < x; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	
	static int removeDuplicates(int[] nums) {
        if(nums.length <= 1){
            return nums.length;
        }
        
        int temp = nums[0];
        int i = 1;
        int ind = 1;
        boolean flag = false;
        for(; i< nums.length; i++){
             if(temp != nums[i]){
                 nums[ind] = nums[i];
                 ind++;
             }else{
                 if(flag){
                     ind++;
                     flag = false;
                 }
             }
            temp = nums[i];
        }
        return ind;
    }
}
