package ska.ds.tree;

import ska.ds.ListNode;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Implement two similer leetcode midium level problems
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class SortedArrayToBST {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] nums = {1,2,3,5,7,8,9,10};
		BinarySearchTree bst = new BinarySearchTree();
		
		//Array
		TreeNode sortedArrayToBST = sortedArrayToBST(nums);
		bst.inorderTraversalRecursive(sortedArrayToBST);
		
		//List
		
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		return helper_sortedArrayToBST(nums,0,nums.length);
	}

	public static TreeNode helper_sortedArrayToBST(int [] nums, int low, int high){

		if(low>=high)
			return null;

		int mid = (int)(low+high)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper_sortedArrayToBST(nums,low,mid);
		root.right = helper_sortedArrayToBST(nums,mid+1,high);

		return root;
	}

	static ListNode temp;
	public TreeNode sortedListToBST(ListNode head) {
		if(head == null) return null;
		int len = 0;
		temp = head;
		while(head != null){
			len++;
			head = head.next;
		}

		return helper_sortedListToBST(  0, len);
	}


	public TreeNode helper_sortedListToBST( int start, int end) {

		if(start>=end) return null;
		int mid =  (int)(end + start)/2;
		TreeNode left = helper_sortedListToBST( start, mid);
		TreeNode root = new TreeNode(temp.val);
		root.left = left;
		temp = temp.next;
		root.right = helper_sortedListToBST(mid+1, end);
		return root;
	}


}
