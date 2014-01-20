package com.me.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetDate {

	private String timeNow = "";
	private int year = 2010;
	private int month = 1;
	private int day = 1;

	public GetDate() {
		Calendar now = Calendar.getInstance();
		setYear(now.get(Calendar.YEAR));
		setMonth(now.get(Calendar.MONTH) + 1); // month start from 0 to 11
		setDay(now.get(Calendar.DATE));
		
		SimpleDateFormat bartDateFormat =new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");

		setTimeNow(bartDateFormat.format(now.getTime()));
	}

	public String getTimeNow() {
		return timeNow;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	private void setTimeNow(String timeNow) {
		this.timeNow = timeNow;
	}

	private void setYear(int year) {
		this.year = year;
	}

	private void setMonth(int month) {
		this.month = month;
	}

	private void setDay(int day) {
		this.day = day;
	}


	public int GetMonthDays() {
		Calendar cal = new GregorianCalendar();

		int month_days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return month_days;

	}

	/*
	 * ���ص���ĳ�� ���������,Java �е��·ݣ�0 - ��ʾ1�·ݣ� .....  11 - ��ʾ12�·ݣ���Ҫ�����Ӵ
	 */
	public int GetMonthDays(int month) {
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, 1);
		int month_days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return month_days;

	}
	/*
	 * ����ĳ��ĳ�� ���������,Java �е��·ݣ�0 - ��ʾ1�·ݣ� .....  11 - ��ʾ12�·ݣ���Ҫ�����Ӵ
	 */
	public int GetMonthDays(int year,int month) {
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, 1);
		int month_days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return month_days;

	}

}
