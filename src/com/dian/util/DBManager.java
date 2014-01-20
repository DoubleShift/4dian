package com.dian.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.me.bean.Account;
import com.me.bean.GetDate;
import com.me.bean.GetUpList;
import com.me.bean.TimeManage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库管理类
 * 
 */
public class DBManager {
	private DBHelper helper;
	private SQLiteDatabase mSQLiteDatabase;

	public DBManager(Context context) {
		helper = new DBHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		mSQLiteDatabase = helper.getWritableDatabase();
	}

	/**
	 * addGetUpList 加入一个月的起床记录
	 * 
	 * @param accounts
	 */
//	public boolean addGetUpList(GetUpList gp_list) {
//		String sql = "";
//		HashMap<String, String> list = gp_list.getGetup_list();
//		mSQLiteDatabase.beginTransaction(); // 开始事务
//		try {
//			sql = "select count(*) from getup where uid = ? and year = ? and month = ? ";
//			Cursor c = mSQLiteDatabase.rawQuery(
//					sql,
//					new String[] { gp_list.getUid(), gp_list.getYear(),
//							gp_list.getMonth() });
//			/*
//			 * android 中数据库处理，特别是使用cursor时，注意初始位置，好像是从下标为-1的地方开始的，
//			 * 也就是说一次查询中，返回给cursor查询结果时，不能够马上从cursor中提取值。
//			 */
//			c.moveToFirst();
//			if (c.getInt(0) == 0) {
//				for (Entry<String, String> entry : list.entrySet()) {
//					String day = entry.getKey();
//					String time = entry.getValue();
//
//					mSQLiteDatabase.execSQL(
//							"INSERT INTO getup VALUES(null, ?, ?, ?, ?, ?)",
//							new String[] { gp_list.getUid(), gp_list.getYear(),
//									gp_list.getMonth(), day, time });
//				}
//				c.close();
//			} else {
//				c.close();
//				return false;
//			}
//
//			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
//		} finally {
//			mSQLiteDatabase.endTransaction(); // 结束事务
//		}
//		return true;
//	}

	public HashMap<Integer, String> getGetUpList(String uid, String year,
			String month, int maxday) {
		String sql = "";

		HashMap<Integer, String> map = new HashMap<Integer, String>();

		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {
			sql = "select day ,time from getup where uid = ? and year = ? and month = ? order by day desc";
			Cursor c = mSQLiteDatabase.rawQuery(sql, new String[] { uid, year,
					month });

			while (c.moveToNext()) {
				map.put(Integer.parseInt(c.getString(0)), c.getString(1));
			}

			c.close();
			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}
		return map;
	}

	/**
	 * getup 成功添加一次起床记录
	 */
	public boolean GetUp(String uid, GetDate gd) {
		String sql = "";
		mSQLiteDatabase.beginTransaction();
		try {
			sql = "select count(*) from getup where uid = ? and year = ? and month = ? and day = ?";
			Cursor c = mSQLiteDatabase.rawQuery(
					sql,
					new String[] { uid, Integer.toString(gd.getYear()),
							Integer.toString(gd.getMonth()),
							Integer.toString(gd.getDay()) });
			c.moveToFirst();
			// 没有记录的话就新增
			if (c.getInt(0) == 0) {
				sql = "insert into getup values(null," + uid + ","
						+ gd.getYear() + "," + gd.getMonth() + ","
						+ gd.getDay() + ")";
				mSQLiteDatabase.execSQL(sql);
				mSQLiteDatabase.setTransactionSuccessful();
				c.close();
			} else {
				c.close();
				return false;
			}

		} finally {
			mSQLiteDatabase.endTransaction();
		}

		return true;
	}

	/**
	 * set User info
	 */
	public void setUserInfo(Account user) {
		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {

			String sql = "select * from account where uid = ?";
			Cursor c = mSQLiteDatabase.rawQuery(sql,
					new String[] { user.getUid() });

			if (c.getCount() == 0) {
				mSQLiteDatabase.execSQL(
								"INSERT INTO account VALUES(?,?,?,?,?,?,?, ?, ?, ?, ?, ?)",
								new String[] { user.getUid(), user.getName(),
										user.getEmail(), user.getPass(),
										user.getAvatar(), user.getGender(),
										user.getAgerange(), user.getDomain(),
										user.getLocation(), user.getHomepage(),
										user.getRp(), user.getChances() });

			} else {
				// 修改SQL语句 account : uid,name,email,pass,avatar,gender
				// 性别,agerange 年龄层,domain,location,homepage,rp,chances

				mSQLiteDatabase.execSQL(
						"update account set " + "name = ?,email=?,pass=?,"
								+ "avatar =?,gender =?,agerange=?,"
								+ "domain=?,location=?,"
								+ "homepage=?,rp=?,chances=?",
						new String[] { user.getName(), user.getEmail(),
								user.getPass(), user.getAvatar(),
								user.getGender(), user.getAgerange(),
								user.getDomain(), user.getLocation(),
								user.getHomepage(), user.getRp(),
								user.getChances() });
			}
			c.close();

			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}

	}

