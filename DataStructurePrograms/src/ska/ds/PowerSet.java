/**
 * 
 */
package ska.ds;

/**
 * @author Shubham(tech.shubham@gmail.com)
 * Printing power set of given array
 * Total elements = 2^n
 */
public class PowerSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = {1,2,3,4};
		getPowerSet(arr);
	}

	private static void getPowerSet(int[] arr) {
		// TODO Auto-generated method stub
		int len = arr.length;
		int total = 1<<len;
		for (int i = 0; i < total; i++) {
			
			for (int j = 0; j < len; j++) {
				//Printing all elements for which there is a bit one in i
				if((i & (1<<j)) != 0){
					System.out.print(arr[j]);
				}
			}
			System.out.print("\n");
		}
		
	}

}
