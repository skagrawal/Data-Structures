/**
 * 
 */
package ska.ds;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * How to use a priority queue using comparator class
 */
public class PriorityQueueExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Comparator<String> comparator = new ExampleComparator();
		PriorityQueue<String> pq = new PriorityQueue<String>(10,comparator);
		
		pq.add("Sdbefgliergerigjergerg");
		pq.add("Sdbefgliergerigj");
		pq.add("Sdbefgliergerigjerge");
		pq.add("Sdbefgl");
		pq.add("Sdbefglierg");
		
		
		while(!pq.isEmpty()){
			System.out.println(pq.remove());
		}
	}

}

class ExampleComparator implements Comparator<String>{
	@Override
	public int compare(String s1,String s2){
		return s1.length() < s2.length() ? -1 : 1;
	}
}