	public Account getUserInfo(String uid) {
		Account user = null;

		String sql = "select * from account where uid = ?";
		Cursor c = mSQLiteDatabase.rawQuery(sql, new String[] { uid });
		int i = 0;
		while (c.moveToNext()) {
			user = new Account(c.getString(0), c.getString(1), c.getString(2),
					c.getString(3), c.getString(4), c.getString(5),
					c.getString(6), c.getString(7), c.getString(8),
					c.getString(9), c.getString(10), c.getString(11));
		}
		return user;

	}

	/**
	 * setArchive
	 */
	public void setUserArchive(Account user) {
		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {
			String sql = "select * from archive where uid = ?";
			Cursor c = mSQLiteDatabase.rawQuery(sql,
					new String[] { user.getUid() });

			// archive:uid,target,level,needtime,limittime,averagetime
			if (c.getCount() == 0) {

				mSQLiteDatabase.execSQL(
						"insert into archive values(?,?,?,?,?,?)",
						new String[] { user.getUid(), user.getTarget(),
								user.getLevel(), user.getNeedtime(),
								user.getLimittime(), user.getAveragetime() });
			} else {

				mSQLiteDatabase.execSQL(
								"update archive set target =?,level=?,needtime=?,limitime=?,averagetime=? where uid =?",
								new String[] { user.getTarget(),
										user.getLevel(), user.getNeedtime(),
										user.getLimittime(),
										user.getAveragetime(), user.getUid() });
			}
			c.close();

			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}
	}

	/**
	 * delete database 怎么传入context？
	 */
	public void deleteDB() {
		helper.deleteDatabase(null);
	}

	/**
	 * close database
	 */
	public void closeDB() {
		mSQLiteDatabase.close();
	}

	public boolean addTimeManage(TimeManage tm){
		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {
			String sql = "select * from time where uid = ? and year=? and month =? and day =?";
			Cursor c = mSQLiteDatabase.rawQuery(sql,
					new String[] { tm.getUid(),tm.getYear(),tm.getMonth(),tm.getDay() });
			if (c.getCount() == 0) {
				//time:uid,year,month,day,sleep,regular,waste,invest,unknow,explain

				mSQLiteDatabase.execSQL(
						"insert into time values(?,?,?,?,?,?,?,?,?,?)",
						new Object[] { tm.getUid(), tm.getYear(),tm.getMonth(),tm.getDay(),tm.getSleep(),tm.getRegular(),
								tm.getWaste(),tm.getInvest(),tm.getUnknow(),tm.getExplain()});
			} else {
				c.close();
				return false;
			}
			c.close();

			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}
	
		return true;
	}
	
	public ArrayList getTimeManage(String uid,String year,String month){
		
		ArrayList List = new ArrayList();
		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {
			String sql = "select * from time where uid = ? and year =? and month =? order by day desc";
			Cursor c = mSQLiteDatabase.rawQuery(sql,new String[] { uid,year,month});
			while(c.moveToNext()){
				TimeManage tm = new TimeManage(
						c.getString(0),c.getString(1),c.getString(2),
						c.getString(3),c.getDouble(4),c.getDouble(5),
						c.getDouble(6),c.getDouble(7),c.getDouble(8),c.getString(9)
						);
				List.add(tm);
			}
			c.close();

			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}
		return List;
	}
	
	public TimeManage getTimeManage(String uid,String year,String month,String day){
		
		TimeManage tm = null;
		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {
			String sql = "select * from time where uid = ? and year =? and month =? and day =?";
			Cursor c = mSQLiteDatabase.rawQuery(sql,new String[] { uid,year,month,day});
			while(c.moveToNext()){
				 tm = new TimeManage(
						c.getString(0),c.getString(1),c.getString(2),
						c.getString(3),c.getDouble(4),c.getDouble(5),
						c.getDouble(6),c.getDouble(7),c.getDouble(8),c.getString(9)
						);
				
			}
			c.close();

			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}
		return tm;
	}
	
	public boolean login(String email, String pass) {

		String sql = "select * from account where email=? and pass =?";
		Cursor c = mSQLiteDatabase.rawQuery(sql, new String[] { email, pass });
		int result = c.getCount();
		c.close();
		if (result == 0) {
			return false;
		} 
			return true;
		

	}

	public void reg(String name, String email, String pass) {
		mSQLiteDatabase.beginTransaction(); // 开始事务
		try {

			mSQLiteDatabase.execSQL(
					"insert into account(name,email,pass) values(?,?,?)",
					new String[] { name, email, pass });
			mSQLiteDatabase.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			mSQLiteDatabase.endTransaction(); // 结束事务
		}

	}

}
