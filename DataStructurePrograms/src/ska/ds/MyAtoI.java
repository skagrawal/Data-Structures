/**
 * 
 */
package ska.ds;

/**
 * @author Shubham
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class MyAtoI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(myAtoi("353455"));
		System.out.println(myAtoi(""+ Integer.MAX_VALUE+100));
	}
	
	public static int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0) return 0;
        int sum = 0;
		char c= ' ';
		int i=0;
		boolean isNegative = false;
		boolean isPositive = false;
		int count = 0;
		while(i < str.length()){
		    if(str.charAt(i) == '-') {
    		    isNegative = true;
    		    i++;
    		    count++;
		    }
		    else if(str.charAt(i) == '+'){ 
		        isPositive = true;
		        i++;
		        count++;
		    }else{
		        break;
		    }
		}
		
		int val = 0;
		for(;i< str.length();i++){
			c = str.charAt(i);
			val = c-'0';
			if(0 > val || val > 9){
			    break;
			}
			if(sum <= (int)Integer.MAX_VALUE/10){
			    sum *= 10;
			}else{
			    if(isNegative){
		            return Integer.MIN_VALUE;
		        }
			    return Integer.MAX_VALUE;
			}
			if(sum <= (int)Integer.MAX_VALUE - val){
			    sum += (val);
			}else{
			    if(isNegative){
		            return Integer.MIN_VALUE;
		        }
			    return Integer.MAX_VALUE;
			}
		}
		if(count>1){
		    return 0;
		}
		
		if(isNegative && isPositive){
		    return 0;
		}
		
		if(isNegative){
		    return 0-sum;
		}
		
		return sum;
    }

}
