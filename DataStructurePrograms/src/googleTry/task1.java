package googleTry;

import java.util.Random;

public class task1 {

	public static void main(String ar[]) {
		Random rn = new Random();
		
		//System.out.println(rn.nextInt(4 + 1));
		int X;
		for (int i = 2; i < 80; i++) {
			X = rn.nextInt(Integer.MAX_VALUE);
			//System.out.println(X);
			System.out.println(allNum(X) - oneNum(X));
			
		}
		
		
		
		//allNum(12511);
		
		//oneNum(12511);
		
	}
	
	public static long oneNum(long x) {
		char[] chars = Long.toString(x).toCharArray();
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			if(chars[i] > chars[i+1]){
				index = i;
				break;
			}
		}
		
		return addDigit(x, index);
	}
	
	
	public static long allNum(long x) {
		long sum = 0;
		long max = 0;
		char[] chars = Long.toString(x).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			for(int k = 0; k < i; k++){
				sum *= 10;
				sum = sum + (chars[k] - '0');
			}
			sum *= 10;
			sum = sum + (chars[i] - '0');
			for(int j = i; j < chars.length; j++){
				sum *= 10;
				sum = sum + (chars[j] - '0');
			}
			if(sum> max){
				max = sum;
			}
			//System.out.println(sum);
			sum = 0;
		}
		return max;
	}
	
	
	private static long addDigit(long x,int ind) {
		StringBuilder sb = new StringBuilder();
		char[] chars = Long.toString(x).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			
			sb.append(chars[i]);
			if(i == ind){
				sb.append(chars[i]);
			}
		}
		return Long.parseLong(sb.toString());
		
	}
	
}
