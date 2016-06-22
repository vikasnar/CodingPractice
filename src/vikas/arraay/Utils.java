package vikas.arraay;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;

/**
 * Created by vnarasimhamurt on 6/7/16.
 */
public class Utils {

    static int[] array;
    static int[] temp;

    public static void print(int[] arr){
        if(arr == null || arr.length ==0)
            System.out.println("Empty Array!!");
        for(int i = 0;i< arr.length-1;i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println(arr[arr.length-1]);
    }

    public static boolean isEmpty(int[] arr) {
        if(arr == null || arr.length == 0)
            return true;
        return false;
    }

    public static int[] sort(int[] arr){
        array = arr;
        temp = new int[arr.length];
        mergeSort(0, arr.length-1);
        return array;
    }

    private static void mergeSort( int l, int r){
        if(l >= r) return;
        int m = (l+r)/2;
        mergeSort(l,m);
        mergeSort(m+1,r);
        merge(l,m+1,r);
    }

    private static void merge(int l, int m, int r){
        for(int i = l; i <= r ; i ++){
            temp[i] = array[i];
        }
        int i = l, j = m, k = l;
        while(i < m && j <= r){
            if(temp[i]<temp[j]){
                array[k++] = temp[i++];
            }
            else{
                array[k++] = temp[j++];
            }
        }
        while(i < m){
            array[k++] = temp[i++];
        }
        while(j <= r){
            array[k++] = temp[j++];
        }
    }


}
