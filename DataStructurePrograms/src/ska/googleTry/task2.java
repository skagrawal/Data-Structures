package ska.googleTry;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class task2 {



	public static void main(String ar[]) {
		String input = "dir1\n dir1133\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file.gif1\n dir31\n adira\n  bdirb";

		
		
		//input = "awqdwed\n b\n  rrr\n cfff\n  c";
		Stack<Node> stack = new Stack<Node>();
		System.out.println(input);
		//System.out.println("-------------------\n\n");
		Node node;

		String [] split = input.split("\n");

		int old = 0;
		int curr = 0;
		List<Node> temp;
		String str = "";
		Node parent = null;
		Node root = new Node("/", new LinkedList<Node>(), 1);
		for(int i = 0 ; i <split.length; i++){
			str = split[i].toLowerCase();

			curr = spaceCount(str);
			if(curr == 0 ){
				List<Node> leaves = new LinkedList<Node>();
				node = new Node(str, leaves, 1 + str.length()-curr);
				stack.push(node);
				parent = node;
				temp = root.getLeaves();
				temp.add(node);
				root.setLeaves(temp);
				continue;
			}

			else{
				if(curr > old){
					parent = stack.pop();
				}

				if(str.endsWith(".jpeg") || str.endsWith(".png") || str.endsWith(".gif") || !str.contains(".")){
					node = new Node(str, new LinkedList<Node>(),1+parent.pathLength + str.length()-curr);
					temp = parent.getLeaves();
					temp.add(node);
					parent.setLeaves(temp);
					old = curr;
					if(!str.contains(".")){
						stack.push(node);
					}
				}
			}

			if(old>curr){
				stack.pop();
			}
		}


		printTree(root);
		//System.out.println(co +" -- count of tree trversal");
		System.out.println(max  +" -- is Max length for one image file");

	}
	static int co = 0;
	static int max = 0;
	private static void printTree(Node head) {
		if(head == null) {
			return;
		}

		for(Node n : head.getLeaves()){
			String str = n.value.toLowerCase();
			if(str.endsWith(".jpeg") || str.endsWith(".png") || str.endsWith(".gif"))
			{
				if(n.pathLength>max){
					max = n.pathLength;
				}
			}
			printTree(n);
		}	
		return;
	}


	public static int spaceCount(String str){
		int index = 0;
		for(index = 0; index < str.length(); index++){
			if(str.charAt(index) != ' '){
				break;
			}
		}
		return index;
	}
}


class Node {
	String value;
	private List<Node> leaves = new LinkedList<Node>();
	int pathLength;


	public Node( String value,List<Node> leaves, int pathLength) {
		super();
		this.leaves = leaves;
		this.value = value;
		this.pathLength = pathLength;
	}

	public List<Node> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Node> leaves) {
		this.leaves = leaves;
	}



}
