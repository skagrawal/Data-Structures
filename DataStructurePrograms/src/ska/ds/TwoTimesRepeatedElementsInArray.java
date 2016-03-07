/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
public class TwoTimesRepeatedElementsInArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int [] arr = {1,1,2,2,4,5,6,7,9,9,8};
		findRepeated(arr);
	}

	private static void findRepeated(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			if(arr[Math.abs(arr[i])] >= 0){
				arr[Math.abs(arr[i])] *= -1;
			}else{
				System.out.println(Math.abs(arr[i]));
			}
		}
		
		
	}

	
	
}
