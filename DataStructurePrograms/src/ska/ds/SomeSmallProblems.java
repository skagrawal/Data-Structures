package ska.ds;
/**
 * @author Shubham (tech.shubham@gmail.com)
 *  
 */ 

public class SomeSmallProblems {

	public static void main(String[] args) {

		int[] arr1 = {1,2,3,24,11,12,13,14};
		int[] arr2 = {11,12,13,14};

		
	}

	public static int makeChange(int n, int denom) {
		int next_denom = 0;
		switch(denom){
		case 25:
			next_denom= 10;
			break; 
		case 10:
			next_denom = 5;
			break; 
		case 5:
			next_denom = 1;
			break;
		case 1:
			return 1;
		}
		int ways = 0;
		for(int i =0; i *denom <=n; i++) {
			//System.out.print(next_denom);
			ways  = ways + makeChange(n - i*denom, next_denom);
			//System.out.println();
		}
		return ways; 
	}



	public static void solve(int n, String start, String auxiliary, String end) {
		if (n == 1) {
			System.out.println(start + " -> " + end);
		} else {
			solve(n - 1, start, end, auxiliary);
			System.out.println(start + " -> " + end);
			solve(n - 1, auxiliary, start, end);
		}
	}

}

