package mission;

import java.util.Scanner;

public class Manager {

	Student[] student;
	final int MAX_COUNT = 3;
	int count;
	
	public Manager(){
		student = new Student[10];
		count = 0;
		
	}
	
	public boolean addStudent(String name, int kor, int eng, int mat){
		if(count >= MAX_COUNT){
			return false;
		}
		
		Subject subject = new Subject(kor, eng, mat);
		Student stu = new Student(name, subject);
		this.student[count] = stu;
		count++;
		return true;
	}
	
	public void modifyStudent(String name, int kor, int eng, int mat){
		int index = -1;
		for(int i = 0; i < count; ++i)
		{
			if(true == student[i].name.equals(name))
				index = i;
		}
		
		if(-1 == index)
			return;
		
		student[index].getSubject().SetSubjectInfo(kor, eng, mat);
	}

	public boolean deleteStudent(String name){
		int index = -1;
		for(int i = 0; i < count; ++i)
		{
			if(true == student[i].name.equals(name))
				index = i;		
		}
		
		if(-1 == index)
		{
			return false;
		}

		for(int i = index; i < count - 1; ++i)
		{
			student[i] = student[i+1]; 
		}
				
		--count;
		student[count] = null;

		return true;
	}

	public Student searchStudent(String name){
		int index = -1;
		for(int i = 0; i < count; ++i)
		{
			if(true == student[i].name.equals(name))
				index = i;		
		}
		
		if(-1 == index)
		{
			return null;
		}
		
		return student[index]; 
	}
	
	// 내림차순 출력
	public Student[] allStudentList(){
		System.out.println("allStudentList");
		Student[] orderStudent = new Student[count];
		System.arraycopy(student, 0, orderStudent, 0, count);
		//int j = 0;
		//Subject temp;
		int index = 0;
		for(int i = 0; i < count; ++i){
			Student student1 = orderStudent[i];
			
			for(int j = 0; j < count; ++j) {
				Student student2 = orderStudent[j];
				if(student1.getSubject().getAvg() < student2.getSubject().getAvg())
				{
					index = j; 
				}
			}
			Student stdTemp = orderStudent[i];
			orderStudent[i] = orderStudent[index];
			orderStudent[index] = stdTemp;
		}
		
		// 삽입정렬
		/*for(int i = 0; i < count; ++i){
			System.out.println(i+":"+count);
			j = i;
			Subject subject1 = orderStudent[j].getSubject();
			Subject subject2 = orderStudent[j+1].getSubject();
			while(subject1.getAvg() < subject2.getAvg()) {
				temp = subject1;
				subject1 = subject2;
				subject2 = temp;
				--j;
			}
		}*/
		
		for(int i = 0; i < count; ++i){
			System.out.println("name:"+orderStudent[i].name+"avg:"+orderStudent[i].getSubject().getAvg());
		}
		
		return orderStudent;
	}
	
	public int getCount()
	{
		return count;
	}
	
 }
