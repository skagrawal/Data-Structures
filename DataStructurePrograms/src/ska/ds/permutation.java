package ska.ds;

public class permutation {


	public static void main(String a[]) {
		System.out.println("Main");
		//Combinations com = new Combinations("ABC");
		
		System.out.println("abc".compareTo("ab"));
		System.out.println("abc".compareTo("abcd"));
		System.out.println("abc".compareTo("ac"));
	}
}


class Combinations {
	private StringBuilder out = new StringBuilder(); 
	private final String in;
	public Combinations( final String str ){ 
		in = str; 
		combine();
	}
	public void combine() { combine( 0 ); } 

	private void combine(int start ){
		for( int i = start; i < in.length(); ++i ){ 
			out.append( in.charAt(i) ); 
			System.out.println( out );
			combine(i + 1); 
			out.setLength( out.length() - 1 );
		} 
	}
}