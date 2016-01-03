package ska.ds;

/**
 * SwapNodesInPairs.java
 * Shubham Agrawal (sagrawal@iastate.edu)
 * This problem is part of Leetcode problem set from linked list with medium difficulty
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Creating a default list
		ListNode head = new ListNode(0);
		ListNode temp = head;
		for (int i = 1; i < 10; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		temp.next = null;

		printList(head);
		//Swapping the list pair wise
		head = swapPairs(head);
		printList(head);

	}



	public static ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null){
			return head;
		}

		ListNode temp = head;
		ListNode second = null,startHead = null;
		startHead =  head;

		//Swapping first pair
		if(head.next != null){
			temp = head.next.next;
			second = head.next;

			head.next = temp;
			second.next = head;
			startHead =  second;
		}

		//Swapping remaining pairs
		while(head.next != null){
			if(head.next != null){
				if(head.next.next != null){
					head.next = swap(head.next);
					head = head.next.next;
				}else{
					break;
				}
			}else{
				break;
			}
		}


		return startHead;
	}

	//Fuction to swap one pair
	public static ListNode swap(ListNode head){
		ListNode temp,second;
		if(head != null){
			if(head.next != null){
				temp = head.next.next;
				second = head.next;
				head.next = temp;
				second.next = head;
				head = second;
			}
		}
		return head;
	}

	//Function to print the list
	private static void printList(ListNode head) {

		while(head != null){
			System.out.print(head.val + "-->");
			head = head.next;
		}
		System.out.print("null");
		System.out.println();

	}

}

// Class for Node of Linked List
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
