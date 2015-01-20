package com.YellowE.nfcrollcall.bean;

public class StudentCourse {
	private int scID;
	private int courseID;
	private int studentID;
	
	public StudentCourse() {

	}
	
	public StudentCourse(int scID, int courseID, int studentID){
		this.scID = scID;
		this.courseID = courseID;
		this.studentID = studentID;
	}
	
	public StudentCourse(int courseID, int studentID){
		this.courseID = courseID;
		this.studentID = studentID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

}
