/**
 * 
 */
package ska.ds.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Given a binary tree, return the level order traversal of its nodes' values. (i.e, from left to right, level by level).
 */
public class BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {

		BinarySearchTree bst = new BinarySearchTree();
		for (int i = 0; i < 10; i++) {
			bst.insert(i);
		}
		TreeNode root = bst.getRoot();
		
		List<List<Integer>> levelOrder = levelOrder(root);
		
		//System.out.println(bst.postOrderTraversalIterative(root));
		for (int i = 0; i < levelOrder.size(); i++) {
			System.out.println(levelOrder.get(i).toString());
		}
		
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> lists = new LinkedList<List<Integer>>();
		if(root == null) return lists;
		List<TreeNode> queue = new LinkedList<TreeNode>();

		List<Integer> list = new LinkedList<Integer>();
		TreeNode temp = null;
		queue.add(root);
		queue.add(temp);

		while(queue.size()>1){
			TreeNode node = queue.remove(0);
			//System.out.println(node.val);
			list.add(node.val);
			if(node.left != null)
				queue.add(node.left);
			if(node.right != null)
				queue.add(node.right);

			if(queue.get(0) == temp){
				queue.remove(0);
				queue.add(temp);
				lists.add(list);
				list = new LinkedList<Integer>();
			}
		}
		return lists;
	}

}
