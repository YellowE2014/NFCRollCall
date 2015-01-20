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
import com.YellowE.nfcrollcall.bean.Course;
import com.YellowE.nfcrollcall.dao.CourseDao;

public class AtyScheduleList extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_schedule_liset);
		
		adapter = new AtyScheduleListAdapter(this);
		setListAdapter(adapter);
		
		loadCourse();
		
	}
	
	private void loadCourse(){
		adapter.clear();
		adapter.addAll(new CourseDao(this).selcetAllData());
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
			Intent i = new Intent(AtyScheduleList.this, AtyAddCourse.class);
			startActivityForResult(i, 0);
			break;

		default:
			break;
		}
		
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case Config.ACTIVITY_RESULT_CODE_NEED_REFRESH:
			loadCourse();
			break;

		default:
			break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Course course = adapter.getItem(position);
		Intent i = new Intent(this, AtyCourse.class);
		i.putExtra(Config.KEY_COURSE_ID, course.getCourseID());
		i.putExtra(Config.KEY_COURSE_NAME, course.getCourseName());
		i.putExtra(Config.KEY_COURSE_CLASSROOM, course.getClsssroom());
		i.putExtra(Config.KEY_COURSE_WEEKS, course.getWeeks());
		startActivityForResult(i, 0);
	}
	
	private AtyScheduleListAdapter adapter = null;
}
