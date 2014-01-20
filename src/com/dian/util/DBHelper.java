
package com.dian.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String ACCOUNT_DATABASE_NAME = "account.db";
	private static final int ACCOUNT_DATABASE_VERSION = 1;

	public DBHelper(Context context) {
		super(context, ACCOUNT_DATABASE_NAME, null, ACCOUNT_DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		db.execSQL("CREATE TABLE IF NOT EXISTS account"
//				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, id VARCHAR,name VARCHAR,"
//				+ "url VARCHAR,token VARCHAR, expires_in VARCHAR, plf VARCHAR,openid VARCHAR,openkey VARCHAR)");
		
		//getup: gid,uid,year,month,day,time
		db.execSQL("CREATE TABLE IF NOT EXISTS getup"
				+ "(gid INTEGER PRIMARY KEY AUTOINCREMENT, uid VARCHAR,year VARCHAR,month VARCHAR,day VARCHAR,time VARCHAR)");

		//account : uid,name,email,pass,avatar,gender 性别,agerange 年龄层,domain,location,homepage,rp,chances
		db.execSQL("CREATE TABLE IF NOT EXISTS account"
				+"(uid VARCHAR,name VARCHAR,email VARCHAR,pass VARCHAR,avatar VARCHAR,gender VARCHAR," +
				"agerange VARCHAR,domain VARCHAR,location VARCHAR,homepage VARCHAR,rp VARCHAR,chances VARCHAR)");
		
		//archive:uid,target,level,needtime,limitime,averagetime
		db.execSQL("CREATE TABLE IF NOT EXISTS archive"
				+"(uid VARCHAR,target VARCHAR,level VARCHAR,needtime VARCHAR,limitime VARCHAR,averagetime VARCHAR)");
		
		//time:uid,year,month,day,sleep,regular,waste,invest,unknow,explain
		db.execSQL("CREATE TABLE IF NOT EXISTS time"+
		"(uid VARCHAR,year  VARCHAR,month VARCHAR,day VARCHAR,sleep DOUBLE,regular DOUBLE,waste DOUBLE,invest DOUBLE,unknow DOUBLE,explain VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS account");
		db.execSQL("DROP TABLE IF EXISTS getup");
		db.execSQL("DROP TABLE IF EXISTS archive");
		db.execSQL("DROP TABLE IF EXISTS time");
		onCreate(db);
	}
	public boolean deleteDatabase(Context context) {

	    return context.deleteDatabase(ACCOUNT_DATABASE_NAME);

	}

	

}
