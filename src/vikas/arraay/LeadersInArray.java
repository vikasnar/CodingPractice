package vikas.arraay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnarasimhamurt on 6/8/16.
 */
public class LeadersInArray {

    public static void main(String... args){
        printLeaders(new int[]{16,17,3,4,5,2});
    }
    public static void printLeaders(int[] arr){
        if(Utils.isEmpty(arr)) return;
        int n = arr.length;
        int highest = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for(int i = n-1 ; i >= 0 ; i--){
            if(arr[i] > highest){
                list.add(arr[i]);
                highest = arr[i];
            }
        }
        int[] res = new int[list.size()];
        int k = 0;
        for(Integer i : list){
            res[k++] = i;
            System.out.println(i);
        }
    }
}
