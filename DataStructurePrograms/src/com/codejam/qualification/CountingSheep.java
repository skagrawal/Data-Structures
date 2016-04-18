/**
 * 
 */
package com.codejam.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
public class CountingSheep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<String> readFile = readFile("B-small-attempt0.in");
		ArrayList<String> writeFile = new ArrayList<String>();
		int cases = Integer.parseInt(readFile.get(0));
		boolean [] digits = new boolean[10];
		boolean flag = false;
		for(int icase=1;icase<cases+1;icase++){
			long num = Integer.parseInt(readFile.get(icase));
			long num_cp = num;
			int count = 0;
			for(int j=2;num<Long.MAX_VALUE;j++){
				if(num == num*j){
					String s = "Case #"+ icase+ ": INSOMNIA";
					writeFile.add(s);
//					System.out.println(s);
					break;
				}
				String str = num + "";
//				System.out.println(str);
				for(int ind = 0;ind<str.length();ind++){
					int pos = str.charAt(ind)-'0';
					if(digits[pos] == false){
						digits[pos] = true;
						count++;
					}
					if(count == 10){
						String s = "Case #"+ icase+ ": " + str;
//						System.out.println(s);
						writeFile.add(s);
						digits = new boolean[10];
						count = 0;
						flag = true;
						break;
					}
				}
				if(flag == true){
					flag = false;
					break;
				}
				num = num + num_cp;
			}
		}
		
		writeFile(writeFile,"output.txt");

	}
	
	private static void writeFile(ArrayList<String> writeFile, String fileName) {

		String path = "/Users/Shubham/Documents/workspace_sa/FunWithDataStructures/DataStructurePrograms/";
		File file = new File(path + fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
//			System.out.println(writeFile.size());
			for(int i = 0;i<writeFile.size();i++){
				bw.write(writeFile.get(i) + "\n");
			}
//			System.out.println("DOne");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			System.out.println(file);
			System.out.println(e.toString()); 
			e.printStackTrace(); 
		}
		return list;
	}

}
