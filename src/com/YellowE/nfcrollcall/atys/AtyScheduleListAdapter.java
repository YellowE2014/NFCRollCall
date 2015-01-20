package com.YellowE.nfcrollcall.atys;

import java.util.ArrayList;
import java.util.List;

import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AtyScheduleListAdapter extends BaseAdapter {

	private Context context = null;
	private List<Course> courses = new ArrayList<Course>();

	public AtyScheduleListAdapter(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	@Override
	public int getCount() {
		return courses.size();
	}

	@Override
	public Course getItem(int position) {
		return courses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.lstv_item_main_course, null);
			convertView.setTag(new ListCell(
							(TextView) convertView
									.findViewById(R.id.lstv_item_main_course_txt_courseNum),
							(TextView) convertView
									.findViewById(R.id.lstv_item_main_course_courseName),
							(TextView) convertView
									.findViewById(R.id.lstv_item_main_course_classroom),
							(TextView) convertView
									.findViewById(R.id.lstv_item_main_course_week)));
		}
		
		ListCell lc = (ListCell) convertView.getTag();
		
		Course course = getItem(position);
		
		lc.getCourseName().setText(course.getCourseName());
		lc.getCourseNum().setText(course.getCourseID()+"");
		lc.getCourseWeek().setText(course.getWeeks());
		lc.getClassroom().setText(course.getClsssroom());

		return convertView;
	}
	
	public void addAll(List<Course> courses){
		this.courses = courses;
		notifyDataSetChanged();
	}
	
	public void clear(){
		courses.clear();
		notifyDataSetChanged();
	}
	
	

	private static class ListCell {
		public ListCell(TextView courseNum, TextView courseName,
				TextView classroom, TextView courseWeek) {
			this.classroom = classroom;
			this.courseName = courseName;
			this.courseNum = courseNum;
			this.courseWeek = courseWeek;
		}

		private TextView courseNum;
		private TextView courseName;
		private TextView classroom;
		private TextView courseWeek;

		public TextView getCourseNum() {
			return courseNum;
		}

		public TextView getCourseName() {
			return courseName;
		}

		public TextView getClassroom() {
			return classroom;
		}

		public TextView getCourseWeek() {
			return courseWeek;
		}

	}

}
