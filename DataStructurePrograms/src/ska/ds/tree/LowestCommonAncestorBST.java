/**
 * 
 */
package ska.ds.tree;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorBST {

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		for (int i = 0; i < 10; i++) {
			bst.insert(i);
		}
		TreeNode root = bst.getRoot();
		TreeNode res = lowestCommonAncestor(root,new TreeNode(5),new TreeNode(8));
		System.out.println(res.val);
	}
	
	
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	     
        if(root == null){
            return root;
        }
        //If both nodes value is greater than root then check in right subtree
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        //If both nodes value is smaller than root then check in left subtree
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        //If value of root is in between of two nodes value then return root
        if(root.val > p.val && root.val < q.val){
            return root;
        }
        return root;
    }

}
