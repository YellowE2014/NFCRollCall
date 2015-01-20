package com.YellowE.nfcrollcall.bean;

public class Course {
	private int courseID;
	private String courseName;
	private String classroom;
	private String weeks;

	public Course(int courseID, String courseName, String classroom, String weeks) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.classroom = classroom;
		this.weeks = weeks;
	}
	
	public Course(String courseName, String classroom, String weeks) {
		this.courseName = courseName;
		this.classroom = classroom;
		this.weeks = weeks;
	}
	
	public Course() {
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getClsssroom() {
		return classroom;
	}

	public void setClsssroom(String clsssroom) {
		this.classroom = clsssroom;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

}
