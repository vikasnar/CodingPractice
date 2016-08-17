package vikas.arraay;

/**
 * Created by vnarasimhamurt on 5/31/16.
 */
public class LargestSumContiguousSubArray {
    public static void main(String[] args){
        int[] arr = new int[]{ 4, -1, -2, 1, 5, -3};
        printLargestSUmSubArray(arr);
    }

    public static void printLargestSUmSubArray(int[] arr){
        if(arr == null || arr.length == 0) return;
        int cur_max = 0, max = 0, si = 0;
        for(int i = 0; i<arr.length;i++){
            cur_max = cur_max + arr[i];
            if(arr[i]>cur_max){
                cur_max = arr[i];
                si = i;
            }
            if(cur_max > max){
                max = cur_max;
            }
        }
        System.out.println("Max Sum = "+max);
        for(int i = si ;i<arr.length && max > 0;i++){
            System.out.print(arr[i]+" ");
            max -= arr[i];
        }
    }
}
