package vikas.arraay;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LargestNumberFormedFromArray {

    /*
        Given an array of numbers, arrange them in a way that yields the largest value. For example, if the given
        numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the largest value. And if the given numbers
        are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives the largest value.
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1, 34, 3, 98, 9, 76, 45, 4};
        ArrayList<String> list = new ArrayList<>();
        for(int i : arr){
            list.add(String.valueOf(i));
        }
        Collections.sort(list, new lexSort());
        System.out.println(StringUtils.join(list,""));
    }

}
/*
    For each pair of numbers(converted to String for ease of manipulation) X and Y
    find if XY is the larger or YX. Sort the list based on this order.
 */
class lexSort implements Comparator<String> {
    private String X,Y;
    private String XY(){
        return this.X+this.Y;
    }
    private String YX(){
        return this.Y+this.X;
    }
    @Override
    public int compare(String x, String y) {
        X = x;
        Y = y;
        return YX().compareTo(XY());
    }
}
