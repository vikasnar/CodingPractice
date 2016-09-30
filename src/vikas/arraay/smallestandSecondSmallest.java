package vikas.arraay;

/**
 * Created by VikasN on 9/28/16.
 */
public class smallestandSecondSmallest {
    public static void main(String... args){
        Utils.print(leastTwo(new int[]{12, 13, 1, 10, 34, 5}));
    }
    public static int[] leastTwo(int[] a){
        int s = Integer.MAX_VALUE,ss = s, n= a.length;
        for(int i=0;i<n;i++){
            if(a[i] < ss){
                if(a[i]<s){
                    ss = s;
                    s = a[i];
                }
                else {
                    ss = a[i];
                }
            }
        }
        return new int[]{s,ss};
    }
}
