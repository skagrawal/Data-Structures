/**
 * 
 */
package ska.ds;

import java.util.Random;
import java.util.Stack;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * This is sorting a stack using one extra stack
 * time - O(N^2) space - O(N)
 */
public class StackSorting {

	public static void main(String[] args) {

		
		Stack<Integer> stack = new Stack<Integer>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			stack.push(random.nextInt(100));
		}
		for(int i : stack){
			System.out.print(i + " ");
		}
		System.out.println("\nAfter sorting");
		//Sorting here
		
		stack = sort(stack);
		
		while(!stack.isEmpty()){
			System.out.print(stack.pop() + " ");
		}
	}

	private static Stack<Integer> sort(Stack<Integer> stack) {

		Stack<Integer> temp = new Stack<Integer>();
		while(!stack.isEmpty()){
			int top = stack.pop();
			while(!temp.isEmpty() && top > temp.peek()){
				stack.push(temp.pop());
			}
			
			temp.push(top);
		}
		return temp;
	}

}
