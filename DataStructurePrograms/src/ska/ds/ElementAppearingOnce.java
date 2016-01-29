package ska.ds;


/**
 * @author Shubham (tech.shubham@gmail.com)
 * Print element that occurs once in an array of elements where all other elements occur 2 and 3 times
 */
public class ElementAppearingOnce {

	public static void main(String sr[]){

		//Array with 3 occurrences of each element except one
		int [] arr = {1,7,5,7,7,5,4,5,1,1};
		int res = findInArrayWithThreeOccurancesOfEach(arr);
		System.out.println(res);
		//Array with 2 occurrences of each element except one		
		int [] arr1 = {9,5,7,7,5,4,4,1,1};
		res = findInArrayWithTwoOccurancesOfEach(arr1);
		System.out.println(res);
		
	}

	private static int findInArrayWithTwoOccurancesOfEach(int[] arr) {
		
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			res = res^arr[i];
		}
		return res;
	}

	private static int findInArrayWithThreeOccurancesOfEach(int[] arr) {
		int ones = 0, twos = 0 ;

		int common_bit_mask;
		for (int i = 0; i < arr.length; i++) {
			twos  = twos | (ones & arr[i]);
			ones  = ones ^ arr[i];
			common_bit_mask = ~(ones & twos);
			ones &= common_bit_mask;
			twos &= common_bit_mask;
		}
		return ones;
	}
}
