package com.YellowE.nfcrollcall.atys;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.Course;
import com.YellowE.nfcrollcall.dao.CourseDao;

public class AtyAddCourse extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_add_shedule);
		
		etCourseName = (EditText) findViewById(R.id.etCourseName);
		etClassroom = (EditText) findViewById(R.id.etClassroom);
		etWeeks = (EditText) findViewById(R.id.etWeeks);
		
		
		findViewById(R.id.edit_shedule_back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		findViewById(R.id.edit_shedule_confirm).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(etCourseName.getText())) {
					Toast.makeText(AtyAddCourse.this, R.string.course_name_cannot_be_empty, Toast.LENGTH_LONG).show();
					return;
				}
				Course course = new Course(etCourseName.getText().toString().trim(), etClassroom.getText().toString().trim(), etWeeks.getText().toString().trim());
				new CourseDao(AtyAddCourse.this).insertData(course);
				setResult(Config.ACTIVITY_RESULT_CODE_NEED_REFRESH);
				finish();
			}
		});
	}
	
	private EditText etCourseName;
	private EditText etClassroom;
	private EditText etWeeks;
}
