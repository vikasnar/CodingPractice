package vikas.arraay;

import java.util.HashSet;

/**
 * Created by VikasN on 9/11/16.
 */
public class Expedia {
    public static void main(String... args){
        System.out.println(jumps(3,1));
        System.out.println(jumps(3,2));
        System.out.println(jumps(3,3));
        System.out.println(getminimumUniqueSum(new int[]{2,2,2}));
        System.out.println(getminimumUniqueSum(new int[]{1,2,3}));
        System.out.println(getminimumUniqueSum(new int[]{1,2,3,4,5,2}));
    }

    public static int jumps(int k, int j){
        int height = k;
        int count = 0;
        while(height != 0){
            if(height >= j){
                count ++;
                height -= j;
            }
            else{
                count ++;
                height --;
            }
        }
        return count;
    }

    public static int getminimumUniqueSum(int[] arr){
        HashSet<Integer> uniqueSet = new HashSet<>();
        int uniqueSum = 0;
        for(int i = 0 ; i < arr.length ;i++){
            if(uniqueSet.contains(arr[i])){
                int uNum = arr[i]+1;
                while(uniqueSet.contains(uNum)){
                    uNum ++;
                }
                uniqueSet.add(uNum);
                uniqueSum += uNum;
            }
            else{
                uniqueSet.add(Integer.valueOf(arr[i]));
                uniqueSum += arr[i];
            }
        }
        return uniqueSum;
    }
}
