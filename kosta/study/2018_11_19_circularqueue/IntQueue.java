package kosta.algorithm;

public class IntQueue {
	private int max;			// 큐의 용량
	private int front;			// 첫 번째 요소 커서
	private int rear;			// 마지막 요소 커서, max랑 만나면 0으로 돌아가기
	private int num;			// 현재 데이터 수
	private int[] que;			// 큐 본체

	// 실행 시 예외：큐가 비어 있음
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException() { }
	}

	// 실행 시 예외：큐가 가득 참
	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException() { }
	}

	// 생성자
	public IntQueue(int capacity) {
		num = front = rear = 0;
		max = capacity;
		try {
			que = new int[max];				// 큐 본체용 배열을  생성
		} catch (OutOfMemoryError e) {		// 생성할 수 없음
			max = 0;
		}
	}

	// 큐에 데이터를 인큐
	public int enque(int x) throws OverflowIntQueueException {
		// 가득 찬 것 체크
		if(true == isFull()){
			throw new OverflowIntQueueException();
		}

		que[rear++] = x;		// 값을 저장한 다음 nextrear을 가르킨다.
		rear %= max;			// 배열 최대 값을 뛰어넘을 수도 있으므로 나머지 연산을 해서 최대 max값이 되도록 한다
		++num;
		return 0;
	}

	// 큐에서 데이터를 디큐
	public int deque() throws EmptyIntQueueException {
		if(true == isEmpty()){
			throw new EmptyIntQueueException();
		}
		
		int value = que[front++];
		front %= max;
		--num;
		return value;
	}

	// 큐에서 데이터를 피크 (프런트 데이터를 들여다봄)
	public int peek() throws EmptyIntQueueException {
		if(true == isEmpty()){
			throw new EmptyIntQueueException();
		}
		
		int value = (front-1)%max;
		return que[value];
	}

	// 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(int x) {
		if(true == isEmpty())
			return -1;
	
		// num만큼 돌자
		int frontIndex = front;
		for(int i = 0; i < num; ++i){
			if(x == que[frontIndex%max])
				return frontIndex;
			
			frontIndex = (frontIndex+1)%max;
		}
	
		return -1;											// 검색 실패
	}

	// 큐를 비움
	public void clear() {
		for(int i = 0; i < max; ++i){
			que[i] = 0;
		}
		
		num = front = rear = 0;
	}

	// 큐의 용량을 반환
	public int capacity() {
		return max;
	}

	// 큐에 쌓여 있는 데이터 수를 반환
	public int size() {
		return num;
	}

	// 큐가 비어 있나요?
	public boolean isEmpty() {
		return num <= 0;
	}

	// 큐가 가득 찼나요?
	public boolean isFull() {
		return num >= max;
	}

	// 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력
	public void dump() {
		if( true == isEmpty() ){
			System.out.println("데이터가 없습니다");
			return;
		}
			
		int frontIndex = front;
		for(int i = 0; i < num; ++i){					// num만큼 반복한다
			System.out.println(i+":"+que[frontIndex]);	// frontindex%max를 하여 0~max 까지의 값만 출력되도록 한다
			frontIndex = (frontIndex+1)%max;					
		}
	}
}