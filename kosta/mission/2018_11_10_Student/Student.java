package mission;

public class Student {

	String name;
	Subject subject;
	
	public Student(String name, Subject subject) {
		super();
		this.name = name;
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public Subject getSubject() {
		return subject;
	}
	
}
