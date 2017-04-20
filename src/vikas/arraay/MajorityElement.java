package vikas.arraay;

import javax.lang.model.element.Element;
import java.util.Arrays;

public class MajorityElement {
	public static void main(String[] args){
		int[] arr = new int[]{3,3,4,3,4,4,2,4,4};
		checkIfElementisMajority(4,arr);
		int candidate = arr[0];
		int count = 0;
		for(int i : arr){
			if(i == candidate) count++;
			else {
				if(count == 0) candidate = i;
                if(count > 0) count-- ;
			}
		}
		// The candidate that popped up is tested.
		count = 0;
		for(int i : arr){
			if(i == candidate) count++;
		}
		if(count >= arr.length/2)
			System.out.println("Majority Element is "+candidate);
		else
			System.out.println("Not Found!");
	}

	static boolean checkIfElementisMajority(int x, int[] a){
		if (a == null || a.length == 0) return false;
		Arrays.sort(a);
		int l = 0, h = a.length-1, k = 0;
		while(l>=h){
			int mid = (l+h)/2;
			if(a[mid] == x){
				if(mid == 0 || a[mid] > a[mid-1]){
					k = mid;
					break;
				}
				h = mid - 1;
			}else{
				if(a[mid] > x){
					h = mid-1;
				}
				else{
					l = mid+1;
				}
			}
		}
		if(k + a.length/2 + 1 < a.length && a[ k + a.length/2+1] == x) {
			System.out.println("Element "+x+" appears more than "+a.length/2+" times");
			return true;
		}
		System.out.println("Element "+x+"does not appears more than "+a.length/2+" times");
		return false;
	}
}