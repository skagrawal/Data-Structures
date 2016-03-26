/**
 * 
 */
package ska.fbhackercup.qualification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Shubham
 *
 */
public class Problem3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> readFile = readFile("input.txt");
		long [] set = null;
		int index = 0;
		Long noOfTestCases = Long.parseLong(readFile.get(index++));
		long price = 0L;
		long res = 0L;
		for (int i = 0; i < noOfTestCases; i++) {
			price = Long.parseLong(readFile.get(index++).split(" ")[1]);
			set = createSet(readFile.get(index++));
			res = getCount(set,price);
			System.out.println("Case #"+(i+1)+": "+res);
		}

	}


	private static long getCount(long[] set, long price) {
		long temp = 0L;
		long count = 0L;
		//Arrays.sort(set);
		for (int i = 0; i < set.length; i++) {
			temp = set[i];
			if(temp <= price){
				//System.out.println(temp);
				count++;
			}
			for (int j = i+1; j < set.length; j++) {
				temp += set[j];
				//System.out.println(temp);
				if(temp <= price){
					//System.out.println(temp);
					count++;
				}
				else{
					break;
				}
			}
			
		}
		return count;
	}

	private static long[] createSet(String str) {
		String[] split = str.split(" ");
		long[] set = new long[split.length];
		int i = 0;
		for(String s : split){
			set[i++] = Long.parseLong(s);
		}

		return set;
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
