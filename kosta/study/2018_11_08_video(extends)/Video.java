package kosta.video;

public class Video {
	private int no;
	private String title;
	private String actor;
	
	public Video(int no, String title, String actor) {
		super();
		this.no = no;
		this.title = title;
		this.actor = actor;
	}

	public int getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getActor() {
		return actor;
	}
}
