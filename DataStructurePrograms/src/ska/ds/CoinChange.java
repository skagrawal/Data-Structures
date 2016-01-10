/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Available coins
		int [] coins = {1,2,3,10};
		
		System.out.println("Amount = 5 will use " + findMinCoins(coins, 5) + " coins");
		System.out.println("Amount = 9 will use " + findMinCoins(coins, 9) + " coins");
		System.out.println("Amount = 22 will use " + findMinCoins(coins, 22) + " coins");

	}

	
	public static int findMinCoins( int[] coins,  int amount) {

		int [] minCoins = new int[amount +1];
		for (int i = 1; i <= amount; i++) {
			minCoins[i] = Integer.MAX_VALUE -1;
		}

		for(int i = 1; i<= amount ; i++){
			for (int coin : coins) {
				if (i >= coin) {
					minCoins[i] = Math.min(minCoins[i-coin] +1, minCoins[i]);
				}
			}
		}
		if(minCoins[amount] == Integer.MAX_VALUE - 1){
			return -1;
		}
		return minCoins[amount];

	}

}
