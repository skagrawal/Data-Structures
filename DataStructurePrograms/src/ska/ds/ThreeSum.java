/**
 * 
 */
package ska.ds;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * This gives all set of three elements whose sum is k
 */
public class ThreeSum {

	public static void main(String[] args) {
		int [] nums = {0,1,2,3,-4,6,0,-6,-1};
		int k = 5;
		List<List<Integer>> threeSum = threeSum(nums,k);
		for(List<Integer> list : threeSum){
			System.out.println(list);
		}


	}

	public static List<List<Integer>> threeSum(int[] nums,int k) {

		Arrays.sort(nums);
		Set<List<Integer>> lists = new HashSet<List<Integer>>();
		List<Integer> list = new LinkedList<Integer>();
		int len = nums.length;
		int low, high, sum;
		int a,b,c;
		for(int i=0; i < len-2; i++){

			low = i+1;
			high = len-1;
			a = nums[i];
			while(low < high){       
				sum = a +nums[low]+nums[high];       
				if(sum == k) {
					list.add(a);
					list.add(nums[low]);
					list.add(nums[high]);

					lists.add(list);
					list = new LinkedList<Integer>();
					low++;
					high--;
				}else if(sum > 0){
					high--;
				}else{
					low++;
				}
			}
		}
		List<List<Integer>> set = new LinkedList<List<Integer>>();
		set.addAll(lists);
		return set;
	}
}


