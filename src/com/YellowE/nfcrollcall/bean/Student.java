package com.YellowE.nfcrollcall.bean;

public class Student {
	private int id;
	private String studentNum;
	private String studentName;
	private String sex;
	private String gradeAndClass;
	private String cardID;
	
	public Student() {
	}

	public Student(String studentNum, String studentName, String sex, String gradeAndClass, String cardID) {
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.sex = sex;
		this.gradeAndClass = gradeAndClass;
		this.cardID = cardID;
	}

	public Student(int id, String studentNum, String studentName, String sex, String gradeAndClass, String cardID) {
		this.id = id;
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.sex = sex;
		this.gradeAndClass = gradeAndClass;
		this.cardID = cardID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGradeAndClass() {
		return gradeAndClass;
	}

	public void setGradeAndClass(String gradeAndClass) {
		this.gradeAndClass = gradeAndClass;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

}
