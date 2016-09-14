package vikas.arraay;

/**
 * Created by VikasN on 9/6/16.
 */
public class Reverse {
    public static void main(String... args){
        Utils.print(reverse(new int[]{5,4,3,2,1}));
    }

    static int[] reverse(int[] arr){
        int l = arr.length;
        if(arr == null || l == 0) return null;
        for(int i =0;i<l;i++){
            int temp = arr[i];
            arr[i] = arr[l-i-1];
            arr[l-i-1] = temp;
        }
        return arr;
    }
}
