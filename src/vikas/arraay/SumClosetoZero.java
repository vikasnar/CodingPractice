package vikas.arraay;

import java.util.Arrays;

/**
 * Created by VikasN on 9/28/16.
 */
public class SumClosetoZero {
    public static void main(String... args){
        Utils.print(sumclosetoZero(new int[]{-81, 60, -10, 70, -80,84, 85}));
    }
    static int[] sumclosetoZero(int[] a){
        Arrays.sort(a);
        int[] res = new int[2];
        int i=0,j=a.length-1, min = Integer.MAX_VALUE, sum, t;
        while(j>i){
            sum = a[i]+a[j];
            if((t = Math.abs(sum)) < min){
                min = t;
                res[0] = a[i];
                res[1] = a[j];
            }
            if(sum == 0){
                res[0] = a[i];
                res[1] = a[j];
            }
            if(sum > 0){
                j--;
            }
            else{
                i++;
            }
        }
        return res;
    }
}
