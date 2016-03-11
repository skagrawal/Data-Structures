/**
 * 
 */
package ska.ds;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * This uses Priority queue for max heap
 */
public class KthLargestElementinanArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int [] arr = {1,2,3,4,567,65756,768,98787,3453,234,5};
		int res = findKthLargestUsingMinHeap(arr, 1);
		System.out.println(res);

	}

	/**
	 * This uses Priority queue for max heap
	 * @param nums
	 * @param k
	 * @return result
	 */
	static int findKthLargestUsingMaxHeap(int[] nums, int k) {

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(nums.length,Collections.reverseOrder());

		for (int i = 0; i < nums.length; i++) {
			maxHeap.add(nums[i]);
		}

		int result = -1;
		for (int i = 0; i < k; i++) {
			result = maxHeap.poll();
		}

		return result;
	}

	/**
	 * This uses Priority queue for min heap
	 * @param nums
	 * @param k
	 * @return
	 */
	static int findKthLargestUsingMinHeap(int[] nums, int k) {

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(nums.length);

		for (int i = 0; i < nums.length; i++) {
			if(minHeap.size()<k){
				minHeap.add(nums[i]);
			}
			else{
				if(minHeap.peek()<nums[i]){
					minHeap.poll();
					minHeap.add(nums[i]);
				}
			}

		}
		return minHeap.peek();
	}

}
