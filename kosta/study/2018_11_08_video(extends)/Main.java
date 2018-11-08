package kosta.video;

public class Main {

	public static void main(String[] args) {

		
		Video video1 = new Video(1, "트렌스포머3", "서봉수");
		GeneralMember member1 = new GeneralMember("aaa", "홍길동", "동탄", video1);
		Video video2 = new Video(2, "쿵푸팬더2", "지성민");
		SpecialMember member2 = new SpecialMember("bbb", "김철수", "서울", video2, 10);
		
		GeneralMember[] members = new GeneralMember[2];
		members[0] = member1;
		members[1] = member2;

		for(int i = 0; i < 2; ++i)
			members[i].ShowInfo();
	}
}
