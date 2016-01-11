package ska.ds;


/**
 * @author Shubham (tech.shubham@gmail.com)
 * Print all combinations of given string
 */
public class Combinations {
	public static void main(String a[]) {
		StringBuilder out = new StringBuilder();
		combine("ABC",0, out);
	}
	
	static void combine(String str , int start, StringBuilder out ){
		for( int i = start; i < str.length(); ++i ){ 
			out.append( str.charAt(i) ); 
			System.out.println( out.toString() );
			combine(str,i + 1, out); 
			out.setLength( out.length() - 1 );
		} 
	}
}
