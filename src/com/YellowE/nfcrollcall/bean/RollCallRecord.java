package com.YellowE.nfcrollcall.bean;

public class RollCallRecord {
	private Student student;
	private String courseID;
	private String studentID;
	private int rollcallTimes;
	private String date;
	private String time;
	private int status; // 0:Õý³£ 1:³Ùµ½ 2:Çë¼Ù

	public RollCallRecord() {

	}

	public RollCallRecord(Student student, String courseID, String studentID, int rollcallTimes, 
			String date, String time, int status) {
		this.student = student;
		this.courseID = courseID;
		this.studentID = studentID;
		this.rollcallTimes = rollcallTimes;
		this.date = date;
		this.time = time;
		this.status = status;
	}

	public int getRollcallTimes() {
		return rollcallTimes;
	}

	public void setRollcallTimes(int rollcallTimes) {
		this.rollcallTimes = rollcallTimes;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
