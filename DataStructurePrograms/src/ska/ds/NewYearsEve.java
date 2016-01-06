/**
 * 
 */
package ska.ds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Shubham
 *
 */
public class NewYearsEve {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		ArrayList<String> readFile = readFile("B-small-practice.in");
		float b,l,n;
		
		List<Integer> list = new LinkedList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		
		set.add(1);
		set.add(2);
		set.add(3);
		
		list.addAll(set);
		
		readFile.remove(0);
		int i = 1;
		int mm,nn;
		for(String s : readFile){
			//System.out.println(s);
			//			b = Float.parseFloat(s.split(" ")[0]);
			//			l = Float.parseFloat(s.split(" ")[1])-1;
			//			n = Float.parseFloat(s.split(" ")[2])+1;
			//			l -= 1;
			//
			//			n = n*(n+1)/2;
			//			b = 3*b;
			//			l = 1 + ((3*l + l*l)/2);
			//			//System.out.println(b +"   "+l +"   "+n);
			//			System.out.print("Case #"+ i++ +": ");
			//			if(b > n*3){
			//				System.out.println(250.0);
			//			}else{
			//				b -= l;
			//				//System.out.println("Else "+b +"   "+l +"   "+n);
			//
			//				System.out.println(250*b/n);
			//			}

			mm = Integer.parseInt(s.split(" ")[0]);
			nn = Integer.parseInt(s.split(" ")[1]);
			if(mm == 1){
				System.out.println("Case #"+ i++ +": " + 1);
			}
			else if(mm == nn){
				System.out.println("Case #"+ i++ +": " + factorial(nn));
			}
			else{
				int value  = (int) (factorial(mm) * mm * (nn-mm) * factorial(nn-mm+1));
				System.out.println("Case #"+ i++ +": " + value);
				

			}

		}

	}

	public static long factorial(int n) {
		long result = 1;
		for (int i = 1; i <= n; i++) {
			result = result * i;
		}
		return result%1000000007;
	}

	private static ArrayList<String> readFile(String file) { 

		ArrayList<String> list = new ArrayList<String>();
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				list.add(line);
				line = br.readLine();
			}
			br.close();

		} catch (IOException e) { 
			System.out.println(e.toString()); 
			e.printStackTrace(); 
		}
		return list;
	} 
}
