/**
 * 
 */
package ska.ds.tree;

import java.util.ArrayList;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Design an algorithm to serialize and deserialize a binary tree.
 */
public class SerializeDeserializeBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TreeNode node = new TreeNode(1);
		
		//Here I store all the elements in a Array list where each null child is stored as #.
		TreeNode ret = deserialize(serialize(node));
		

	}


	static ArrayList<String> list = new ArrayList<String>();
	static int ind = 0;
	
	// Encodes a tree to a single list.
	public static String serialize(TreeNode root) {

		if(root == null){
			list.add("#");
			return "";
		}

		list.add(root.val+"");
		serialize(root.left);
		serialize(root.right);

		return "";

	}

	// Decodes your encoded list to tree.
	public static TreeNode deserialize(String data) {


		if(ind >= list.size() || list.get(ind).equals("#")){
			ind++;
			return null;
		}
		//System.out.println(list.get(ind));
		TreeNode root = new TreeNode(Integer.parseInt(list.get(ind)));
		ind++;
		root.left = deserialize(data);
		root.right = deserialize(data);

		return root;
	}
}



