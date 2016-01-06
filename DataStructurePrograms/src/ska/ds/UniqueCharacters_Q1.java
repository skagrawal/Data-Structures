/**
 * 
 */
package ska.ds;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author Shubham
 * 1.1 Implement an algorithm to determine if a string has all unique characters. 
 * What if you cannot use additional data structures?
 */
public class UniqueCharacters_Q1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(checkUniqueWithSort("test"));

		
	}

	/* This uses additional data structure Tree set which gives lookup in O(Nlg N) time.
	 * Time = O(Nlg N)
	 * Space = O(N)
	 */

	private static boolean checkUnique(String s) {
		// TODO Auto-generated method stub
		char[] charArr = s.toCharArray();
		TreeSet<Character> tree = new TreeSet<Character>();
		char c;
		for(int i= 0; i<charArr.length; i++){
			c = charArr[i];
			if(!tree.contains(c)){
				tree.add(c);
			}else{
				System.out.println(c+ " is repeated");
				return false;
			}
		}
		return true;
	}

	/* This uses no additional data structure but uses sorting the characters.
	 * Time = O(Nlg N)
	 * Space = O(N)
	 */
	private static boolean checkUniqueWithSort(String s) {
		// TODO Auto-generated method stub
		char[] charArr = s.toCharArray();
		Arrays.sort(charArr);
		
		char c;
		char last = charArr[0];
		for(int i= 1; i<charArr.length; i++){
			c = charArr[i];
			if(c != last){
				last = c;
			}else{
				System.out.println(c + " is repeated");
				return false;
			}
		}
		return true;
	}
}
