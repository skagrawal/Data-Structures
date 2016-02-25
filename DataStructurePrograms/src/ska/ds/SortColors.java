package ska.ds;

import java.util.Arrays;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent
 * , with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * https://leetcode.com/problems/sort-colors/
 * Time - O(N), Space - O(1)
 */
public class SortColors {

	public static void main(String[] args) {

		sortColors(new int[]{1,2,1,1,0,2,0,1,1,0,0,0,1,2});
	}
	
	
	static void sortColors(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        //counting all 0s and 1s
        for(int i = 0;i<nums.length;i++){
            if(nums[i]==0){
                count0++;
            } else if(nums[i]==1){
                count1++;
            }
        }
        
        //Set 0, 1 and 2s
        for(int i = 0;i<nums.length;i++){
            if(count0-->0){
                nums[i] = 0;
            }else if(count1-->0){
                nums[i] = 1;
            }else{
                nums[i] = 2;
            }        
        }
        
        System.out.println(Arrays.toString(nums));
    }

}
