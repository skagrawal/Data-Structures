/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Count the number of prime numbers less than a non-negative number, n.
 * Implementing "Sieve of Eratosthenes"
 * https://leetcode.com/problems/count-primes/
 */
public class CountPrimes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(countPrimes(100));
		System.out.println(countPrimes(10));
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	static int countPrimes(int n) {

		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true;
		}

		for (int i = 2; i * i < n; i++) {
			if (!isPrime[i]) continue;
			for (int j = i * i; j < n; j += i) {
				isPrime[j] = false;
			}
		}

		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) count++;
		}
		return count;
	}
}
