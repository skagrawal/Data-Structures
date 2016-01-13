/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given an integer, write a function to determine if it is a power of two.
 * https://leetcode.com/problems/power-of-two/
 */
public class IsPowerOfTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		System.out.println("16 is power of 2 -->"+isPowerOfTwo(16));
		System.out.println("10 is power of 2 -->"+isPowerOfTwo(10));
	}

	// Doing bitwise with n and n-1 e.g. 4 and 3
	public static boolean isPowerOfTwo(int n) {
        if(n == 0 || n == Integer.MIN_VALUE) return false;
        int n1 = n&(n-1);
        if(n1 == 0){
            return true;
        }else{
            return false;
        }
    }
}
