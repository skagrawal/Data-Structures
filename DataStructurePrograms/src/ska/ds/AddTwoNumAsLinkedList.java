/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * https://leetcode.com/problems/add-two-numbers/
 * Medium level problem
 */
public class AddTwoNumAsLinkedList {


	public static void main(String[] args) {

		int [] arr = {1,2,3,4};
		ListNode l1 = ListNode.createList(arr);
		ListNode l2 = ListNode.createList(arr);
		ListNode res = addTwoNumbers(l1, l2);
		ListNode.printList(res);
		
	}

	
	 public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        if(l1 == null){
	            return l2;
	        }
	        if(l2 == null){
	            return l1;
	        }
	        
	        ListNode ret = l1;
	        ListNode temp = l1;
	        int len1 = 0,len2 = 0;
	        while(temp != null){
	            len1++;
	            temp = temp.next;
	        }
	        
	        temp = l2;
	        int carry = 0;
	        while(temp != null){
	            len2++;
	            temp = temp.next;
	        }
	        
	        ListNode prev = l1;
	        int min = Math.min(len1 ,len2);
	        // Add all elements until the smaller number
	        for(int i = 0; i < min; i++){
	            int sum = l1.val;
	            sum = sum + l2.val;
	            sum += carry;
	            l1.val = sum%10;
	            
	            carry = sum/10;
	            prev = l1;
	            l1 = l1.next;
	            l2 = l2.next;
	            
	        }
	        
	        //Add remaining numbers
	        if(len1 == len2){
	            if(carry > 0){
	                prev.next = new ListNode(carry);
	            }
	            return ret;
	        }else if (len1 > len2){
	            int dif = len1-len2;
	            for(int i = 0; i < dif; i++){
	                int sum = l1.val + carry;
	                l1.val = sum%10;
	                carry = sum/10;
	                prev = l1;
	                l1 = l1.next;
	            }
	            if(carry > 0){
	                prev.next = new ListNode(carry);
	            }
	            return ret;
	        }else{
	            prev.next = l2;
	            int dif = len2-len1;
	            for(int i = 0; i < dif; i++){
	                int sum = l2.val + carry;
	                l2.val = sum%10;
	                carry = sum/10;
	                prev = l2;
	                l2 = l2.next;
	            }
	            if(carry > 0){
	                prev.next = new ListNode(carry);
	            }
	            return ret;
	        }
	        
	    }
}
