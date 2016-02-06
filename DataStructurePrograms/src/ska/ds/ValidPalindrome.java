package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * https://leetcode.com/problems/valid-palindrome/
 */

public class ValidPalindrome {

	public static void main(String[] args) {

		String str = "A man, a plan, a canal: Panama";
		System.out.println(isValidPalindrome(str));

	}

	private static boolean isValidPalindrome(String s) {
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		if(s.length() == 1) return true;

		char [] arr = s.toCharArray();
		int j = arr.length-1;

		for(int i = 0;i < j;){
			if(arr[i++] != arr[j--]){
				return false;
			}
		}
		return true;		
	}

}
