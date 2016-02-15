/**
 * 
 */
package ska.ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values 
 * along the path equals the given sum.
 * https://leetcode.com/problems/path-sum/
 */
public class PathSumInBinaryTree {

	public static void main(String[] args) {

		Random rand = new Random();
		BinarySearchTree bst = new BinarySearchTree();
		for (int i = 0; i < 10; i++) {
			bst.insert(rand.nextInt(100));
		}
		TreeNode root = bst.getRoot();
		boolean res = hasPathSum(root,45);
		System.out.println(res);
		res = hasPathSumRec(root,45);
		System.out.println(res);
	}


	public static boolean hasPathSum(TreeNode root, int sum) {
		if(root == null){
			return false;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);

		while(!q.isEmpty()){
			TreeNode temp = q.remove();
			if(temp.left == null && temp.right == null && temp.val == sum){
				return true;
			}
			else{
				if(temp.left != null){
					temp.left.val += temp.val;
					q.add(temp.left);
				}
				if(temp.right != null){
					temp.right.val += temp.val;
					q.add(temp.right);
				}

			}
		}
		return false;
	}


	public static boolean hasPathSumRec(TreeNode root, int sum) {
		if (root == null){
			return false;
		}
		if (root.val == sum && (root.left == null && root.right == null)){
			return true;
		}
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

}
