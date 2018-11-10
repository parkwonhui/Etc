package baseball;

import java.util.Random;

public class RandomInfo {

	Random random;
	private int start;
	private int end;
	
	public RandomInfo(int start, int end) {
		random = new Random();
		this.start = start;
		this.end = end;
	}
	
	public void SetRandomRound(int start, int end) {
		this.start = start;
		this.end = end;	
	}
	
	public int GetRandom() {
		return random.nextInt(9)+this.start;
	}
}
