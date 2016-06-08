package vikas.arraay;


/**
 * Created by vnarasimhamurt on 6/2/16.
 */
public class RotateArray {

    public static void main(String[] args){
        int[] arr = new int[]{1,2,3,4,5,6,7};
        Utils.print(arr);
        rotate(arr,3);
        Utils.print(arr);
    }

    public static void rotate(int[] arr, int d){
        if(arr == null || arr.length == 0) return;
        int n = arr.length;
        if(d >= n) d = d % n;
        int temp,j;
        for(int i = 0 ;i < d ; i++){
            temp = arr[0];
            for(j = 0 ; j < n-1 ; j++){
                arr[j] = arr[j+1];
            }
            arr[j] = temp;
        }
        Utils.print(arr);
    }

}
