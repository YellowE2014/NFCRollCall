package com.YellowE.nfcrollcall.atys;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.Student;
import com.YellowE.nfcrollcall.dao.StudentDao;

public class AtyCourseStudentList extends ListActivity {
	
	private AtyCourseStudentListAdapter adapter = null;
	private int courseID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_course_student_list);
		
		Intent data = getIntent();
		courseID = Integer.parseInt(data.getStringExtra(Config.KEY_COURSE_ID));
		
		adapter = new AtyCourseStudentListAdapter(this);
		setListAdapter(adapter);
		
		loadStudent(courseID); 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_aty_schedule_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.meneShowAtyEditCourse:
			Intent i = new Intent(AtyCourseStudentList.this, AtyAddStudent.class);
			i.putExtra(Config.KEY_COURSE_ID, courseID);
			startActivityForResult(i, 0);
			break;

		default:
			break;
		}
		
		return true;
	}
	
	private void loadStudent(int courseID){
		System.out.println(courseID);
		System.out.println(this.courseID);
		adapter.clear();
		adapter.addAll(new StudentDao(this).selcetDataByCourseID(courseID));
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case Config.ACTIVITY_RESULT_CODE_NEED_REFRESH:
			System.out.println("¸üÐÂ");
			loadStudent(courseID);
			break;

		default:
			break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Student student = adapter.getItem(position);
		Intent i = new Intent(this, AtyEditStudent.class);
		i.putExtra(Config.KEY_COURSE_ID, courseID);
		i.putExtra(Config.KEY_STUDENT_ID, student.getId());
		i.putExtra(Config.KEY_STUDENT_NAME, student.getStudentName());
		i.putExtra(Config.KEY_STUDENT_NUM, student.getStudentNum());
		i.putExtra(Config.KEY_STUDENT_SEX, student.getSex());
		i.putExtra(Config.KEY_STUDENT_CLASS, student.getGradeAndClass());
		i.putExtra(Config.KEY_STUDENT_CARDNUM, student.getCardID());
		
		startActivityForResult(i, 0);
	}
}
