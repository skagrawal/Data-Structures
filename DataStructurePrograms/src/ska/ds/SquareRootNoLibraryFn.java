/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Implementing Square Root without using library method
 */
public class SquareRootNoLibraryFn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(calculateSqrt(9));
		
	}

	private static double calculateSqrt(int num) {
		
		double sqrt = num/2;
		double temp = 0;
		do{
			temp = sqrt;
			sqrt = (temp+num/temp)/2.0;
			
		}while(temp-sqrt != 0);
		return sqrt;
	}

}
