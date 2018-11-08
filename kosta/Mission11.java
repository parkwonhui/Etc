package kosta.mission;

import java.util.Scanner;

public class Mission11 {
	static Scanner sc;
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		final int maxCount = 3;
		int index = 0;
		String[] subject = {"�̸�","����","����","����","����","���"};
		String[] name = new String[maxCount];
		int[][] score = new int[maxCount][5];
		
		sc = new Scanner(System.in);
		int value = 0;
		do{
			
			System.out.println("// 1.�߰� 2. ��ü��� 3.�˻� 4.���� => �ܼ����");
			//value = Integer.parseInt(sc.nextLine());
			value = sc.nextInt();
			sc.nextLine();
			switch(value)
			{
			case 1 :
				if(maxCount < (index+1))
				{
					System.out.println("�� �̻� �߰��� �� �����ϴ�");
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
				// �ٽ��ѹ� �Է¹޴´�
				String strTemp = sc.nextLine();
				int searchIndex = SearchName(name, strTemp, index);
				if(-1 == searchIndex)
				{
					System.out.println("�̸��� ã�� �� �����ϴ� \n");
					break;
				}

				PrintSubject(subject);
				PrintStudentInfo(score[searchIndex], strTemp, searchIndex);
				}
				break;
			case 4:
				break;
			default:
				System.out.println("�߸��Է��߾��");
				break;
			}
			
		}while(4 != value);
		
		System.out.println("���α׷� ����");

	}
	
	static void AddStudent(int[][] score,String[] name, int index)
	{	
		String[] subject = {"����", "����", "����"};
		
		System.out.print("�̸�:");
		name[index] = sc.nextLine();
		
		for(int i = 0; i < 3; ++i)
		{
			System.out.print(subject[i]+"���� :");
			//score[index][i] = Integer.parseInt(sc.nextLine());
			score[index][i] = sc.nextInt();
			score[index][3] += score[index][i];
		}
		// �� �޾����� ��� ���ϱ�
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
