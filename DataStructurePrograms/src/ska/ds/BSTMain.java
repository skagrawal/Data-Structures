package ska.ds;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Shubham
 * Main file for calling different operation on  Binary Search Tree class 
 */
public class BSTMain {

	public static void main(String [] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(1);
		bst.insert(4);
		bst.insert(2);
		bst.insert(3);
		TreeNode root = bst.getRoot();
		System.out.println("Max "+ bst.findMaximum(root).value);
		System.out.println("Min "+ bst.findMinimum(root).value);
		bst.inOrderTraversalIterative(root);
		bst.preorderTraversalRecursive(root);
		bst.postOrderTraversalIterative(root);
	}

	

}
