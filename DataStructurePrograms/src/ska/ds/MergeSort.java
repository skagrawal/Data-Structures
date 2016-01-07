/**
 * 
 */
package ska.ds;

/**
 * @author Shubham
 * Merge Sort
 */
public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] arr = {2,4,5,1,2,3,6,7};
		int[] mergeSort = mergeSort(arr);


		for (int i = 0; i < mergeSort.length; i++) {
			System.out.println(mergeSort[i]);
		}
	}

	private static int[] mergeSort(int[] arr) {
		if (arr.length <= 1) {
            return arr;
        }

		int mid = arr.length/2;
		int[] first = new int[mid];
		int[] second = new int[arr.length - first.length];
		System.arraycopy(arr, 0, first, 0, first.length);
		System.arraycopy(arr, mid, second, 0, second.length);
		mergeSort(first);
		mergeSort(second);
		merge(first,second,arr);

		return arr;

	}

	private static int[] merge(int[] first, int[] second, int[] arr) {

		int i = 0,j = 0, k=0;
		while(i< first.length && j< second.length){
			if(first[i]<second[j]){
				arr[k++] = first[i];
				i++;
			}
			else{
				arr[k++] = second[j];
				j++;
			}
		}

		while(i< first.length){
			arr[k++] = first[i++];
		}
		while(j< second.length){
			arr[k++] = second[j++];
		}

		return arr;

	}

}
