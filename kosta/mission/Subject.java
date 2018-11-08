package mission;

public class Subject {
    private int kor;
    private int eng;
    private int mat;
    private int sum;
    private int avg;
    
	public Subject(int kor, int eng, int mat) {
		super();
		SetSubjectInfo(kor, eng, mat);
	}
	
	public int getKor() {
		return kor;
	}
	
	public int getEng() {
		return eng;
	}
	
	public int getMat() {
		return mat;
	}
	
	public int getSum() {
		return sum;
	}
	
	public int getAvg() {
		return avg;
	}
	
	public int sumSubject(int kor, int eng, int mat){
		return kor + eng + mat;
	}
	
	public int calAvg(int sum) {
		return (int)(sum/3.0f);	// float->int 변환 찾자
	}
	
	public void SetSubjectInfo(int kor, int eng, int mat){
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sum = sumSubject(kor, eng, mat);
		this.avg = calAvg(this.sum);
	}
    
}
