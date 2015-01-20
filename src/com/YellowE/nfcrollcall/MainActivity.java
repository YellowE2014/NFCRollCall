package com.YellowE.nfcrollcall;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle.Control;

import com.YellowE.nfcrollcall.atys.AtyScheduleList;
import com.YellowE.nfcrollcall.bean.Student;
import com.YellowE.nfcrollcall.bean.StudentCourse;
import com.YellowE.nfcrollcall.dao.StudentDao;
import com.YellowE.nfcrollcall.util.Db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File destDir = new File(Config.FILE_PATH);
		  if (!destDir.exists()) {
		   destDir.mkdirs();
		  }
		
//		Db db = new Db(this);
//		
//		SQLiteDatabase dbWrite = db.getWritableDatabase();
//		ContentValues cv = new ContentValues();
//		cv.put(Config.TABLE_COURSE_NAME, "计算机网络");
//		cv.put(Config.TABLE_COURSE_CLASSROOM, "教5-104");
//		cv.put(Config.TABLE_COURSE_WEEKS, "1-3周");
//		dbWrite.insert(Config.TABLE_COURSE, null, cv);
//		
//		cv = new ContentValues();
//		cv.put(Config.TABLE_COURSE_NAME, "数据结构");
//		cv.put(Config.TABLE_COURSE_CLASSROOM, "教3-206");
//		cv.put(Config.TABLE_COURSE_WEEKS, "1-16周");
//		dbWrite.insert(Config.TABLE_COURSE, null, cv);
		
//		SQLiteDatabase dbWrite = db.getWritableDatabase();
//		ContentValues cv = new ContentValues();
//		cv.put("studentID", "3111005877");
//		cv.put("studentname", "高伟东");
//		cv.put("sex", "女");
//		cv.put("gradeAndClass", "2011级计科5");
//		dbWrite.insert("student", null, cv);
//		
//		cv = new ContentValues();
//		cv.put("studentID", "3111005878");
//		cv.put("studentname", "黄才标");
//		cv.put("sex", "女");
//		cv.put("gradeAndClass", "2011级计科5");
//		dbWrite.insert("student", null, cv);
//		
//		dbWrite.close();
		
//		SQLiteDatabase dbRead = db.getReadableDatabase();
//		Cursor c = dbRead.query("student", null, null, null, null, null, null);
//		
//		while (c.moveToNext()) {
//			String name = c.getString(c.getColumnIndex("studentname"));
//			System.out.println(name);
//			
//		}
		
//		Db db = new Db(this);
//		
//		SQLiteDatabase dbWrite = db.getWritableDatabase();
//		ContentValues cv = new ContentValues();
//		cv.put("courseID", 1);
//		cv.put("studentID", 1);
//		cv.put("date", "2014-12-21");
//		cv.put("time", "10:50:20");
//		dbWrite.insert("rollCallRecord", null, cv);
//		
//		cv = new ContentValues();
//		cv.put("courseID", 1);
//		cv.put("studentID", 2);
//		cv.put("date", "2014-12-22");
//		cv.put("time", "10:50:21");
//		dbWrite.insert("rollCallRecord", null, cv);
		
//		StudentDao stuDao = new StudentDao(this);
//		stuDao.insertData(new Student("3111005875", "黄才标", "男", "11级计科5班", "F2BA68DB"));
//		stuDao.insertData(new Student("3111005876", "高伟东", "女", "11级计科5班", "6DC3CFCE"));
//		stuDao.insertData(new Student("3111005875", "黄才标", "男", "11级计科5班", "F2BA68DB"), new StudentCourse(1,1));
//		stuDao.insertData(new Student("3111005876", "高伟东", "女", "11级计科5班", "6DC3CFCE"), new StudentCourse(1,2));
//		stuDao.insertData(new Student("3111005875", "黄才标", "男", "11级计科5班", "F2BA68DB"), new StudentCourse(2,1));
//		stuDao.insertData(new Student("3111005877", "崔汉华", "男", "11级计科5班", "6DC3CFCR"), new StudentCourse(2,3));
//		
//		List<Student> students = stuDao.selcetAllData();
//		
//		Iterator<Student> it = students.iterator();
//		while(it.hasNext()){   
//            Student student = it.next();
//            System.out.println(student.getId() + student.getStudentName() + student.getSex() + student.getGradeAndClass() + student.getCardID());
//        }
//		
		
		
		
		
		startActivity(new Intent(this, AtyScheduleList.class));
		
		finish();
	}
}