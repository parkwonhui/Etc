package baseball;

public class BaseballGame extends BaseGame{

	RandomInfo 	randomInfo;
	GameData 	masterGameData;
	GameData 	playerGameData;
	BufferReader bufferReader;
	int 		strike;
	int 		ball;
	boolean		gameContinue;
	int			searchCount;
	int			saveSesarchCount;
	boolean		bResultOpen = true;
	
	public BaseballGame(int count, int startValue, int endValue) {
		strike = 0;
		ball = 0;
		searchCount = 0;
		saveSesarchCount = -1;
		gameContinue = true;
		randomInfo = new RandomInfo(startValue, endValue);
		masterGameData = new GameData(count);
		playerGameData = new GameData(count);
		bufferReader = new BufferReader();
	}
	
	@Override
	public void Start() {
		System.out.println("★★★★ Baseball Game Start ★★★★");
		boolean bFirst = true;
		while(gameContinue) {
			if(true == bFirst) {
				Update();
				bFirst = false;
			}
			Input();
			Calculate();
			Draw();
		}
	}

	@Override
	public void Update() {
		int[] arr = GetMasterArr();
		for(int i = 0; i < arr.length; ++i) {
			if(true == bResultOpen)
				System.out.print(arr[i]);
			masterGameData.SetData(i, arr[i]);
		}
		if(true == bResultOpen)
			System.out.println();
	}
	
	@Override
	public void Input() {
		int value = UserInput();
		saveValue(value);
		AddSearchCount();
	}

	@Override
	public void Calculate() {
		this.strike = masterGameData.GetStrikeCount(playerGameData);
		this.ball = masterGameData.GetBallCount(playerGameData);
	}

	@Override
	public void Draw() {
		if(masterGameData.GetInputCount() != strike) {
			System.out.println(strike+"S"+ball+"B");
		}else{
			
			PrintResult();				
			gameContinue = false;
		}
	}
	
	public int[] GetMasterArr() {
		int count = masterGameData.GetInputCount();
		boolean bSame = true;
		int[] arr = new int[count];
		while(true) {
			for(int i = 0; i < count; ++i)
				arr[i] = randomInfo.GetRandom();
			
			if(true == IsSame(arr))
				break;
		}
		
		return arr;
	}
	
	public boolean IsSame(int[] data) {		
		int count = data.length;
		for(int i = 0; i < count; ++i) {
			for(int j = i+1; j < count; ++j) {
				
				if(i != j && data[i] == data[j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int UserInput() {
		int result = 0;
		while(true) {
			System.out.println(masterGameData.GetInputCount()+"자리 숫자를 입력해주세요");
			int value = bufferReader.Input();
			if(-1 == value) {
				System.out.println(masterGameData.GetInputCount()+"자리 숫자를 입력해주세요");
				continue;
			}
			
			int index = 1;
			int temp = 1;
			int count = 0;
			while(true) {
				result = value / (index*temp);
				count++;
				temp *= 10;
				
				if(result < 10) {
					result = value;
					break;		
				}
			}
			if(masterGameData.GetInputCount() == count)
				break;
		}
		
		return result;
	}
	
	public void saveValue(int value) {
		int count = masterGameData.GetInputCount();
		
		int index = 1;
		int temp = 1;
		for(int i = 0; i < count;) {
			index = index*temp;
			
			++i;
			if( i < count )
				temp *= 10;

		}
		for(int i = 0; i < count; ++i) {
			int share = value / temp;
			value -= (share * temp);
			temp *= 0.1;
			playerGameData.SetData(i, share);
		}
	}
	
	public void PrintResult(){
		
		System.out.println("축하합니다"+searchCount+"회만에 맞추셨습니다");
		
		if(searchCount <= 2)
			System.out.println("정답을 보기라도 한건가요?");
		else if(searchCount <= 5)
			System.out.println("엄청 빨리 맞추셨네요!");
		else if(searchCount <= 10) 
			System.out.println("그럭저럭 잘하셨네요");
		else if(searchCount <= 15)
			System.out.println("더 노력하세요");
		else if(searchCount <= 20)
			System.out.println("찍기 놀이를 하고 계신건가요?");
		
		System.out.println("★★★★★★★★  GameClear ★★★★★★★★");		
	}

	public void AddSearchCount() {
		++searchCount;
	}
	public void SetSearchCount(int value) {
		searchCount = value;
	}
	public int GetSearchCount() {
		return searchCount;
	}
	public void SetSaveSearchCount(int value) {
		saveSesarchCount = value;
	}
	public int GetSaveSearchCount() {
		return saveSesarchCount;
	}
}
