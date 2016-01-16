/**
 * 
 */
package ska.ds;

import java.util.LinkedList;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Suppose we have intervals like 5-10, 8-12. Then total numbers in these two intervals 
 * would be: {5,6,7,8,8,9,9,10,10,11,12}
 * 4th smallest number: 8
 * 5th smallest number: 8
 * Time complexity - O(n) 
 */
public class KthSmallestElementInIntervals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<interval> list = new LinkedList<interval>();

		list.add(new interval(5, 10));
		list.add(new interval(8, 12));
		int x = 4;
		System.out.println(x+ "th smllest element --"+getElement(list, x));
		x += 1;
		System.out.println(x+ "th smllest element --"+getElement(list, x));

	}

	static int getElement(LinkedList<interval> list, int k){

		int max = 0;
		for(interval i : list){
			max = (i.high > max ? i.high:max);
		}

		int [] arr = new int[max];
		int l,h;
		for (interval i : list) {
			l = i.low;
			h = i.high;
			while(l<=h){
				arr[l-1] = arr[l-1]+1;
				l++;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if(k <= arr[i])
				return i+1;
			k -= arr[i];
		}
		return -1;
	}


}

class interval{
	public int low  = 0;
	public int high = 0;

	interval(int low,int high) {
		this.low = low;
		this.high = high;
	}
}

