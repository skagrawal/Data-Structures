package ska.ds;

//Class for Node of Linked List
public class ListNode {
	
	public int val;
	public ListNode next;
	
	ListNode(int x) {
		val = x; 
	}
	
	public ListNode mergeSorted(ListNode head1, ListNode head2){
		if(head1 == null) 
			return head2;
		else if(head2 == null) 
			return head1;
		ListNode node;
		ListNode result = null;
		if(head1.val < head2.val){
			node = new ListNode(head1.val);
			result = head1;
			result.next = mergeSorted(head1.next, head2);
			//head1 = head1.next;
		}
		else if(head1.val > head2.val){
			node = new ListNode(head2.val);
			result = head2;
			result.next = mergeSorted(head1, head2.next);
			//head2 = head2.next;
		}
		return result;
	}

	//Delete alternate nodes
	public ListNode deleteAlt(ListNode node) {

		ListNode temp;
		if(node == null) 
			return node;
		else{
			temp = node.next;
			if(temp != null){
				node.next = temp.next;
				temp = null;
				deleteAlt(node.next);
			}else
				return node;
		}
		return node;
	}

	//Print linked list
	public static void printList(ListNode head) {
		while(head!=null){
			System.out.print(head.val + " ");

			head = head.next;
		}
	}
	
	//Remove node with value greater than head value
	public static void removeGreater(ListNode head){
		ListNode temp,next;

		temp = head;

		if(head == null){
			return;
		}

		ListNode priv = null;
		while(head.next!=null && head.val < head.next.val){
			head = temp.next;
			temp = head;
		}

		while(temp.next != null){
			next = temp.next;
			if(temp.val < next.val){

				temp = next;
				priv.next = temp;
				//temp = temp.next;
			}else{
				priv = temp;
				temp = next;
			}
		}
		//head = temp;
		while(head!=null){
			System.out.println(head.val);
			head = head.next;
		}

	}
	
	//create linked list from a array
	public static ListNode createList(int[] arr){
		ListNode head = null;
		ListNode temp = null;
		for(int i = 0;i<arr.length;i++){
			if(head == null){
				head = new ListNode(arr[i]);
				temp = head;
			}
			else{
				head.next = new ListNode(arr[i]);
				head = head.next;
			}
		}
		head.next = null;
		head = temp;
		return head;
	}
}
