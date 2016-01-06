package ska.ds;

public class checkPalindromLL {

	
	static NodeLL left;
	public static void main(String[] args) {
		int[] arr1 = {1,1,2,2,1};
		
		NodeLL head = createList(arr1);
		left = head;

		//while(head.next != null){
			//System.out.println(head.value);
			//head = head.next;
		//}
		
		//head = reverseLL(head);
		
		boolean palindromeUtil = isPalindromeUtil( head);
		System.out.println("isPalidnrodme = "+palindromeUtil);
	}

	
	
	private static boolean isPalindromeUtil(NodeLL right)
	{
	   /* stop recursion when right becomes NULL */
	   if (right == null)
	      return true;
	   
	   if (!isPalindromeUtil(right.next))
	      return false;
	 
	   boolean isEqual = false;
	   if(right.value == left.value){
		    isEqual = true;
	   }
	   // Move left 
	   left = left.next;
	   return isEqual;
	}
	
	
	private static NodeLL reverseLL(NodeLL head) {

		NodeLL curr,priv,next;
		priv = null;
		curr = head;
		next = curr.next;
		head.next = null;
		while(next != null){
			curr.next = priv;
			priv = curr;
			curr = next;
			next = next.next;
		}
		curr.next = priv;
		return curr;
	}


	public static NodeLL createList(int[] arr){
		NodeLL head = null;
		NodeLL temp = null;
		for(int i = 0;i<arr.length;i++){
			if(head == null){
				head = new NodeLL(arr[i]);
				temp = head;
			}
			else{
				head.next = new NodeLL(arr[i]);
				head = head.next;
			}
		}
		head.next = null;
		head = temp;
		return head;
	}
}



class NodeLL{
	int value = 0;
	NodeLL next = null;

	public NodeLL(int value) {
		this.value = value;
		next = null;
	} 

}