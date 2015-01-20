package com.YellowE.nfcrollcall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.bean.Student;
import com.YellowE.nfcrollcall.bean.StudentCourse;
import com.YellowE.nfcrollcall.util.Db;

public class StudentDao {

	Db db = null;

	public StudentDao(Context cxt) {
		db = Db.getInstance(cxt);
	}

	// 插入操作
	public void insertData(Student student) {
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Config.TABLE_STUDENT_STUDENTNUM, student.getStudentNum());
		cv.put(Config.TABLE_STUDENT_NAME, student.getStudentName());
		cv.put(Config.TABLE_STUDENT_SEX, student.getSex());
		cv.put(Config.TABLE_STUDENT_GRADEANDCLASS, student.getGradeAndClass());
		cv.put(Config.TABLE_STUDENT_CARDID, student.getCardID());
		dbWrite.insert(Config.TABLE_STUDENT, null, cv);

		dbWrite.close();
	}

	public void insertData(Student student, StudentCourse sc) {
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Config.TABLE_STUDENT_STUDENTNUM, student.getStudentNum());
		cv.put(Config.TABLE_STUDENT_NAME, student.getStudentName());
		cv.put(Config.TABLE_STUDENT_SEX, student.getSex());
		cv.put(Config.TABLE_STUDENT_GRADEANDCLASS, student.getGradeAndClass());
		cv.put(Config.TABLE_STUDENT_CARDID, student.getCardID());
		dbWrite.insert(Config.TABLE_STUDENT, null, cv);

		cv = new ContentValues();
		cv.put(Config.TABLE_STUDENTCOURSE_COURSEID, sc.getCourseID());
		cv.put(Config.TABLE_STUDENTCOURSE_STUDENTID, sc.getStudentID());
		dbWrite.insert(Config.TABLE_STUDENTCOURSE, null, cv);

		dbWrite.close();

	}
	
	public void insertData(Student student, int courseID) {
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Config.TABLE_STUDENT_STUDENTNUM, student.getStudentNum());
		cv.put(Config.TABLE_STUDENT_NAME, student.getStudentName());
		cv.put(Config.TABLE_STUDENT_SEX, student.getSex());
		cv.put(Config.TABLE_STUDENT_GRADEANDCLASS, student.getGradeAndClass());
		cv.put(Config.TABLE_STUDENT_CARDID, student.getCardID());
		dbWrite.insert(Config.TABLE_STUDENT, null, cv);

		SQLiteDatabase dbRead = db.getReadableDatabase();
		int studentID = 0;
		Cursor cursor = dbRead.query(Config.TABLE_STUDENT, new String[]{"_id"}, Config.TABLE_STUDENT_STUDENTNUM +"=?", new String[]{student.getStudentNum()}, null, null, null);
		while (cursor.moveToNext()) {

			studentID = cursor.getInt(cursor
					.getColumnIndex(Config.TABLE_STUDENT_ID));

		}
		cursor.close();
		
		cv = new ContentValues();
		cv.put(Config.TABLE_STUDENTCOURSE_COURSEID, courseID);
		cv.put(Config.TABLE_STUDENTCOURSE_STUDENTID, studentID);
		dbWrite.insert(Config.TABLE_STUDENTCOURSE, null, cv);

		dbWrite.close();

	}

	// 查询操作
	public List<Student> selcetAllData() {
		SQLiteDatabase dbRead = db.getReadableDatabase();
		List<Student> students = new ArrayList<Student>();
		Cursor cursor = dbRead.query(Config.TABLE_STUDENT, null, null, null,
				null, null, null);
		while (cursor.moveToNext()) {

			students.add(encapsulate(cursor));

		}
		cursor.close();
		return students;
	}
	
	public List<Student> selcetDataByCourseID(int courseID){
		SQLiteDatabase dbRead = db.getReadableDatabase();
		List<Student> students = new ArrayList<Student>();
		
		String sql = "select * from student where _id in (select student_ID from studentCourse where course_ID = ?)";
		
		Cursor cursor = dbRead.rawQuery(sql, new String[]{String.valueOf(courseID)});
		while (cursor.moveToNext()) {

			students.add(encapsulate(cursor));

		}
		cursor.close();
		
//		Cursor cursor1 = dbRead.query(Config.TABLE_STUDENTCOURSE, null, Config.TABLE_STUDENTCOURSE_COURSEID + "=?", new String[]{String.valueOf(courseID)}, null, null, null);
//		
//		List<String> ids = new ArrayList<String>();
//		while (cursor1.moveToNext()) {
//			ids.add(String.valueOf(cursor1.getInt(cursor1.getColumnIndex(Config.TABLE_STUDENTCOURSE_STUDENTID))));
//		}
//		String[] strIds = ids.toArray(new String[ids.size()]);
//		
//		for (String string : strIds) {
//			System.out.println(string);
//		}
//		
//		
//		Cursor cursor2 = dbRead.query(Config.TABLE_STUDENT, null, Config.TABLE_STUDENT_ID + "=?", new String[]{"1"}, null, null, null);
//		while (cursor2.moveToNext()) {
//
//			students.add(encapsulate(cursor2));
//
//		}
//		
//		cursor1.close();
//		cursor2.close();
		
		
		
		return students;
	}
	
	public Student selcetDataByCardID(String CardID, String courseID){
//		SQLiteDatabase dbRead = db.getReadableDatabase();
//		Student student = null;
//		Cursor cursor = dbRead.query(Config.TABLE_STUDENT, null, Config.TABLE_STUDENT_CARDID+"=?", new String[]{CardID}, null, null, null);
//		
//		while (cursor.moveToNext()) {
//
//			student = encapsulate(cursor);
//
//		}
//		cursor.close();
//		return student;
		
		SQLiteDatabase dbRead = db.getReadableDatabase();
		Student student = null;
		
		String sql = "select * from student left join studentCourse on student._id = studentCourse.student_ID where student.cardID = ? and studentCourse.course_ID = ?";
		
		Cursor cursor = dbRead.rawQuery(sql, new String[]{CardID,courseID});
		while (cursor.moveToNext()) {

			student = encapsulate(cursor);

		}
		cursor.close();
		return student;
	}
	
	//更新
	public void updataData(Student student){
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Config.TABLE_STUDENT_STUDENTNUM, student.getStudentNum());
		cv.put(Config.TABLE_STUDENT_NAME, student.getStudentName());
		cv.put(Config.TABLE_STUDENT_SEX, student.getSex());
		cv.put(Config.TABLE_STUDENT_GRADEANDCLASS, student.getGradeAndClass());
		cv.put(Config.TABLE_STUDENT_CARDID, student.getCardID());
		dbWrite.update(Config.TABLE_STUDENT, cv, Config.TABLE_STUDENT_ID+"=?", new String[]{String.valueOf(student.getId())});
		dbWrite.close();
	}
	
	//删除
	public void deleteCourseStudent(int courseID,int studentID) {
		System.out.println(courseID + "  " + studentID);
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		SQLiteDatabase dbRead = db.getReadableDatabase();
		dbWrite.delete(Config.TABLE_STUDENTCOURSE, Config.TABLE_STUDENTCOURSE_COURSEID + "=? and " + Config.TABLE_STUDENTCOURSE_STUDENTID + "=?",
				new String[] { courseID + "", studentID + "" });
		
		int studentCourseID = 0;
		Cursor cursor = dbRead.query(Config.TABLE_STUDENTCOURSE, new String[]{Config.TABLE_STUDENTCOURSE_ID}, Config.TABLE_STUDENTCOURSE_STUDENTID + "=?", new String[]{studentID+""}, null, null, null);
		while (cursor.moveToNext()) {

			studentCourseID = cursor.getInt(cursor
					.getColumnIndex(Config.TABLE_STUDENTCOURSE_ID));

		}
		cursor.close();
		
		if (studentCourseID == 0) {
			deleteStudent(studentID);
		}
	}
	
	public void deleteStudent(int studentID){
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		dbWrite.delete(Config.TABLE_STUDENT, Config.TABLE_STUDENT_ID + "=?",
				new String[] { studentID + "" });
	}

	private Student encapsulate(Cursor cursor) {
		Student student = new Student();
		student.setId(cursor.getInt(cursor
				.getColumnIndex(Config.TABLE_STUDENT_ID)));
		student.setStudentName(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_STUDENT_NAME)));
		student.setStudentNum(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_STUDENT_STUDENTNUM)));
		student.setSex(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_STUDENT_SEX)));
		student.setGradeAndClass(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_STUDENT_GRADEANDCLASS)));
		student.setCardID(cursor.getString(cursor
				.getColumnIndex(Config.TABLE_STUDENT_CARDID)));

		System.out.println(student.getId() + student.getStudentName()
				+ student.getStudentNum() + student.getSex()
				+ student.getGradeAndClass() + student.getCardID());
		return student;
	}
	
	
}
