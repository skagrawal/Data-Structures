/**
 * 
 */
package ska.ds;

/**
 * StartPointOfLoopInLinkedList.java
 * Shubham Agrawal (sagrawal@iastate.edu)
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * https://leetcode.com/problems/linked-list-cycle-ii/
 */

public class StartPointOfLoopInLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(0);
		ListNode temp = head;
		//For cycle
		ListNode cyclePoint = null;
		for (int i = 1; i < 10; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
			if(i == 5)
				cyclePoint = temp;
		}
		temp.next = cyclePoint;
		head = detectCycle(head);
		if(head != null)
			System.out.println(head.val);
		else
			System.out.println("No loop");
	}


	public static ListNode detectCycle(ListNode head) {
		if(head == null || head.next == null)
			return null;

		if(head.next == head)
			return head;

		ListNode fast = head,slow = head;
		while(fast != null && fast.next != null ){
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow)
				break;
		}
		if(fast == slow){
			// Reset slow to head
			slow = head;
			while(fast != slow ){
				slow = slow.next;
				fast = fast.next;
				if(fast == slow)
					break;
			}
			return slow;
		}
		return null;
	}

}
