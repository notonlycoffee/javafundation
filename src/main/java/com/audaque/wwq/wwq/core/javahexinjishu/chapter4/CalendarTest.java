package com.audaque.wwq.wwq.core.javahexinjishu.chapter4;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarTest {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		// construct d as current date
		GregorianCalendar d = new GregorianCalendar();//初始化的日期是当前系统日期

		int today = d.get(Calendar.DAY_OF_MONTH);//月份当中的日
		int month = d.get(Calendar.MONTH);//月份

		// set d to start date of the month
		d.set(Calendar.DAY_OF_MONTH, 1);//将日期设置为月份中的第一天

		int weekday = d.get(Calendar.DAY_OF_WEEK);//返回月份中第一天所在的星期几

		// get first day of week (Sunday in the U.S.)
		int firstDayOfWeek = d.getFirstDayOfWeek();//返回一个星期的开始，比如一个星期中，星期日是开始，就返回星期日;如果星期一是开始，就返回星期一，根据不同地域的习惯不同，这里设置的是US地域的星期，也就是星期日是一个星期的开始

		// determine the required indentation for the first line
		int indent = 0;//这个变量左右下面设置的缩进参数
		while (weekday != firstDayOfWeek) {
			indent++;
			d.add(Calendar.DAY_OF_MONTH, -1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}

		// print weekday names打印星期日到星期六
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
		do {
			System.out.printf("%4s", weekdayNames[weekday]);
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		} while (weekday != firstDayOfWeek);
		System.out.println();

		//设置缩进
		for (int i = 1; i <= indent; i++)
			System.out.print("    ");

		d.set(Calendar.DAY_OF_MONTH, 1);
		//下面开始打印日期
		do {
			// print day
			int day = d.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);

			// mark current day with *
			if (day == today)
				System.out.print("*");
			else
				System.out.print(" ");

			// advance d to the next day
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);

			// start a new line at the start of the week
			if (weekday == firstDayOfWeek)//如果一个星期已经打印完毕，就换行
				System.out.println();
		} while (d.get(Calendar.MONTH) == month);//这里通过每天的数字加1，知道这个日期的月份发生了改变，就是已经把一整个月都打印完毕了
		// the loop exits when d is day 1 of the next month

		// print final end of line if necessary
		if (weekday != firstDayOfWeek)
			System.out.println();
	}
}
