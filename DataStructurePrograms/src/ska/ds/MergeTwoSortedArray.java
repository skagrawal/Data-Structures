package ska.ds;

import java.util.Random;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * https://leetcode.com/problems/merge-sorted-array/
 */

public class MergeTwoSortedArray {
	public static void main(String[] args) {

		int[] nums1 = new int[5];
		int[] nums2 = new int [2];
		//Putting 3 values in first array
		nums1[0] = 3;
		nums1[1] = 7;
		nums1[2] = 10;
		//Putting 2 values in second array
		nums2[0] = 1;
		nums2[1] = 50;
		
		merge(nums1, 3, nums2, 2);
		
	}

	
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int end = m+n-1;
        m--;
        n--;
        while(m >= 0 && n >= 0){
			if(nums1[m]<nums2[n]){
				nums1[end--] = nums2[n--]; 
			}
			else{
				nums1[end--] = nums1[m--];
			}
		}
		
		while(n>=0){
		    nums1[end--] = nums2[n--]; 
		}
		//Printing final merged array
		System.out.println("Sorted Array");
		for (int i = 0; i < nums1.length; i++) {
			System.out.println(nums1[i]);
		}
    }
}
