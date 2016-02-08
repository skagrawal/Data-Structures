/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbStairs {

	public static void main(String[] args) {

		int res = climbStairs(10);
		System.out.println(res);
	}

	//It uses Dynamic programming and store previous results in an array
	public static int climbStairs(int n) {
		//m is steps upto which person can climb at a time
		int  m = 2;
		if(n <= 2){
			return n;
		}
		int res [] = new int[n];
		res[0] = 1; res[1] = 2;

		for (int i=2; i<n; i++)
		{
			res[i] = 0;
			for (int j=1; j<=m && j<=i; j++){
				res[i] += res[i-j];
			}
		}
		//Returning last value in array
		return res[n-1]; 
	}
}
