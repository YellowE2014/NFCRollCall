package com.YellowE.nfcrollcall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.bean.Course;
import com.YellowE.nfcrollcall.util.Db;

public class CourseDao {

	Db db = null;

	public CourseDao(Context cxt) {
		db = Db.getInstance(cxt);
	}

	// 插入操作
	public void insertData(Course course) {
		String sql = "insert into course (coursename, classroom, weeks)values(?,?,?)";
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		dbWrite.execSQL(sql,
				new Object[] { course.getCourseName(), course.getClsssroom(),
						course.getWeeks() });
	}

	// 查询操作
	public List<Course> selcetAllData() {
		SQLiteDatabase dbRead = db.getReadableDatabase();
		List<Course> courses = new ArrayList<Course>();
		Cursor cursor = dbRead.query(Config.TABLE_COURSE, null, null, null,
				null, null, null);
		// Cursor
		// cursor=database.rawQuery("select * from "+WordSQLiteHelper.TABLE_WORDS,new
		// String[]{});
//		cursor.moveToFirst();
		while (cursor.moveToNext()) {

			courses.add(encapsulate(cursor));
			
		}
		cursor.close();
		return courses;
	}

	public void delete(int courseID) {
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		dbWrite.delete(Config.TABLE_COURSE, Config.TABLE_COURSE_ID + "=?",
				new String[] { courseID + "" });
	}
	
	

	private Course encapsulate(Cursor cursor) {
		Course course = new Course();
		course.setCourseID(cursor.getInt(cursor
				.getColumnIndex(Config.TABLE_COURSE_ID)));
		course.setCourseName(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_COURSE_NAME)));
		course.setClsssroom(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_COURSE_CLASSROOM)));
		course.setWeeks(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_COURSE_WEEKS)));
		
//		course.setCourseID(cursor.getInt(0));
//		course.setCourseName(cursor.getString(1));
//		course.setCourseName(cursor.getString(2));
//		course.setCourseName(cursor.getString(3));

		System.out.println(course.getCourseID() + course.getCourseName() + course.getClsssroom() + course.getWeeks());
		return course;
	}
}
