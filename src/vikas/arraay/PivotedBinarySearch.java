package vikas.arraay;

/**
 * Created by vnarasimhamurt on 6/2/16.
 */
public class PivotedBinarySearch {
    public static void main(String... args){
        PivotedBinarySearch pivotedBinarySearch = new PivotedBinarySearch();
        int[] arr = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2};
        System.out.println(pivotedBinarySearch.pivotedSearch(arr, 6));
    }

    int pivotedSearch(int[] arr, int x){
        if(arr != null && arr.length != 0){
            int p = findPivot(arr);
            int f = binarySearch(arr, p, arr.length-1, x);
            if(f != -1) return f;
            else return binarySearch(arr, 0, p-1, x);
        }
        return -1;
    }

    public int findPivot(int[] arr){
        int i = 0, j = arr.length-1, m;
        while(i<j){
            m = (i+j)/2;
            if(arr[m]>arr[m+1]) return m+1;
            if(arr[m-1]>arr[m]) return m;
            else if(arr[m]>arr[j]) i = m+1;
            else j = m+1;
        }
        return 0;
    }

    public int binarySearch(int[] arr, int i, int j, int x){
        if (arr == null || arr.length == 0) return -1;
        else{
            while(i<=j){
                int m;
                m = (i+j)/2;
                if(arr[m] == x) return m;
                else if(arr[m]>x) j = m-1;
                else i = m+1;
            }
        }
        return -1;
    }

}
