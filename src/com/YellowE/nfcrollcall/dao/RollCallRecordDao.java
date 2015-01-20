package com.YellowE.nfcrollcall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.bean.RollCallRecord;
import com.YellowE.nfcrollcall.util.Db;

public class RollCallRecordDao {

	Db db = null;

	public RollCallRecordDao(Context cxt) {
		db = Db.getInstance(cxt);
	}

	// 插入操作
	public void insertData(RollCallRecord rcRecord) {
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Config.TABLE_ROLLCALLRECORD_COURSEID, rcRecord.getCourseID());
		cv.put(Config.TABLE_ROLLCALLRECORD_STUDENTID, rcRecord.getStudentID());
		cv.put(Config.TABLE_ROLLCALLRECORD_TIMES, rcRecord.getRollcallTimes());
		cv.put(Config.TABLE_ROLLCALLRECORD_DATE, rcRecord.getDate());
		cv.put(Config.TABLE_ROLLCALLRECORD_TIME, rcRecord.getTime());
		cv.put(Config.TABLE_ROLLCALLRECORD_STATUS, rcRecord.getStatus());
		dbWrite.insert(Config.TABLE_ROLLCALLRECORD, null, cv);

		dbWrite.close();
	}

	// 查询操作
	public List<RollCallRecord> selcetData(String courseID, String studentID) {
		SQLiteDatabase dbRead = db.getReadableDatabase();
		List<RollCallRecord> rcRecords = new ArrayList<RollCallRecord>();
		Cursor cursor = dbRead.query(Config.TABLE_ROLLCALLRECORD, null, Config.TABLE_ROLLCALLRECORD_COURSEID+"=? and "+Config.TABLE_ROLLCALLRECORD_STUDENTID+"=?", new String[]{courseID, studentID}, null, null, Config.TABLE_ROLLCALLRECORD_TIMES);
		while (cursor.moveToNext()) {

			rcRecords.add(encapsulate(cursor));

		}
		cursor.close();
		return rcRecords;
	}
	
	private RollCallRecord encapsulate(Cursor cursor) {
		RollCallRecord rcRecord = new RollCallRecord();
		rcRecord.setRollcallTimes(cursor.getInt(cursor.getColumnIndex(Config.TABLE_ROLLCALLRECORD_TIMES)));
		rcRecord.setStatus(cursor.getInt(cursor.getColumnIndex(Config.TABLE_ROLLCALLRECORD_STATUS)));

		System.out.println(rcRecord.getRollcallTimes() +rcRecord.getStatus());
		return rcRecord;
	}
}
