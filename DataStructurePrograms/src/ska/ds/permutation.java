package ska.ds;


/**
 * @author Shubham (tech.shubham@gmail.com)
 * Print all permutation of given string
 */
public class Permutation {

	public static void main(String a[]) {
		permute("ABCDE","");
	}

	public static void permute(String str,String prefix) {


		int len = str.length();
		if(len == 0){
			System.out.print(prefix +" ");
		}
		else{	
			for (int i = 0; i < len; i++) {
				String s = str.substring(0, i)+ str.substring(i+1, len);
				permute(s, prefix + str.charAt(i));
			}
		}

	}

}
