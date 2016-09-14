package vikas.arraay;

/**
 * Created by VikasN on 8/24/16.
 */
public class MergeArrays {
    private final static int NA = -1;

    public static void main(String... args){
        int[] big = new int[]{2,NA,7,NA,NA,10,NA};
        int[] small = new int[]{5,8,12,14};
        Utils.print(big);
        Utils.print(small);
        merge(big,small);
        Utils.print(big);
    }

    public static int[] merge(int[] big, int[] small){
        int len = big.length;
        int j = 0;
        for(int i = 0 ; i < len ; i++){
            if(big[i] == NA){
                big[i] = small[j++];
            }
        }
        return big;
    }
}
