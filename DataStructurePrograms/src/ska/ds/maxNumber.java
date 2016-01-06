package ska.ds;
import java.util.Arrays;
import java.util.Comparator;

public class maxNumber {

    private static Integer[] VALUES = { 5, 2, 1, 9, 50, 56 };

    public static void main(String[] args) {
        Arrays.sort(VALUES, new Comparator() {

            @Override
            public int compare(Object lhs, Object rhs) {
                String v1 = lhs.toString();
                String v2 = rhs.toString();

                //System.out.println(v1+v2);
                return (v1 + v2).compareTo(v2 + v1) * -1;
            }
        });
    }

	
}    