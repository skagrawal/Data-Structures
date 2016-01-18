/**
 * 
 */
package ska.ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Shubham
 *
 */
public class BFS_DFS_tree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(3);
		bst.insert(4);
		bst.insert(2);
		bst.insert(1);
		for (int i = 0; i < 10; i++) {
			bst.insert(i);
		}

		TreeNode root = bst.getRoot();
		
		//Use any of the following
		dfsRec(root);
		dfsIterative(root);
		bfsIter(root);
	}

	static void dfsIterative(TreeNode root){
		System.out.println("In DFS iterative \n");
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			if(!node.visited){
				System.out.print(node.val+" ");
				node.visited = true;
				if(node.right != null && !node.right.visited) 
					stack.push(node.right);
				if(node.left != null && !node.left.visited)
					stack.push(node.left);

			}
		}
		System.out.println();
	}

	static void dfsRec(TreeNode root){

		if(!root.visited){
			root.visited = true;
			System.out.println(root.val);
		}

		if(root.left != null && !root.left.visited){
			dfsRec(root.left);
		}
		if(root.right != null && !root.right.visited){
			dfsRec(root.right);
		}
	}

	static void bfsIter(TreeNode root){

		System.out.println("In BFS iterative");
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			TreeNode node = queue.remove();
			if(!node.visited){
				System.out.print(node.val+" ");
				node.visited = true;
				if(node.left != null && !node.left.visited)
					queue.add(node.left);
				if(node.right != null && !node.right.visited) 
					queue.add(node.right);
			}
		}
		System.out.println();
	}

}
