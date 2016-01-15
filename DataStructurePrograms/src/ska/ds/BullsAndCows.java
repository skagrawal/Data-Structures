/**
 * 
 */
package ska.ds;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * https://leetcode.com/problems/bulls-and-cows/
 */
public class BullsAndCows {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getHint("1807", "7810"));
		System.out.println(getHint("1", "0"));
		
	}
	
	public static String getHint(String secret, String guess) {
		 
        int A = 0;
        int B = 0;
        int [] arr1 = new int[10];
        int [] arr2 = new int[10];
        int temp;
        for(int i = 0;i<arr1.length;i++){
            arr1[i] = 0;
            arr2[i] = 0;
        }
        
        for(int i = 0;i< secret.length();i++){
                if(secret.charAt(i) == guess.charAt(i)){
                    A++;
                }else{
                    temp = secret.charAt(i) - '0';
                    arr1[temp] = arr1[temp]+1;
                    temp = guess.charAt(i) - '0';
                    arr2[temp] = arr2[temp]+1;
                }
        }
        
        for(int i = 0;i<arr1.length;i++){
            B += Math.min(arr1[i],arr2[i]);
        }
        
        return A+"A"+B+"B";
    }

}
