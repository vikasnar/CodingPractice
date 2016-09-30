package vikas.arraay;

/**
 * Created by VikasN on 9/28/16.
 */
public class MaxMininArray {
    public static void main(String... args){
        int arr[] = {1000, 11, 445, 1, 330, 3000};
        Utils.print(maxMin(arr,0,arr.length-1));
    }

    static int[] maxMin(int[] a,int l, int h){
        if(l==h) return new int[]{a[l],a[h]};
        if(h-l == 1) return (a[l] > a[h]) ? new int[]{a[l],a[h]} : new int[]{a[h],a[l]};
        else{
            int[] left = maxMin(a,l,(l+h)/2);
            int[] right = maxMin(a,(l+h)/2, h);
            int tMax, tMin;
            tMax = (left[0]>right[0])? left[0]:right[0];
            tMin = (left[1]<right[1])? left[1]:right[1];
            return new int[]{tMax,tMin};
        }
    }
}
