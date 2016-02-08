/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * https://leetcode.com/problems/excel-sheet-column-number/
 */
public class ExcelSheetColumn {

	public static void main(String[] args) {

		String str = "CD";
		int value = titleToNumber(str);
		System.out.println(value);
	}

	private static int titleToNumber(String s) {
		int sum = 0;
		int cur = 0;
		int len = s.length();
		for(int i = 0; i < len - 1;i++){
			cur = s.charAt(i) - 'A' + 1;
			sum = sum + cur;
			sum *= 26;
		}
		sum = sum + s.charAt(len-1) - 'A' + 1;
		return sum;
	}
	

}
