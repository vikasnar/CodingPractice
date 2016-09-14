package vikas.arraay;

/**
 * Created by VikasN on 8/18/16.
 * Given an array of positive integers. All numbers occur even number of times except one number which
 * occurs odd number of times. Find the number in O(n) time & constant space.
 * Example
 * I/P = [1, 2, 3, 2, 3, 1, 3}
 * O/P = 3
 */
public class OddElement {
    public static void main(String... args){
        int res;
        int[] ele = new int[]{1, 2, 3, 2, 3, 1, 3};
        res = ele[0];
        for (int i = 1; i < ele.length; i++) {
            res = res ^ ele[i];
        }
        Utils.print(ele);
        System.out.println("Odd Element : "+String.valueOf(res));
    }
}
