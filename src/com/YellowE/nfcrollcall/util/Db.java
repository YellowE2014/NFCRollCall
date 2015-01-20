package com.YellowE.nfcrollcall.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {
	
	private static Db mInstance = null;

	private Db(Context context) {
		super(context, "db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE student(" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT , "  +
					"studentID TEXT UNIQUE, "  +
					"studentname TEXT DEFAULT \"\", " +
					"sex TEXT DEFAULT \"\", " +
					"gradeAndClass TEXT DEFAULT \"\", " +
					"cardID TEXT DEFAULT \"\")";
		db.execSQL(sql);
		
		sql = "CREATE TABLE course(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT , "  +
				"coursename TEXT DEFAULT \"\", " +
				"classroom TEXT DEFAULT \"\", " +
				"weeks TEXT DEFAULT \"\")";
		db.execSQL(sql);
		
		sql = "CREATE TABLE rollCallRecord(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT , "  +
				"courseID INTEGER , " +
				"studentID INTEGER , " +
				"rollcalltimes INTEGER, " +
				"date DATE," +
				"time TIME," +
				"status INTEGER)";
		db.execSQL(sql);
		
		sql = "CREATE TABLE studentCourse(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT , "  +
				"course_ID INTEGER , " +
				"student_ID INTEGER , " +
				"UNIQUE(course_ID,student_ID))";
		db.execSQL(sql);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	
	public synchronized static Db getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new Db(context);
		}
		return mInstance;
	};
}
