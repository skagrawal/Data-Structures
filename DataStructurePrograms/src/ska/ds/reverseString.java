package ska.ds;

public class reverseString {

	
	public static void main(String s[]) {
		  StringBuilder stringBuilder = new StringBuilder();
		  String string = "a if this fdf fdffffff";
		  
//	        String[] words = string.split(" ");
//
//	        for (int j = 0; j < words.length; j++) {
//	            stringBuilder.append(new StringBuilder(words[j]).reverse()).append(' ');
//	        }
	        //System.out.println("Reverse words: " + stringBuilder.reverse());
	        
	        //System.out.println(new StringBuilder(string).reverse());
	        
	        
	        String s1 = new StringBuilder(string.replaceAll(" ", "")).reverse().toString();
	        StringBuilder res = new StringBuilder(s1);
	        
	        System.out.println(string.indexOf(" ",1));
	        
	        System.out.println(res.toString());
	        int j =1;
	        for(int i = 0;i<s1.length();){
	        	if(j>i)
	        	{
	        		i = string.indexOf(" ",i)+1;
		        	System.out.println(i);
		        	res.insert(i," ");
		        	j=i;
	        	}
	        	
	        }
	        System.out.println(res.toString());
	}
	
}
