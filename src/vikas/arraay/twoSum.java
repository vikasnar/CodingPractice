package vikas.arraay;

import java.util.HashSet;

class twoSum{
	public static void main(String... args){
		int[] arr = new int[]{2,5,7,8,12,7};
		twoSum a = new twoSum();
		a.printSums(arr, 10);
		int A[] = {1, 4, 45, 6, 10, 8};
        int n = 16;
        a.printSums(A,n);
	}	

	public void printSums(int[] arr, int x){
		HashSet<Integer> set = new HashSet<>();
		for(int i : arr){
			if(set.contains(x-i)){
				System.out.println("Two Numbers are " + (x-i) +" & "+i);
			}
			else{
				set.add(i);
			}
		}
	}
}
