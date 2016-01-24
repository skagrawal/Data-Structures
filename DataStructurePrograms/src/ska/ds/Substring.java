/**
 * 
 */
package ska.ds;

/**
 * @author Shubham(tech.shubham@gmail.com)
 * Iterative solution for checking if one string is substring of another
 * Time = O(N)
 */
public class Substring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str1 = "abc";
		String str2 = "abcde";
		
		System.out.println(checkSubstring(str1,str2));
	}

    private static boolean checkSubstring(String str1, String str2) {

    	if(str1.length()> str2.length()){
		    String temp = str1;
		    str1 = str2;
		    str2 = temp;
		}
		
		int s = str1.length();
		int l = str2.length();
		
		int j = 0;
		for(int i = 0; i<l && j<s; i++){
			if(str2.charAt(i) == str1.charAt(j)){
				j++;
			}
		}
		
		if(j == s){
		    return true;
	    }
		return false;
	}
	

}
