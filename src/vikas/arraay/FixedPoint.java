package vikas.arraay;

/**
 * Created by VikasN on 9/28/16.
 */
public class FixedPoint {
    public static void main(String... args){
        System.out.println(fixedPoint(new int[]{-10, -5, -3, 3, 7, 9}, 0, 6));
    }

    static int fixedPoint(int[] a, int l, int h){
        if(l > h) return -1;
        int mid = (l+h)/2;
        if(mid == a[mid]) return mid;
        if(mid > a[mid]) return fixedPoint(a, mid+1, h);
        else return fixedPoint(a, l, mid-1);
    }
}
