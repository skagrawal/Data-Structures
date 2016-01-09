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
public class Problem1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<String> readFile = readFile("input.txt");
		long [] set = null;
		int index = 0;
		Long noOfTestCases = Long.parseLong(readFile.get(index++));
		int count = 0;
		int [][] points = null;
		String s;
		long res = 0L;
		for (int i = 0; i < noOfTestCases; i++) {
			count = Integer.parseInt(readFile.get(index++));
			points = new int[count][2];
			for (int j = 0; j < count; j++) {
				s = readFile.get(index++);
				points[j][0] = Integer.parseInt(s.split(" ")[0]);
				points[j][1] = Integer.parseInt(s.split(" ")[1]);
			}
			System.out.println("Case #"+(i+1)+": "+getCount(points));

		}
	}
	
	
	
	private static int getCount(int[][] points) {
		double dis1, dis2 = 0;
		int count = 0;
		for (int i = 0; i < points.length; i++) {
			//System.out.println("i "+i);
			for (int j = 0; j < points.length; j++) {
				if(i==j) continue;
				//System.out.println("i "+i+" j "+j);
				dis1 = getDistance(points[i][0],points[i][1],points[j][0],points[j][1]);
				//System.out.println("first = "+ dis1 +"  "+ dis2);
				for (int k = 0; k < points.length; k++) {
					if(k==j || k==i || i== j) continue;
					//System.out.println("j "+j+" k "+k);
					dis2 = getDistance(points[j][0],points[j][1],points[k][0],points[k][1]);
					//System.out.println(dis1 +"  "+ dis2);
					
					if(dis1 == dis2){
						//System.out.println("i "+i+" j "+j+" k "+k);
						count++;
						//System.out.println();
					}
				}
			}
		}
		
		return count/2;
		
	}

	
	



	private static double getDistance(int px, int py, int qx, int qy) {
		//System.out.println(px+"  "+py+"  "+qx+"  "+qy);
		int val = (int) (Math.pow((px-qx),2) + Math.pow((py-qy),2));
		System.out.println(val);
		return val;
		
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
