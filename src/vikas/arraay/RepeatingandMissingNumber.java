package vikas.arraay;

import java.util.Arrays;

/**
 * Created by VikasN on 9/28/16.
 */
//Given an unsorted array of size n. Array elements are in range from 1 to n. One number from set {1, 2, …n} is missing
// and one number occurs twice in array. Find these two numbers.
public class RepeatingandMissingNumber {
    public static void main(String... args){
        Utils.print(repeatedandMissing(new int[]{1,2,2}));
    }

    static int[] repeatedandMissing(int[] a){
        int[] count = new int[a.length+1];
        Arrays.fill(a,0);
        for (int i = 0; i < a.length; i++) {
            count[a[i]] += 1;
        }
        int[] res = new int[2];
        for (int i = 1; i < count.length; i++) {
            if(count[i] == 0) res[0] = i;
            else if(count[i] == 2) res[1] = i;
        }
        return res;
    }
}
