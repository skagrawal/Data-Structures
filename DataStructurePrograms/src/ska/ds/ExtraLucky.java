package ska.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExtraLucky {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int numTestCases = 0;
	    numTestCases = Integer.parseInt(sc.nextLine());
	    for(int testCase = 0; testCase < numTestCases; testCase++){
	        int num = Integer.parseInt(sc.nextLine());
	        int count = 0;
	        for(int n : getFactors(num)){
	            if(isExtraLucky(n)){
	                count++;
	            }
	        }
	        System.out.println(count);
	    }
	    sc.close();

	    }
	    private static boolean isExtraLucky(int n){
	        String str = "" +n;
	        if(str.contains("3") || str.contains("5")){
	            return true;
	        }else{
	            return false;
	        }
	    }

	    private static List<Integer> getFactors(int n){
	        List<Integer> retList = new ArrayList<Integer>();
	        for(int i = 1; i <= Math.sqrt(n) ; i++){
	            if(n % i == 0){
	                retList.add(i);
	                retList.add(n / i);
	            }
	        }
	        return retList;
	    }
}
