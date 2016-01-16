/**
 * 
 */
package fbhackercup.qualification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Shubham
 *
 */
public class CodingContestCreation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<String> readFile = readFile("input.txt");
		long [] set = null;
		int index = 0;
		int noOfTestCases = Integer.parseInt(readFile.get(index++));
		long price = 0L;
		long res = 0L;
		for (int i = 0; i < noOfTestCases; i++) {
			
			int size = Integer.parseInt(readFile.get(index++));
			String [] str = new String[size];
			int [] arr = new int[size];
			str = readFile.get(index++).split(" ");
			for (int j = 0; j < str.length; j++) {
				arr[j] = Integer.parseInt(str[j]);
			}
			

			int count = 0;
			
			for (int j = 0; j < arr.length; j++) {
				
				int diff =arr[j+1] - arr[j];
				if(diff >0 &&  diff<= 10){
					count++;
				}else{
					
				}
				
			}
			
		}

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
