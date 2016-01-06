package ska.ds;
import java.util.HashSet;
import java.util.Set;

public class RemovalRepeatArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[] set = new boolean[1001]; //values must default to false
		int totalItems = 0;
		
		int[] arr = new int[]{4,1, 1, 4, 7, 7, 8, 9, 9, 9, 10};
		for( int i = 0; i < arr.length; ++i ) {
			if( set[arr[i]] == false ) {
				set[arr[i]] = true;
				totalItems++;
			}
		}

		int[] ret = new int[totalItems];
		int c = 0;
		for( int i = 0; i < set.length; ++i ) {
			if( set[i] == true ) {
				System.out.println(i);
			}
		}
		
		Set<Integer> hs = new HashSet<Integer>();

		for(int i = 0; i < arr.length; i++){
		  hs.add(arr[i]);
		}
		for(int i : hs)
			System.out.println(i);
		
	}

}
