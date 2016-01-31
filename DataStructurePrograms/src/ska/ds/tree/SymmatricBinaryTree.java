/**
 * 
 */
package ska.ds.tree;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * https://leetcode.com/problems/symmetric-tree/
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 */
public class SymmatricBinaryTree {

	public static void main(String[] args) {

		
		BinarySearchTree bst = new BinarySearchTree();
		for (int i = 0; i < 10; i++) {
			bst.insert(i);
		}
		TreeNode root = bst.getRoot();
		//Pass a binary tree root
		System.out.println(isSymmetric(root));
	}
	
	
	public static boolean isSymmetric(TreeNode root) {
        if (root == null)
		    return true;
	    return isSymmetric(root.left, root.right);
    }
    
    public static boolean isSymmetric(TreeNode l, TreeNode r) {
    	if (l == null && r == null) {
    		return true;
    	} 
    	else if (r == null || l == null) {
    		return false;
    	}
     
    	if (l.val != r.val)
    		return false;
     
    	if (!isSymmetric(l.left, r.right))
    		return false;
    	if (!isSymmetric(l.right, r.left))
    		return false;
     
    	return true;
    }

}
