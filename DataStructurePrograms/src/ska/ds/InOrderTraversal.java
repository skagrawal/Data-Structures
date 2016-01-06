package ska.ds;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Shubham
 * In Order Traversal iteratively
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * Difficulty: Medium
 */
public class InOrderTraversal {

	public static void main(String [] args) {


		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;

		
		List<Integer> trav = inorderTraversal(n1);
		
		
	}

	public static List<Integer> inorderTraversal(TreeNode root) {

		List<TreeNode> list = new ArrayList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		if(root == null){
			return null;
		}
		while(node != null){
			stack.push(node);
			node = node.left;
		}

		while(!stack.empty()){
			node = stack.pop();
			System.out.println(node.val);

			node = node.right;
			while(node != null){
				stack.push(node);
				node = node.left;
			}
		}
		return null;
	}

}


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; left = null; right = null;}
}
