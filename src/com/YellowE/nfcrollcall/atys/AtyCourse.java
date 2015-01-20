package com.YellowE.nfcrollcall.atys;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.dao.CourseDao;
import com.YellowE.nfcrollcall.util.ProduceExcel;

public class AtyCourse extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_detail_course);

		Intent data = getIntent();
		courseID = String.valueOf(data.getIntExtra(Config.KEY_COURSE_ID, 0));
		courseName = data.getStringExtra(Config.KEY_COURSE_NAME);
		classroom = data.getStringExtra(Config.KEY_COURSE_CLASSROOM);
		weeks = data.getStringExtra(Config.KEY_COURSE_WEEKS);

		edCourseID = (TextView) findViewById(R.id.course_info_txv_courseID);
		edCourseName = (TextView) findViewById(R.id.course_info_txv_courseName);
		edclassroom = (TextView) findViewById(R.id.course_info_txv_classroom);
		edWeeks = (TextView) findViewById(R.id.course_info_txv_week);

		edCourseID.setText(courseID);
		edCourseName.setText(courseName);
		edclassroom.setText(classroom);
		edWeeks.setText(weeks);

		findViewById(R.id.edit_course_rlyt_delete_course).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new CourseDao(AtyCourse.this).delete(Integer
								.parseInt(courseID));
						setResult(Config.ACTIVITY_RESULT_CODE_NEED_REFRESH);
						Toast.makeText(AtyCourse.this, "成功删除",
								Toast.LENGTH_LONG).show();
						finish();
					}
				});

		// 开始点名按钮
		findViewById(R.id.course_info_btn_begin_roll_call).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent(AtyCourse.this,
								AtyRollCallList.class);
						i.putExtra(Config.KEY_COURSE_ID, courseID);
						startActivityForResult(i, 0);
					}
				});

		// 学生列表按钮
		findViewById(R.id.course_info_btn_classmate).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent(AtyCourse.this,
								AtyCourseStudentList.class);
						i.putExtra(Config.KEY_COURSE_ID, courseID);
						startActivity(i);
					}
				});

		findViewById(R.id.course_info_btn_produce).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						ProduceExcel.writeExcel(AtyCourse.this, courseID);
						Toast.makeText(AtyCourse.this, "成功生成点名表",
								Toast.LENGTH_LONG).show();
					}
				});

		findViewById(R.id.course_info_btn_open).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent("android.intent.action.VIEW");
						i.addCategory("android.intent.category.DEFAULT");
						i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						Uri uri = Uri.fromFile(new File(Config.FILE_PATH + courseID + ".xls"));
						i.setDataAndType(uri, "application/vnd.ms-excel");
						startActivity(i);
					}
				});

	}

	private String courseID;
	private String courseName;
	private String classroom;
	private String weeks;

	private TextView edCourseID;
	private TextView edCourseName;
	private TextView edclassroom;
	private TextView edWeeks;

}
