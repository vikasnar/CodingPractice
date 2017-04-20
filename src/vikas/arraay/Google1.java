package vikas.arraay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by VikasN on 9/25/16.
 */
public class Google1 {
    public static void main(String... args){
        System.out.println(largest(623315));

    }

    static int largest(int X){
        ArrayList<Integer> roundedList = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();
        int temp = X;
        String _Xs  = String.valueOf(X);
        for(int i = 0;i<_Xs.length()-1;i++){
            int A = Integer.valueOf(_Xs.substring(i,i+1));
            int B = Integer.valueOf(_Xs.substring(i+1,i+2));
            int avg = (int) Math.ceil((A+B)/2);
            String res = _Xs.substring(0,i)+String.valueOf(avg)+_Xs.substring(i+2);
            roundedList.add(Integer.valueOf(res));
        }
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(roundedList,comparator);
        return roundedList.get(0);
    }
}
