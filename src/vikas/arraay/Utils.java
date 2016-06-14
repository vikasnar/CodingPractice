package vikas.arraay;

/**
 * Created by vnarasimhamurt on 6/7/16.
 */
public class Utils {

    public static void print(int[] arr){
        if(arr == null || arr.length ==0)
            System.out.println("Empty Array!!");
        for(int i = 0;i< arr.length;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static boolean isEmpty(int[] arr) {
        if(arr == null || arr.length == 0)
            return true;
        return false;
    }
}
