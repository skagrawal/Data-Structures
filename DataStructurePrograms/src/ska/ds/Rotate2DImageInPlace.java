/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * You are given an n x n 2D matrix representing an image.Rotate the image by 90 degrees (clockwise).Could you do this in-place.
 * https://leetcode.com/problems/rotate-image/
 * Time - O(N^2) Space - O(1)
 */
public class Rotate2DImageInPlace {

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] matrix = {{1,23},{43,4}};
		rotate(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Rotate the image by 90 degrees
	 * @param matrix
	 */
	private static void rotate(int[][] matrix) {

		if(matrix.length <= 1){
			return;
		}

		int n = matrix.length;

		for(int layer = 0;layer<n/2;layer++){
			int first = layer;    
			int last = n-1-layer;
			for(int i=layer;i<last;i++){
				int offset = i-first;
				//top
				int top = matrix[first][i];
				//left->top
				matrix[first][i] = matrix[last-offset][first];
				//bottom->left
				matrix[last-offset][first] = matrix[last][last-offset];
				//Right->Bottom
				matrix[last][last-offset] = matrix[i][last];
				//Top->Right
				matrix[i][last] = top;
			}
		}
	}
}
