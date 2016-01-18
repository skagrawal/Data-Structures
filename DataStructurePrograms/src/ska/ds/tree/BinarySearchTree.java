package ska.ds.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * @author Shubham (tech.shubham@gmail.com)
 * Binary Search Tree implementation and supported operations
 * Pre order traversal (Recursive and iterative)
 * In order traversal (Recursive and iterative)
 * Post order traversal (Recursive and iterative)
 * Find maximum element of BST
 * Find minimum element of BST
 */

public class BinarySearchTree {

	private TreeNode root = null;

	/*
	 * Check for root and call insertRecc()
	 */
	public BinarySearchTree insert(int value){
		TreeNode node = new TreeNode(null, value, null);
		if(root == null){
			root = node;
			return this;
		}
		insertRecc(root, node);
		return this;
	}

	/*
	 * in order Traversal Recursive of a BST
	 */
	public void inorderTraversalRecursive(TreeNode root) {
		if(root == null){
			return;
		}
		inorderTraversalRecursive(root.left);
		System.out.print(root.val +"  ");
		inorderTraversalRecursive(root.right);
	}

	/*
	 * pre order Traversal Recursive of a BST
	 */
	public void preorderTraversalRecursive(TreeNode root) {
		if(root == null){
			
			return;
		}
		System.out.print(root.val +"  ");
		preorderTraversalRecursive(root.left);
		preorderTraversalRecursive(root.right);
		
	}

	/*
	 * post order Traversal Recursive of a BST
	 */
	public void postorderTraversalRecursive(TreeNode root) {
		if(root == null){
			return;
		}
		postorderTraversalRecursive(root.left);
		postorderTraversalRecursive(root.right);
		System.out.print(root.val +"  ");
	}

	/*
	 * in order Traversal Iteratively of a BST
	 */
	public List<Integer> inOrderTraversalIterative(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> list = new LinkedList<Integer>();
		TreeNode node = root;
		if(root == null){
			return list;
		}
		while(node != null){
			stack.push(node);
			node = node.left;
		}

		while(!stack.empty()){
			node = stack.pop();
			System.out.print(node.val + "  ");
			list.add(node.val);
			node = node.right;
			while(node != null){
				stack.push(node);
				node = node.left;
			}
		}
		System.out.println("\nEnd of inorder");
		return list;
	}

	/*
	 * in order Traversal Iterative of a BST
	 */
	public List<Integer> preOrderTraversalIterative(TreeNode root) {
		List<Integer> list = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		if(root == null){
			return list;
		}

		stack.push(root);

		while(!stack.isEmpty()){
			node = stack.pop();
			// System.out.println(node.val);
			list.add(node.val);
			if(node.right != null)
				stack.push(node.right);
			if(node.left != null)
				stack.push(node.left);
		}
		System.out.println("\nEnd of preorder");
		return list;
	}
	/*
	 * post order Traversal Iterative of a BST
	 */
	public List<Integer> postOrderTraversalIterative(TreeNode root) {
		System.out.println();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> list = new LinkedList<Integer>();
		if (root == null) 
			return null;

		stack.push(root);
		TreeNode priv = null;

		while (!stack.isEmpty()) {
			TreeNode curr = stack.peek();
			if ((priv == null) || (priv.left == curr) || (priv.right == curr)) {
				if (curr.left != null){
					stack.push(curr.left);
				}
				else if (curr.right != null){
					stack.push(curr.right);
				}
			} 
			// we are traversing up in the tree from the left
			else if (curr.left == priv) {
				if (curr.right != null){
					stack.push(curr.right);
				}
			} 
			// we are traversing up in the tree from the right
			else {
				System.out.print(curr.val + "  ");
				list.add(curr.val);
				stack.pop();
			}
			priv = curr;
		}
		System.out.println("\nEnd of postorder");
		return list;
	}


	/*
	 * Return node with minimum value
	 */
	public TreeNode findMinimum(TreeNode root){
		if(root == null){
			return null;
		}
		while(root.left != null){
			root = root.left;
		}
		return root;
	}

	/*
	 * Return node with maximum value
	 */
	public TreeNode findMaximum(TreeNode root){
		if(root == null){
			return null;
		}
		while(root.right != null){
			root = root.right;
		}
		return root;
	}

	/*
	 * Function for inserting one node at a time
	 */
	private void insertRecc(TreeNode newRoot, TreeNode node) {
		//if new node value is less than current parent then insert into left else into right
		if (node.val < newRoot.val) {
			if(newRoot.left == null){
				newRoot.left = node;
				return;
			}
			else {
				insertRecc(newRoot.left, node);
			}
		}
		else{
			if(newRoot.right == null){
				newRoot.right = node;
				return;
			}
			else {
				insertRecc(newRoot.right, node);
			}
		}

	}

	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}
}

/*
 * Node class with fields for value of node and left and right children
 */

class TreeNode {
	TreeNode left;
	int val;
	TreeNode right;
	public boolean visited = false;

	TreeNode(TreeNode left, int val, TreeNode right) {
		this.left = left;
		this.right = right;
		this.val = val; 
	}
}