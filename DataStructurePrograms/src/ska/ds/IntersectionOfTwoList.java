/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
public class IntersectionOfTwoList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ListNode headB = null;
		ListNode headA = null;
		getIntersectionNode(headA, headB);
	}


	static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		if(headA == null || headB == null){
			return null;
		}

		//Calculating the length of list
		int lenA = 0;
		ListNode temp = headA;
		while(temp.next != null){ 
			lenA++;
			temp = temp.next;
		}

		int lenB = 0;
		temp = headB;
		while(temp.next != null){
			lenB++;
			temp = temp.next;
		} 
		int abs = Math.abs(lenA-lenB);

		if(lenA > lenB){
			while(abs>0){
				headA = headA.next;
				abs--;
			}
			while(headA != null){
				if(headA == headB){
					return headA;
				}
				headA = headA.next;
				headB = headB.next;
			}

		}else{
			while(abs>0){
				headB = headB.next;
				abs--;
			}

			while(headB != null){
				if(headA == headB){
					return headA;
				}
				headA = headA.next;
				headB = headB.next;
			}

		}
		return null;
	}        

}
