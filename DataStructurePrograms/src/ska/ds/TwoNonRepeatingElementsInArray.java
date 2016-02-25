package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
 * Find the two elements that appear only once. For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * https://leetcode.com/problems/single-number-iii/
 */
public class TwoNonRepeatingElementsInArray {

	public static void main(String[] args) {

		int arr1[] = {2, 3, 7, 9, 11, 2, 3, 11};
		singleNumber(arr1);
		int arr2[] = {1, 2, 1, 3, 2, 5};
		singleNumber(arr2);
	}

	static int[] singleNumber(int[] nums) {
        
        int oldRes = nums[0];
        for(int i=1;i<nums.length;i++){
            oldRes ^= nums[i];
        }
        //Find the rightmost set bit
        oldRes = (oldRes & ~(oldRes-1));
        
        int x = 0;
        int y = 0;
        
        //Divide the array based on the number which as set bit equal to oldRes
        for(int i=0;i<nums.length;i++){
            if((oldRes & nums[i]) == 0){
                x ^= nums[i];
            }else{
                y ^= nums[i];
            }
        }
        //Printing non repeated pair
        System.out.println(x + " and "+y);
		return new int[]{x,y};
    }
}
