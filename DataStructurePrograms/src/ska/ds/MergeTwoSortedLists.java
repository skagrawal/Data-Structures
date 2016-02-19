/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * Time - O(N) and space - O(N)
 */
public class MergeTwoSortedLists {

	public static void main(String[] args) {
		ListNode l1 = null,l2 = null; 
		mergeTwoLists(l1, l2);
		
	}

	static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    
        if( l1 == null) return l2;
        if( l2 == null) return l1;
        
        ListNode temp = null;
        if(l1.val < l2.val){
            temp = l1;
            l1.next = mergeTwoLists(l1.next,l2);
        }else{
            temp = l2;
            l2.next = mergeTwoLists(l1,l2.next);
        }
        return temp;
    }
}
