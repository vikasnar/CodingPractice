package vikas.arraay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vnarasimhamurt on 6/13/16.
 */
public class SortOnFrequency {
    public static void main(String[] args){
        int[] arr = new int[]{2, 5, 2, 8, 5, 6, 8, 8};
        Utils.print(arr);
        sortOnFrequency(arr);
    }

    public static int[] sortOnFrequency(int[] arr){
        if(arr == null || arr.length < 2)
            return arr;
        List<Tuple> elements = new ArrayList<>();
        int i;
        for(i = 0;i<arr.length;i++){
            elements.add(new Tuple(arr[i],0, i));
        }
        Collections.sort(elements, new stablesort());
        elements.get(0).count = 1;
        for(i = 1 ;i< arr.length;i++){
            if(elements.get(i).val == elements.get(i-1).val){
                elements.get(i).count += elements.get(i-1).count + 1;
                elements.get(i-1).count = -1;
            }
            else{
                elements.get(i).count = 1;
            }
        }
        Collections.sort(elements, new customSort());
        i = 0;
        for(Tuple t : elements){
            if(t.count != -1)
                System.out.print(t.val+" ");
        }
        return arr;
    }

    static class Tuple implements Comparable{
        int val;
        int count;
        int idx;
        public Tuple(int val, int count, int i){
            this.val = val;
            this.count = count;
            this.idx = i;
        }

        public int compareTo(Object o) {
            if(o instanceof Tuple){
                Tuple T = (Tuple) o;
                if(T.val > this.val)
                    return -1;
                else if(T.val < this.val)
                    return 1;
                else{
                    if(T.idx > this.idx)
                        return 1;
                    else
                        return -1;
                }
            }
            return 0;
        }
    }

    public static class stablesort implements Comparator<Tuple>{
        public int compare(Tuple o1, Tuple o2) {
            return o1.compareTo(o2);
        }
    }

    public static class customSort implements Comparator<Tuple>{
        public int compare(Tuple o1, Tuple o2)
        {
            if(o1.count < o2.count) return 1;
            else if(o1.count > o2.count) return -1;
            else {
                if (o1.idx > o2.idx)
                    return 1;
                else
                    return -1;
            }
        }
    }

}
