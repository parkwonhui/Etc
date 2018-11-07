package kosta.mission;

import java.util.Scanner;

public class Mission11 {
	static Scanner sc;
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		final int maxCount = 3;
		int index = 0;
		String[] subject = {"이름","국어","영어","수학","총합","평균"};
		String[] name = new String[maxCount];
		int[][] score = new int[maxCount][5];
		
		sc = new Scanner(System.in);
		int value = 0;
		do{
			
			System.out.println("// 1.추가 2. 전체출력 3.검색 4.종료 => 콘솔출력");
			//value = Integer.parseInt(sc.nextLine());
			value = sc.nextInt();
			sc.nextLine();
			switch(value)
			{
			case 1 :
				if(maxCount < (index+1))
				{
					System.out.println("더 이상 추가할 수 없습니다");
					break;
				}
				
				AddStudent(score, name, index);
				++index;
				break;
			case 2 :
				PrintScore(score, subject, name, index);
				break;
			case 3 :
				{	
				// 다시한번 입력받는다
				String strTemp = sc.nextLine();
				int searchIndex = SearchName(name, strTemp, index);
				if(-1 == searchIndex)
				{
					System.out.println("이름을 찾을 수 없습니다 \n");
					break;
				}

				PrintSubject(subject);
				PrintStudentInfo(score[searchIndex], strTemp, searchIndex);
				}
				break;
			case 4:
				break;
			default:
				System.out.println("잘못입력했어요");
				break;
			}
			
		}while(4 != value);
		
		System.out.println("프로그램 종료");

	}
	
	static void AddStudent(int[][] score,String[] name, int index)
	{	
		String[] subject = {"국어", "영어", "수학"};
		
		System.out.print("이름:");
		name[index] = sc.nextLine();
		
		for(int i = 0; i < 3; ++i)
		{
			System.out.print(subject[i]+"점수 :");
			//score[index][i] = Integer.parseInt(sc.nextLine());
			score[index][i] = sc.nextInt();
			score[index][3] += score[index][i];
		}
		// 다 받았으면 평균 구하기
		score[index][4] = (int)(score[index][3]/3.0);
		++index;
	}
	
	static void PrintScore(int[][] score, String[] subject, String[] name, int index)
	{	
		PrintSubject(subject);
		
		for(int i = 0; i < index; ++i)
			PrintStudentInfo(score[i], name[i], i);
		
		System.out.println();
	}
	
	static void PrintStudentInfo(int[] score,String name, int index)
	{
		System.out.print(name+"\t");

		for(int i =0; i < score.length; ++i)
			System.out.print(score[i]+"\t");
		
		System.out.println();	
	}
	
	static void PrintSubject(String[] subject)
	{
		for(int i = 0; i < subject.length; ++i)
			System.out.print(subject[i]+"\t");

		System.out.println();
	}
	
	static int SearchName(String[] name, String searchName, int index)
	{	
		int i = 0;
		for(i = 0; i < index; ++i)
		{
			if(true == searchName.equals(name[i]))
				return i;
		}
		
		return -1;
	}
}
