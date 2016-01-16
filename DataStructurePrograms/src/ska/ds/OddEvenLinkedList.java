/**
 * 
 */
package ska.ds;

/**
 * StartPointOfLoopInLinkedList.java
 * Shubham Agrawal (sagrawal@iastate.edu)
 * Given a singly linked list, group all odd nodes together followed by the even nodes. 
 * Please note here we are talking about the node number and not the value in the nodes.
 * https://leetcode.com/problems/odd-even-linked-list/
 */

public class OddEvenLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(0);
		ListNode temp = head;
		//For cycle
		for (int i = 1; i < 10; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		head = oddEvenList(head);
		while(head != null){
			System.out.print(head.val + " ");
			head = head.next;
		}
		
	}


	public static ListNode oddEvenList(ListNode head) {
		ListNode odd;
        ListNode even;
        ListNode start1, start2;
        if(head == null || head.next == null || head.next.next == null)
            return head;
        
        
        odd = head;
        even = head.next;
        start1 = odd;
        start2 = even;
        while(odd.next != null  && even.next != null ){
            
            odd.next = odd.next.next;
            odd = odd.next;
            
            even.next = even.next.next;
            even = even.next;
        }
        
        odd.next = start2;
        return start1;
	}

}
