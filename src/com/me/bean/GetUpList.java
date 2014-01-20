package com.me.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * api地址  http://4dian.sinaapp.com/api/getup/201303/1
 */
public class GetUpList {
	private int uid;
	private int year;
	private int month;
	private HashMap<Integer , String>  getup_list = new HashMap<Integer , String>();

	/**
	 * @param uid
	 * @param year
	 * @param month
	 * @param getup_list
	 */
	public GetUpList(int uid, int year, int month,
			HashMap<Integer, String> getup_list) {
		super();
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.getup_list = getup_list;
	}

	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public HashMap<Integer, String> getGetup_list() {
		return getup_list;
	}


	public void setGetup_list(HashMap<Integer, String> getup_list) {
		this.getup_list = getup_list;
	}





}
