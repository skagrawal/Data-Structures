package ska.ds;

import java.util.HashMap;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Most frequent word in given message using hash map
 */
public class MostFrequentWordInString {

	public static void main(String[] args) {

		HashMap<String,Integer> map = new HashMap<String,Integer>();

		String str = "The best things in life are free yes free free free";
		String [] arr = str.split(" ");
		for(int i = 0; i<arr.length;i++){
			if(map.containsKey(arr[i])){
				map.put(arr[i], map.get(arr[i])+1);
			}else{
				map.put(arr[i], 1);
			}
		}

		int max = 0;
		String maxStr = null;
		for(String i : map.keySet()){
			if(map.get(i) > max){
				max = map.get(i);
				maxStr = i;
			}
		}

		System.out.println("String '"+maxStr +"' occures max "+ max +" times");
	}

}
