package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a string, find the length of the longest substring without repeating characters. 
 * "abcabcbb"  --> 3, "bbbbbb" --> 1
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingChar {

	public static void main(String[] args) {

		int x = lengthOfLongestSubstring("ababasdasdsadasdasd");
		System.out.println(x);
	}

	// If repeated character found set false for all the characters till first occurrence of repeated char.
	static int lengthOfLongestSubstring(String s) {

		boolean exist[] = new boolean[255];
		int len = s.length();
		int i = 0, j = 0;
		int maxLen = 0;
		while(j<len){
			int x = s.charAt(j) - ' ';
			if(exist[x]){
				maxLen = Math.max(maxLen, j-i);
				while(s.charAt(i) != s.charAt(j)){
					x = s.charAt(i) - ' ';
					exist[x] = false;
					i++;
				}
				i++;
				j++;
			}else{
				exist[x] = true;
				j++;
			}

		}
		maxLen = Math.max(maxLen, j-i);
		return maxLen;
	}
}
