package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * https://leetcode.com/problems/maximum-subarray/
 * Medium
 */

public class MaximumSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(nums));
		
	}


	public static int maxSubArray(int[] nums) {

		if(nums.length == 1){
            return nums[0];
        }
        int maxSum = 0;
        int sum = 0;
        int start = 0;
        int end = 0;
        int temp = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=0 && temp <= nums[i]){
                 temp = nums[i];
            }
            sum += nums[i];
            if(sum<=0){
                start = i+1;
                sum = 0;
            }
            
            if(sum > maxSum ){
                maxSum = sum;
                end = i;
            }
            
        }    
        
        if(maxSum <= 0){
            maxSum = temp;
        }
        return maxSum;
	}

}
