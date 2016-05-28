package vikas.arraay;

public class MajorityElement {
	public static void main(String[] args){
		int[] arr = new int[]{3,3,4,3,4,4,2,4,4};
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
}