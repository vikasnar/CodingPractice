package vikas.arraay;

/**
 * Created by VikasN on 9/28/16.
 */
public class maxElementinincreasingandDecreasingArray {
    public static void main(String... args){
//        int[] arr = new int[]{8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
//        int[] arr = new int[]{1,2,3,5};
//        int[] arr = new int[]{5,4,2,1};
        int[] arr = new int[]{2,3,4,5,2,1};
        System.out.println(maxElement(arr, 0, arr.length-1));
    }

    static int maxElement(int[] a, int l, int h){
        if(l==h) return a[l];
        int mid = (l+h)/2, x = a[mid];
        if(mid == 0 ){
            if(x < a[mid+1])
                return maxElement(a,mid+1,h);
            return a[mid];
        }
        if(mid == a.length-1){
            if(x > a[mid-1]) return a[mid];
            return maxElement(a, l, mid-1);
        }
        else{
            if(x > a[mid-1] && a[mid+1] > x)
                return maxElement(a, mid+1, h);
            else if(x > a[mid+1] && a[mid-1] > x)
                return maxElement(a, l, mid-1);
            else
                return x;
        }
    }
}
