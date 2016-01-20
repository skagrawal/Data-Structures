package citrix;

import java.util.Scanner;

import java.util.Arrays;
import java.util.Comparator;
public class Circles {

	private static class Gear {
		int radius;
		int cost;
		int index;
		public Gear(int radius, int cost, int index){
			this.radius = radius;
			this.cost = cost;
			this.index = index;
		}

	}

	static int[] circles(int distance, int[] radius, int[] cost){
		
		boolean flag= true;
		for (int i = 0; i < radius.length; i++) {
			if(radius[i] != Integer.MAX_VALUE){
				flag = false;
				break;
			}
		}
		
		System.out.println(flag);
		int[] retArr = new int[radius.length];
		Gear[] arrGears = new Gear[radius.length];
		for(int index = 0; index < radius.length; index++){
			arrGears[index] = new Gear(radius[index], cost[index], index + 1);
		}
		Arrays.sort(arrGears, new Comparator<Gear>(){
			@Override
			public int compare(Gear g1, Gear g2) {
				if(g1.cost < g2.cost){
					return -1;
				} else if ( g1.cost == g2.cost){
					if(g1.radius > g2.radius){
						return -1;
					} else if ( g1.radius == g2.radius){
						if(g1.index < g2.index){
							return -1;
						}else if (g1.index == g2.index){
							return 0;
						}else{
							return 1;
						}
					} else{
						return 1;
					}
				} else{
					return 1;
				}
			}
		}
				);		

		for(int index = 0 ; index < arrGears.length; index++){
			Gear g1 = arrGears[index];
			for(int j = 0 ; j < arrGears.length ; j++){
				if(g1.radius + arrGears[j].radius >= distance){
					retArr[g1.index - 1] = arrGears[j].index;
					break;
				}
			}
		}

		return retArr;
	}

	public static void main(String[] args){
		int distance = 8;
		int[] radius = new int[]{1,3,6,2,5};
		int[] cost = new int[]{5,6,8,3,4};
		int[] outArr = circles(distance, radius, cost);
		for(int i : outArr){
			System.out.print(i +"\t");
		}
		System.out.println("");
	}
}