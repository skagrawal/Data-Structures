/**
 * 
 */
package ska.ds;

import java.util.LinkedList;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * https://leetcode.com/problems/perfect-squares/
 */
public class LeastNumberOfPerfectSsquare {

	public static void main(String[] args) {
		System.out.println(numSquares(13));
		System.out.println(numSquares(25));
		System.out.println(numSquares(12));
	}

	
	/**
	 * 
	 * @param n
	 * @return min value
	 */
	static int numSquares(int n) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        //create list of all squares
        for(int i = 1; i*i<= n; i++){
            list.add(i*i);
        }
        
        int [] min = new int[n +1];
		for (int i = 1; i <= n; i++) {
			min[i] = Integer.MAX_VALUE - 1;
		}
        
        for(int i=1;i<=n;i++){
            for(int val : list){
                if(val> n){
                    break;
                }
                if(i >= val){
                    min[i] = Math.min(min[i-val]+1,min[i]);
                }
            }
        }
        if(min[n] == Integer.MAX_VALUE - 1){
			return -1;
		}
		return min[n];
    }
}
