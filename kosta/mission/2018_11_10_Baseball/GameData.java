package baseball;

public class GameData {
	final private int inputCount;			// 입력받을 갯수
	final private int[] arrInputValue;	// 숫자저장
	
	public GameData(int inputCount) {
		this.inputCount = inputCount;
		arrInputValue = new int[inputCount];
	}
	
	public int GetStrikeCount(GameData gameData) {
		
		int strike = 0;
		for(int i = 0; i < inputCount; ++i) {
			if(arrInputValue[i] == gameData.GetInputValue(i)) {
				++strike;
			}
		}
		
		return strike;
	}
	
	public int GetBallCount(GameData gameData) {
		int ball = 0;
		for(int i = 0; i < inputCount; ++i) {
			for(int j = 0; j < inputCount; ++j) {
				if(i != j && arrInputValue[i] == gameData.GetInputValue(j)) {
					++ball;
				}
			}
		}
		return ball;
	}

	public void SetData(int index, int value) {
		arrInputValue[index] = value;
	}
	
	public int[] GetInputValue() {
		return arrInputValue;
	}
	
	public int GetInputValue(int index) {
		return arrInputValue[index];
	}
	
	public int GetInputCount() {
		return inputCount;
	}
}
