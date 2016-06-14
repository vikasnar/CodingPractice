package vikas.arraay;

/**
 * Created by vnarasimhamurt on 6/8/16.
 */
public class MaxSumNoAdjacentElements {
    public static void main(String[] args){
        int[] arr = new int[]{5,  5, 10, 40, 50, 35};
        System.out.println(maxSumNoAdjacent(arr));
    }

    static int maxSumNoAdjacent(int[] arr) throws NullPointerException{
        if(arr == null || arr.length == 0)
            throw new NullPointerException();
        else{
            int prevPrev=0, prev=0, cur;
            for(int i = 0 ; i < arr.length ; i++){
                cur = Math.max(prev,prevPrev+arr[i]);
                prevPrev = prev;
                prev = cur;
            }
            return prev;
        }
    }
}
