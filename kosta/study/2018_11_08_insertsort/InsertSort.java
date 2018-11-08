package algo;

public class InsertSort {

	static public void InsertSortFunc(int[] arr){
		
		for(int i = 1; i < arr.length; ++i){
			// 앞에보다 뒤에가 더 크면 바꾼다
			for(int j = i-1; j >= 0 ; --j){
				if(arr[j] > arr[i]){
					int temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args){
		
		int[] arr = {7, 2, 1, 8, 5};
		InsertSortFunc(arr);
		
		for(int i = 0 ; i < arr.length; ++i){
			System.out.println(arr[i]+",");
		}
	}
}
