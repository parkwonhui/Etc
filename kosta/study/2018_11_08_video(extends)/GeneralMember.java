package kosta.video;

public class GeneralMember {
	
	private String id;
	private String name;
	private String addr;
	private Video video;
	
	public GeneralMember(){
		
	}
	
	public GeneralMember(String id, String name, String addr, Video video) {	
		super();
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.video = video;
	}
	
	protected void ShowInfo(){
		System.out.println();
		System.out.println("회원의 아이디:"+id);
		System.out.println("회원의 이름:"+name);
		System.out.println("회원의 주소:"+addr);

		if(null == video)
			System.out.println("빌린 비디오가 없음");
		else
		{
			System.out.println("회원이 대여한 비디오 번호:"+video.getNo());
			System.out.println("회원이 대여한 비디오 제목:"+video.getTitle());
			System.out.println("회원이 대여한 비디오 주인공:"+video.getActor());
		}
	}
	
	
}
