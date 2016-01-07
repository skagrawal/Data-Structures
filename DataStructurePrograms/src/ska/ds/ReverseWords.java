package ska.ds;

public class ReverseWords {

	public static void main(String s[]) {
		System.out.println(reverseWordsUsingSplit("the sky is    blue     "));
		System.out.println(reverseWordsInPlace("the sky is    blue     "));

	}

	public static String reverseWordsUsingSplit(String s) {

		s = s.trim();
		String [] arr = s.split(" ");
		String temp;
		int start = 0;
		int end = arr.length-1;
		String str = "";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< arr.length; i++){
			
			if(i< end){
				temp = arr[i].trim();
				arr[i] = arr[end].trim();
				arr[end] = temp;
				end--;
			}
			str = arr[i].trim();
			if(str.length()>0)
			sb.append(str).append(" ");
		}

		return sb.toString().trim();


	}
	public static String reverseWordsInPlace(String s) {

		s = s.trim();
		char [] arr = s.toCharArray();

		int start = 0;
		int end = arr.length-1;
		char temp;
		while(start < end){
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}

		start = 0;
		for(int i = 0; i < arr.length ; i++){
			if(arr[i] == ' '){
				end = i-1;
				while(start<end){
					temp = arr[start];
					arr[start] = arr[end];
					arr[end] = temp;
					start++;
					end--;
				}
				start = i +1;
			}
		}
		end = arr.length-1;
		while(start<end){
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}

		return new String(arr);

	}
}

