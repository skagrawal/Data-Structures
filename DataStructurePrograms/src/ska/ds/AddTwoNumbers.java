/**
 * 
 */
package ska.ds;

import java.util.Random;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Add two numbers without using any operator
 */
public class AddTwoNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println(add(7,7));
		Random rand = new Random();
		for (int i = 0; i < 50; i++) {
			add(rand.nextInt(100),rand.nextInt(1000));
		}
	}

	private static int add(int x, int y) {
		System.out.print(x+y +" ");
		int carry = 0;
		while (y != 0)
	    {
			//carry
	        carry = x & y;  
	        //sum
	        x = x ^ y; 
	        y = carry << 1;
	    }
		
		System.out.println(x);
	    return x;
	}


}
