package kosta.video;

public class SpecialMember extends GeneralMember{
	
	private int point;
	
	public SpecialMember() {
	}

	public SpecialMember(String id, String name, String addr, Video video, int point) {
		super(id, name, addr, video);
		this.point = point;
	}

	public void setPoint(int p){
		point = p;
	}

	public int getPoint(){
		return point;
	}
	
	public void ShowPoint(){
		System.out.println("ShowPoint:"+point);
	}
	
	protected void ShowInfo(){	
		super.ShowInfo();
		System.out.println("회원이 보너스 포인터 적립:"+point);
	}
	
	

}
