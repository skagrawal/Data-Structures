/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given two binary strings, return their sum (also a binary string).
 * https://leetcode.com/problems/add-binary/
 */
public class Add2BinaryNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(addBinary("101010", "11"));
		System.out.println(addBinary("101010", "001100"));

	}


	static String addBinary(String a, String b) {

		int lenA = a.length();
		int lenB = b.length();
		int i = 0;

		StringBuilder sb = new StringBuilder();
		int rem = Math.abs(lenA-lenB);
		while(rem >0){
			sb.append('0');
			rem--;
		}
		if(lenA > lenB){
			sb.append(b);  
			b = sb.toString();
		}else{
			sb.append(a);
			a = sb.toString();
		}

		sb = new StringBuilder();
		char carry = '0';
		i = a.length();
		while(i > 0){
			if(a.charAt(i-1) == b.charAt(i-1)){
				sb.append(carry);
				if(a.charAt(i-1) == '1'){
					carry = '1';
				}else{
					carry = '0';
				}
			}else{
				if(carry == '1'){
					sb.append('0');
					carry = '1';
				}else{
					carry = '0';
					sb.append('1');
				}
			}
			i--;
		}

		if(carry == '1'){
			sb.append(carry);
		}

		sb.reverse();
		return sb.toString();

	}

}
