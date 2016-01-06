package ska.ds;

public class InsertionSort {

	public static void main(String[] args) {

		int [] arr = {2,5,3,45,7,8,1};

		arr = doBubbleSort(arr);
		for(int i = 0; i< arr.length; i++){
			System.out.println(arr[i]);
		}

	}

	private static int[] doInsertionSort(int[] arr) {
		// TODO Auto-generated method stub
		int j = 0;
		int temp = 0;
		for(int i=0;i<arr.length;i++){
			j = i;
			while(j>0 && arr[j]<arr[j-1]){
				//Swap two elements
				temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp; 
				//Reduce j to check for previous element
				j = j-1;
			}

		}
		return arr;
	}


	private static int[] doBubbleSort(int[] arr) {
		// TODO Auto-generated method stub
		int j = 0;
		int temp = 0;
		int len = arr.length;
		for(int i=0;i<len;i++){
			for(j = 1; j<len-i;j++){
				if(arr[j]<arr[j-1]){
					//Swap two elements
					temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp; 
				}
			}

		}
		return arr;
	}
}
