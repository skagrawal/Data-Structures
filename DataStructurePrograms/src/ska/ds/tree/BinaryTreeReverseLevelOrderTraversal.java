/**
 * 
 */
package ska.ds.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example: Given binary tree {3,9,20,#,#,15,7},
 */
public class BinaryTreeReverseLevelOrderTraversal {

	public static void main(String[] args) {

		//create tree and pass root
		TreeNode root = null;
		levelOrderBottom(root);

	}
	
	static List<List<Integer>> lists = new  LinkedList<List<Integer>>();
    /**
     * 
     * @param root
     * @return
     */
	static List<List<Integer>> levelOrderBottom(TreeNode root) {
         if(root == null) return lists;
         int level = 0;
         getList(root,lists,level);
         Collections.reverse(lists);
         return lists;
    }
	
	/**
	 * 
	 * @param root
	 * @param lists
	 * @param level
	 */
    static void getList(TreeNode root,List<List<Integer>> lists,int level){
        if(root == null){
            return;
        }
        if(lists.size() < level+1){
            lists.add(new LinkedList<Integer>());
        }       
        lists.get(level).add(root.val);
        
        getList(root.left,lists,level+1);
        getList(root.right,lists,level+1);
    }

}
