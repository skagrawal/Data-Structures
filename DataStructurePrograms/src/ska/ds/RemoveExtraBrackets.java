/**
 * 
 */
package ska.ds;

import java.util.Stack;

/**
 * @author Shubham(tech.shubham@gmail.com)
 * Remove all redundant parentheses from given equation
 * a+b+((c*d)) --> a+b+(c*d) 
 */
public class RemoveExtraBrackets {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "a+b+((c*d))";
		removeExtraBraces(str);

	}

	private static void removeExtraBraces(String str) {

		Stack<Integer> stack = new Stack<Integer>();
		int i = 0;
		char r[] = str.toCharArray();

		while (i < str.length()) {
			
			if((str.charAt(i) == '(')){
				if(str.charAt(i+1) == '('){
					stack.push(-i);
				}else{
					stack.push(i);
				}
				i++;
			}
			else if((str.charAt(i) != '(') && (str.charAt(i) != ')')){
				i++;
			}else if(str.charAt(i) == ')'){
				if(str.charAt(i-1) == ')' && stack.peek()<0){
					r[-stack.peek()] = '!';
				}
				r[i] = '!';
				stack.pop();
			}
			else if (str.charAt(i-1) != ')' && stack.peek() > 0){
				stack.pop();
			}
			i++;
		}
		StringBuffer result = new StringBuffer();
		for (i = 0; i<r.length; i++) {
			if (r[i] == '!') {
				continue;
			}
			result.append(r[i]);
		}
		System.out.println(result.toString());
		return;
	}

}
