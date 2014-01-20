package com.me.bean;

import java.util.HashMap;

public class TimeManage {
	//time:uid,year,month,day,sleep,regular,waste,invest,unknow,explain

	private String uid;
	private String year;
	private String month;
	private String day;
	private double sleep;
	private double regular;
	private double waste;
	private double invest;
	private double unknow;
	private String explain;
	public TimeManage(String uid, String year, String month, String day,
			double sleep, double regular, double waste, double invest,
			double unknow, String explain) {
		super();
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.day = day;
		this.sleep = sleep;
		this.regular = regular;
		this.waste = waste;
		this.invest = invest;
		this.unknow = unknow;
		this.explain = explain;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public double getSleep() {
		return sleep;
	}
	public void setSleep(double sleep) {
		this.sleep = sleep;
	}
	public double getRegular() {
		return regular;
	}
	public void setRegular(double regular) {
		this.regular = regular;
	}
	public double getWaste() {
		return waste;
	}
	public void setWaste(double waste) {
		this.waste = waste;
	}
	public double getInvest() {
		return invest;
	}
	public void setInvest(double invest) {
		this.invest = invest;
	}
	public double getUnknow() {
		return unknow;
	}
	public void setUnknow(double unknow) {
		this.unknow = unknow;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}


	
}
