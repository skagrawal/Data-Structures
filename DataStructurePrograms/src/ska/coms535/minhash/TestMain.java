/**
 * 
 */
package ska.coms535.minhash;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MinHash min = new MinHash("/Users/Shubham/Documents/workspace_sa/FunWithDataStructures/DataStructurePrograms/src/ska/coms535/minhash/space", 400);
		String[] list = min.allDocs();
		System.out.println(min.exactJaccard("space-0.txt", "space-1.txt"));
		System.out.println(min.approximateJaccard("space-0.txt", "space-1.txt"));
	}

}
