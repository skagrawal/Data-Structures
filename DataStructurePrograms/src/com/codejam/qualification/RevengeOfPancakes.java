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
import java.util.Arrays;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
public class RevengeOfPancakes {

	public static void main(String[] args) {

		ArrayList<String> readFile = readFile("B-large.in");
		ArrayList<String> writeFile = new ArrayList<String>();
		int cases = Integer.parseInt(readFile.get(0));
		boolean [] digits = new boolean[10];
		boolean flag = false;
		for(int icase=1;icase<cases+1;icase++){
			String seq = readFile.get(icase);
			int count = sort(seq);
			String s = "Case #"+ icase+ ": " + count;
			writeFile.add(s);
		}
		writeFile(writeFile,"output.txt");
	}

	private static int sort(String seq) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (char c : seq.toCharArray()) {
			list.add(c);
		}
		int count = 0;
		while(list.indexOf('-') != -1){
			count++;
			int ind = list.indexOf('+');
			for(int i=1;i< list.size();i++){
				if(list.get(i-1) == '+' && list.get(i) == '-'){
					ind = i;
				}
			}
			if(ind == -1){
				ind = list.size();
			}
			for(int i=0;i<ind;i++){
				if(list.get(i) == '-'){
					list.set(i, '+');
				}else if(list.get(i) == '+'){
					list.set(i, '-');
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		
		return count;
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
