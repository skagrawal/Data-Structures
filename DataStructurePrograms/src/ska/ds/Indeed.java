package ska.ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Indeed {

	public static void main(String[] args) {
	
		
		Scanner in = new Scanner(System.in);
		int Q = Integer.parseInt(in.nextLine());
        int M = Integer.parseInt(in.nextLine());
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int N = 0;
        for(int i=0; i< M; i++){
            String line = in.nextLine();
            int x = Integer.parseInt(line.split(" ")[0]);
            int y = Integer.parseInt(line.split(" ")[1]); 
            N += y;
            map.put(x,y);
            
        }
        Map<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(map);
        int ind = 0;
        int [] arr = new int[N];
		for(int x : sortedMap.keySet()){
			for(int i = 0; i < sortedMap.get(x); i++){
				arr[ind++] = x;
			}
		}
                                     
        for(int k = 1 ; k < Q;k++){
            int temp = N*k/Q;
            int index = (int)Math.ceil(temp);
            System.out.println(arr[index]);
        }
	}

}
