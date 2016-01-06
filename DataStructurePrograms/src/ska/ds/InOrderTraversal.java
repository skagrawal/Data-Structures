package ska.ds;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Shubham
 *
 */
public class InOrderTraversal {

	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public class Solution {
	    public List<Integer> inorderTraversal(TreeNode root) {
	        
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
}