package algo;

public class InsertSort {

	static public void InsertSortFunc(int[] arr){	
		for(int i = 1; i < arr.length; ++i){
			int key = arr[i];									// 비교key(value)를 저장해야한다. index로 접근하면 정렬되면서 index가 가르키는 값이 변하기 때문이다
			for(int j = i-1; j >= 0 && arr[j] > key ; --j){		// j가 0보다 크거나 같고 비교배열이 key보다 크면 반복한다
					int t = arr[j];								// key value가 더 크다면 그 앞 배열은 다 정리가 되어있다
					arr[j] = key;
					arr[j+1] = t;
			}
		}
	}
	
	static public boolean Check(int[] arr){
		for(int i = 1; i < arr.length - 1; ++i){
			if(arr[i] > arr[i+1])
				return false;
		}
		
		return true;
	}
		
	public static void main(String[] args){
		
		int[] arr = {5, 10, 9, 2, 1, 3};
		InsertSortFunc(arr);
		
		assert Check(arr) : "No Sort";

		for(int i = 0 ; i < arr.length; ++i){
			System.out.println(arr[i]+",");
		}
	}
}
