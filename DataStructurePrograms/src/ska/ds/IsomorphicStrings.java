/**
 * 
 */
package ska.ds;

import java.util.HashMap;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * https://leetcode.com/problems/isomorphic-strings/
 */
public class IsomorphicStrings {

	public static void main(String[] args) {
		System.out.println(isIsomorphic("dfdgd", "sdffdsf"));
		System.out.println(isIsomorphic("DSS", "ABB"));
	}

	public static boolean isIsomorphic(String s, String t) {

		if(s == null || t == null || s.length() != t.length() ){
			return false;
		}

		HashMap<Character,Character> map = new HashMap<Character,Character>();

		int len = s.length();
		for(int i = 0; i< len ;i++){

			if(map.containsKey(s.charAt(i))){
				if(map.get(s.charAt(i)) != t.charAt(i) ){
					return false;
				}
			}else{
				if (map.containsValue(t.charAt(i))) 
					return false;
				map.put(s.charAt(i),t.charAt(i) );
			}
		}
		return true;
	}
}
