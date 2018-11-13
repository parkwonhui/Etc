package kosta.api;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calenar1 {

	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2018, 11, 1);// 2018년 12월 1일
		int week = gc.get(Calendar.DAY_OF_WEEK);
		
		System.out.println("<2018 12월>");
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		
		for(int i = 1; i < week; ++i){
			System.out.print("\t");
		}
		
		for(int i = 1; i <=gc.getActualMaximum(Calendar.DATE); ++i){
			System.out.print(i+"\t");
			if(0 == (week-1+i)%7)
				System.out.println();
		}
	}

}
