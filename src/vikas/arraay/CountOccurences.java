package vikas.arraay;

/**
 * Created by VikasN on 9/28/16.
 */
public class CountOccurences {
    public static void main(String... args){
        int[] a = new int[]{1,1,2,2,2,2,3,4};
        System.out.println(count(a,2));
    }

    static int count(int[] a, int x){
        int fI = firstIndex(a,0,a.length-1,x);
        if(fI == -1) return -1;
        int lI = lastIndex(a,0,a.length-1,x);
        return lI - fI + 1;
    }
    static int firstIndex(int[] a, int l, int h, int x){
        if(l>h) return -1;
        int mid = (l+h)/2;
        if((mid == 0 || x > a[mid-1]) && a[mid] == x) return mid;
        if(x > a[mid]) return firstIndex(a, mid+1,h, x);
        else return firstIndex(a, l, mid-1, x);
    }
    static int lastIndex(int[] a, int l, int h, int x){
        if(l>h) return -1;
        int mid = (l+h)/2;
        if((mid == h || x < a[mid+1]) && a[mid] == x) return mid;
        if(x >= a[mid]) return lastIndex(a, mid+1,h, x);
        else return lastIndex(a, l, mid-1, x);
    }
}
