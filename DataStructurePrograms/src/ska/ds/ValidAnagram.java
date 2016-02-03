/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

	public static void main(String[] args) {

		boolean res = isAnagram("APPLE", "ALEPP");
		System.out.println(res);
	}


	/*This create an array and store each occurrence of character in first string and then compare 
	 * if second contains same number of occurrences for each character.
	 */
	
	public static boolean isAnagram(String s, String t) {

		if(s.length() != t.length()) return false;

		int [] arr = new int[128];
		for(int i = 0; i< s.length(); i++){
			// int val = s.charAt(i) - null;
			arr[s.charAt(i)]+= 1; 
		}

		for(int i = 0; i< t.length(); i++){
			// int val = t.charAt(i) - null;
			if(arr[t.charAt(i)]<=0){
				return false;
			}else{
				arr[t.charAt(i)]-= 1; 
			}
		}

		return true;

	}
}
