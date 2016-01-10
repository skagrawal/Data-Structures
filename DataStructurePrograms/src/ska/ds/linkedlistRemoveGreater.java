package ska.ds;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Lock;


public class linkedlistRemoveGreater {

	public static void main(String[] args) {

		int[] arr1 = {1,2,3,24,11,12,13,14};
		int[] arr2 = {11,12,13,14};


		
		
		//removeGreater(head);
		//printList(createList(arr2));
		//Node node = mergeSorted(createList(arr1), createList(arr2));
		//printList(node);
		//printList(deleteAlt(createList(arr1)));
		//System.out.println(new Random().nextInt(7));

		//solve(44, "A", "B", "C");
		//System.out.println(makeChange(6, 25));
		
		String Demo = "This is a string-th at,we want to tokenize";

        StringTokenizer Tok = new StringTokenizer(Demo,"- ,");
        int n=0;
        char c = 'A';
        String s = "hi shubham";
        String s1 = "hi shubham";
        //System.out.println(atoi("1700123687"));
        Integer k = 10;
        Integer l = 10;
        System.out.println(s.hashCode()+" "+s1.hashCode());
        //System.out.println(s.compareTo("hi uhubham"));;
        //while (Tok.hasMoreElements()){
        	
               // System.out.println("" + ++n +": "+Tok.nextElement().toString());
        //}
        
        Queue<String> queue = new PriorityQueue<String>(20,new Comparator<String>(){
        	@Override
        	public int compare(String f1, String f2){
        		return f1.compareTo(f2);
        	}
        });
        //Collections.sort(queue);
        queue.add("A");
        queue.add("C");
        queue.add("B");
        queue.add("D");
        for(String qs : queue){
        	System.out.println(qs);
        }
	}

	
	public static int atoi(String s){
		int sum = 0;
		char c= ' ';
		int i=0;
		if(s.charAt(0) == '-') i++;
		for(;i< s.length();i++){
			c = s.charAt(i);
			sum *= 10;
			sum += (c-'0');
			
			
		}
		return sum;
		
	}
	public static int makeChange(int n, int denom) {
		int next_denom = 0;
		switch(denom){
		case 25:
			next_denom= 10;
			break; 
		case 10:
			next_denom = 5;
			break; 
		case 5:
			next_denom = 1;
			break;
		case 1:
			return 1;
		}
		int ways = 0;
		for(int i =0; i *denom <=n; i++) {
			//System.out.print(next_denom);
			ways  = ways + makeChange(n - i*denom, next_denom);
			//System.out.println();
		}
		return ways; 
	}



	public static void solve(int n, String start, String auxiliary, String end) {
		if (n == 1) {
			System.out.println(start + " -> " + end);
		} else {
			solve(n - 1, start, end, auxiliary);
			System.out.println(start + " -> " + end);
			solve(n - 1, auxiliary, start, end);
		}
	}

	public static double sqrt(double d) {
		double t;

		double squareRoot = d / 2;

		do {
			t = squareRoot;
			squareRoot = (squareRoot + (d / squareRoot)) / 2;
		} while ((t - squareRoot) != 0);

		return squareRoot;
	}

	private static Node deleteAlt(Node node) {

		Node temp;
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

	public static Node mergeSorted(Node head1, Node head2){
		if(head1 == null) 
			return head2;
		else if(head2 == null) 
			return head1;
		Node node;
		Node result = null;
		if(head1.value < head2.value){
			node = new Node(head1.value);
			result = head1;
			result.next = mergeSorted(head1.next, head2);
			//head1 = head1.next;
		}
		else if(head1.value > head2.value){
			node = new Node(head2.value);
			result = head2;
			result.next = mergeSorted(head1, head2.next);
			//head2 = head2.next;
		}
		return result;
	}


	public static void removeGreater(Node head){
		Node temp,next;

		temp = head;

		if(head == null){
			return;
		}

		Node priv = null;
		while(head.next!=null && head.value < head.next.value){
			head = temp.next;
			temp = head;
		}

		while(temp.next != null){
			next = temp.next;
			if(temp.value < next.value){

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
			System.out.println(head.value);
			head = head.next;
		}

	}


	private static void printList(Node head) {
		while(head!=null){
			System.out.print(head.value + " ");

			head = head.next;
		}
	}

	public static Node createList(int[] arr){
		Node head = null;
		Node temp = null;
		for(int i = 0;i<arr.length;i++){
			if(head == null){
				head = new Node(arr[i]);
				temp = head;
			}
			else{
				head.next = new Node(arr[i]);
				head = head.next;
			}
		}
		head.next = null;
		head = temp;
		return head;
	}

}


class Node{
	int value = 0;
	Node next = null;

	public Node(int value) {
		this.value = value;
		next = null;
	} 

}
