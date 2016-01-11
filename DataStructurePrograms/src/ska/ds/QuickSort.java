/**
 * 
 */
package ska.ds;


/**
 * @author Shubham (tech.shubham@gmail.com)
 * Quick sort implementation
 */
public class QuickSort {

	public static void main(String[] args) {

		int [] arr = {2,4,5,1,2,3,6,7};
		int[] sorted = quickSort(arr, 0 , arr.length-1);


		for (int i = 0; i < sorted.length; i++) {
			System.out.println(sorted[i]);
		}
	}

	private static int[] quickSort(int[] arr, int start, int end) {

		if (arr == null || arr.length == 0)
			return arr;
		
		if(start >= end){
			return arr;
		}
		
		int pivot = (start+end)/2;
		int i = start;
		int j = end;
		while(i <= j){
			while(arr[i]< arr[pivot]){
				i++;
			}
			while(arr[j]> arr[pivot]){
				j--;
			}
			if(i<=j){
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		// recursively sort two sub parts
		if (start < j)
			quickSort(arr, start, j);
		if (end > i)
			quickSort(arr, i, end);

		return arr;
	}
}
