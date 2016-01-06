package ska.ds;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;



public class hello {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {

		// TODO Auto-generated method stub

//		int num, temp, x=0, s;
//		BufferedReader buff= new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Enter the no.");
//		num = Integer.parseInt(buff.readLine());
//		temp = num;

		
		Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();
		System.out.println(table.size());
		table.put(1, true);
		System.out.println(table.get(1));
		
		List<String> lis = new LinkedList<String>();
		System.out.println(lis.size());
		
//
//		for (; num > 0;) 
//		{
//			s = num%10;
//			System.out.println(s);
//			x = x*10 + s;
//			System.out.println("x="+x);
//			num = num/10;
//		}
//		if (temp==x)
//			System.out.println("no. is palindrome");
//		else
//			System.out.println("no. isn't palindrome");
	}


}
