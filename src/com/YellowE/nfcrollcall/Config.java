package com.YellowE.nfcrollcall;

public class Config {
	public static final String TABLE_COURSE = "course";
	public static final String TABLE_COURSE_ID = "_id";
	public static final String TABLE_COURSE_NAME = "coursename";
	public static final String TABLE_COURSE_CLASSROOM = "classroom";
	public static final String TABLE_COURSE_WEEKS = "weeks";
	
	public static final String TABLE_STUDENT = "student";
	public static final String TABLE_STUDENT_ID = "_id";
	public static final String TABLE_STUDENT_STUDENTNUM = "studentID";
	public static final String TABLE_STUDENT_NAME = "studentname";
	public static final String TABLE_STUDENT_SEX = "sex";
	public static final String TABLE_STUDENT_GRADEANDCLASS = "gradeAndClass";
	public static final String TABLE_STUDENT_CARDID = "cardID";
	
	public static final String TABLE_STUDENTCOURSE = "studentCourse";
	public static final String TABLE_STUDENTCOURSE_ID = "_id";
	public static final String TABLE_STUDENTCOURSE_STUDENTID = "student_ID";
	public static final String TABLE_STUDENTCOURSE_COURSEID = "course_ID";
	
	public static final String TABLE_ROLLCALLRECORD = "rollCallRecord";
	public static final String TABLE_ROLLCALLRECORD_ID = "_id";
	public static final String TABLE_ROLLCALLRECORD_STUDENTID = "studentID";
	public static final String TABLE_ROLLCALLRECORD_COURSEID = "courseID";
	public static final String TABLE_ROLLCALLRECORD_TIMES = "rollcalltimes";
	public static final String TABLE_ROLLCALLRECORD_DATE = "date";
	public static final String TABLE_ROLLCALLRECORD_TIME = "time";
	public static final String TABLE_ROLLCALLRECORD_STATUS = "status";
	
	public static final int ACTIVITY_RESULT_CODE_NEED_REFRESH = 1;
	
	public static final String KEY_COURSE_ID = "courseId";
	public static final String KEY_COURSE_NAME = "courseName";
	public static final String KEY_COURSE_CLASSROOM = "classroom";
	public static final String KEY_COURSE_WEEKS = "weeks";
	
	public static final String KEY_STUDENT_ID = "_id";
	public static final String KEY_STUDENT_NAME = "studentname";
	public static final String KEY_STUDENT_NUM = "studentID";
	public static final String KEY_STUDENT_SEX = "sex";
	public static final String KEY_STUDENT_CLASS = "gradeAndClass";
	public static final String KEY_STUDENT_CARDNUM = "cardID";
	
	public static final String FILE_PATH = "mnt/sdcard/NFCRollCall/";
}
