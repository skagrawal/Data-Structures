package ska.ds;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given numRows, generate the first numRows of Pascal's triangle.
 * https://leetcode.com/problems/pascals-triangle/
 */
public class PascalsTriangle {

	public static void main(String[] args) {

		List<List<Integer>> lists = generatePascalTriangle(8);
		for(List<Integer> list:lists){
			System.out.println(list.toString());
		}
	}

	/**
	 * @param numRows
	 * @return Given numRows, generate the first numRows of Pascal's triangle.
	 */
	private static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> lists = new LinkedList<List<Integer>>();
        if(numRows == 0){
            return lists;
        }
        List<Integer> list = new LinkedList<Integer>();
        List<Integer> prev = new LinkedList<Integer>();
        list.add(1);
        lists.add(list);
        prev = list;
        for(int i = 1;i<numRows;i++){
            list = new LinkedList<Integer>();
            list.add(1);
            for(int j = 1;j<i;j++){
                int val = prev.get(j-1) + prev.get(j);   
                list.add(val);
            }
            list.add(1);
            prev = list;
            lists.add(list);
        }
        return lists;
    }
}

