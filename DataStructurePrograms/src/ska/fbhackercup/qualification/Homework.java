/**
 * 
 */
package ska.fbhackercup.qualification;

import java.util.ArrayList;

/**
 * @author Shubham
 *
 */
public class Homework {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		int low = 5,high = 15;
		
		getAllPrimes((int) Math.pow(10, 7));
		//int [] res = getAllPrimeWithinRange(low,high);

		//		for (int i = 0; i < res.length; i++) {
		//			//System.out.println(i +" -- "+res[i+low]);
		//		}
		//		
		
	}

	private static int[] getAllPrimeWithinRange(int low, int high) {

		int [] arr = new int [high-low];

		for (int i = low; i < high; i++) {
			arr [i-low] = getCount(i);
		}


		return arr;
	}

	private static int getCount(int x) {

		int count = 0;
		for (int i = 2; i < x/2+1; i++) {
			if(x%i ==0)
				count++;
		}

		System.out.println(x +" -- "+count);
		return count;
	}


	private static ArrayList<Integer> getAllPrimes(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=2; i< max;i++){
			int counter=0; 		
			for(int num =i/2; num>1; num--){
				if(i%num==0)
					counter++;
			}
			if(counter ==0){
				System.out.println(i);
				list.add(i);
			}
		}
		
		return list;
	}

}
