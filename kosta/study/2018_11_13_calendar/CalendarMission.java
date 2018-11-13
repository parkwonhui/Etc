package kosta.api;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarMission {
	
	public static void main(String[] args) {
		// 2018년 11월 캘린더 만들기
		int year = 2018;
		int month = 11;	// 12월. -1 한 값을 넣어줘야 한다
		int day = 1;
		char[] week = {'일', '월', '화', '수','목', '금', '토'};

		System.out.println("\t\t<"+year+"년 "+(month+1)+"월 달력>");
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(year, month, day);	// 잘 세팅되었는지 확인하려면 gc.getTime()함수를 출력하면 현재 세팅 시간이 나온다
			
		int endDay = gc.getActualMaximum(Calendar.DATE);	// 마지막 날짜
		String strCalenar = "";
		int startDay = gc.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		
		
		for(int i = 0; i < startDay; ++i){					// 날짜 적을 위치로 이동
			strCalenar+="\t";
		}
		
		for(int i = 1; i < endDay+1;){		// 1부터 시작하므로 1더해줌
			strCalenar+=i;
			gc.set(year, month, ++i);
			int nextDay = gc.get(Calendar.DAY_OF_WEEK)-1;	// 요일 날짜에 따라서 개행문자를 넣는다(6)
			if(0 == nextDay)
				strCalenar+="\n";
			else
				strCalenar+="\t";

		}
		
		System.out.println(strCalenar);
	}
}
