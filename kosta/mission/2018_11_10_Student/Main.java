package mission;

import java.util.Scanner;

public class Main {
	
	public static void Input(int[] arr)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("국어 :");
		arr[0] = sc.nextInt();
		System.out.print("영어 :");
		arr[1] = sc.nextInt();
		System.out.print("수학 :");
		arr[2] = sc.nextInt();
		sc.nextLine();			// 개행문자 먹기
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Manager manager = new Manager();
		Scanner sc 		= new Scanner(System.in);
		int key 		= 0;
		do {
			try {
				System.out.println("****** 학생 관리 프로그램 입니다 ******");
				System.out.println("1.학생추가 2.학생 점수 수정 3.학생삭제 4.학생검색 5.전체출력 6.종료  ");
				key = sc.nextInt();
				
				switch(key) {
				case 1:
				{
						sc.nextLine();	// 엔터 
						int[] tempSubject = {0, 0, 0};
						System.out.print("이름:");
						String name = sc.nextLine();
						Input(tempSubject);						
						manager.addStudent(name, tempSubject[0], tempSubject[1], tempSubject[2]);
				}
					break;
				case 2:
				{
					sc.nextLine();	// 엔터
					int[] tempSubject = {0, 0, 0};
					Input(tempSubject);
					System.out.print("성적을 수정할 학생 이름:");
					String name = sc.nextLine();
					if(null == manager.searchStudent(name)){
						System.out.println("학생을 찾을 수 없습니다");
					}
					else
					{
						Input(tempSubject);
						manager.modifyStudent(name, tempSubject[0],tempSubject[1], tempSubject[2]);
					}
				}
					break;
				case 3:
				{
					// 삭제
					sc.nextLine();	// 엔터 
					System.out.print("삭제할 학생 이름:");
					String name = sc.nextLine();
					if(false == manager.deleteStudent(name)){
						System.out.println("학생을 찾을 수 없습니다");
					}
					else{
						System.out.println("삭제 했습니다");
					}
				}
					break;
				case 4:
				{
					sc.nextLine();	// 엔터 
					System.out.print("성적을 수정할 학생 이름:");
					String name = sc.nextLine();
					Student std = manager.searchStudent(name);
					if(null == std)
					{
						System.out.println("학생을 찾을 수 없습니다");
					}
					else
					{
						System.out.println("이름"+'\t'+"국어"+'\t'+"영어"+'\t'+"수학"+'\t'+"합계"+'\t'+"평균"+'\t');
						System.out.println(std.getName()+'\t'+std.getSubject().getKor()+'\t'+std.getSubject().getEng()+'\t'+std.getSubject().getMat()+'\t'+std.getSubject().getSum()+'\t'+std.getSubject().getAvg());
					}
				}	
					break;
				case 5:
					Student[] students = manager.allStudentList();
					int count = manager.getCount();
					System.out.println("이름"+'\t'+"국어"+'\t'+"영어"+'\t'+"수학"+'\t'+"합계"+'\t'+"평균"+'\t');
					for(int i = 0; i < count; ++i )
					{
						System.out.println(students[i].getName()+'\t'+students[i].getSubject().getKor()+'\t'+students[i].getSubject().getEng()+'\t'+students[i].getSubject().getMat()+'\t'+students[i].getSubject().getSum()+'\t'+students[i].getSubject().getAvg());					
					}
					break;
				case 6:
					System.out.println("프로그램 종료");
					break;
				}
				
			}catch(Exception e) {
				System.out.println("1~6까지 눌러주세요^^");
			}	
		}while(6!=key);
	}

}
