/**
 * 
 */
package ska.yodle;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * http://www.yodlecareers.com/puzzles/triangle.html
 */
public class Triangle {

	public static void main(String[] args) {

		int [][] arr = readFile("triangle.txt");
//		int [][] arr = {{5},{9, 6 },{4, 6, 8},{0, 7, 1, 5}};
		int count = 1;
		while(count < arr.length){

			arr[count][0] += arr[count - 1][0];
			for(int i = 1; i< count; i++){
				int max = Math.max(arr[count-1][i], arr[count-1][i-1]);
				arr[count][i] += max;
			}
			arr[count][count] += arr[count - 1][count-1];
			count++;
		}

		int max = 0;
		count--;
		for (int i = 0; i < arr[count].length; i++) {
			if(max < arr[count][i]){
				max = arr[count][i];
			}
		}

		System.out.println(max);

	}

	private static int[][] readFile(String string) {
		int [][] arr = null;
		FileReader reader = null;
		BufferedReader br = null;

		try {
			//Reading here for counting the number of lines in file
			reader = new FileReader(string);
			br =  new BufferedReader(reader);

			int len = 0;
			while (br.readLine() != null) {
				len++;
			}

			//Reading again for real input
			reader = new FileReader(string);
			br = new BufferedReader(reader);
			arr = new int[len][len];
			String line = br.readLine();
			int ind = 0;
			while (line != null) {
				String [] temp = line.split(" ");
				for (int i = 0; i < temp.length; i++) {
					arr[ind][i] = Integer.parseInt(temp[i]);
				}
				ind++;
				line = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arr;
	}

}
