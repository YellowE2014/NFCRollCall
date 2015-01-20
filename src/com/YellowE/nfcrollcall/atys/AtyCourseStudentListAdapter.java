package com.YellowE.nfcrollcall.atys;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.Student;

public class AtyCourseStudentListAdapter extends BaseAdapter {

	private Context context = null;
	private List<Student> students = new ArrayList<Student>();

	public AtyCourseStudentListAdapter(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	@Override
	public int getCount() {
		return students.size();
	}

	@Override
	public Student getItem(int position) {
		return students.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.lstv_item_student, null);
			convertView.setTag(new ListCell((TextView) convertView
					.findViewById(R.id.classmate_item_txv_studentName),
					(TextView) convertView
							.findViewById(R.id.classmate_item_txv_classInfo),
					(TextView) convertView
							.findViewById(R.id.classmate_item_txv_studentNum),
					(ImageView) convertView
							.findViewById(R.id.classmate_item_imgv_gender)));
		}

		ListCell lc = (ListCell) convertView.getTag();

		Student student = getItem(position);

		lc.getStudentName().setText(student.getStudentName());
		lc.getGradeAndClass().setText(student.getGradeAndClass());
		lc.getStudentNum().setText(student.getStudentNum());
		
		if (student.getSex().equals("ÄÐ")) {
			lc.getSex().setImageResource(R.drawable.ic_sex_boy);
		}

		return convertView;
	}

	public void addAll(List<Student> student) {
		this.students = student;
		notifyDataSetChanged();
	}

	public void clear() {
		students.clear();
		notifyDataSetChanged();
	}

	private static class ListCell {
		public ListCell(TextView studentName, TextView gradeAndClass,
				TextView studentNum, ImageView sex) {
			this.studentName = studentName;
			this.gradeAndClass = gradeAndClass;
			this.studentNum = studentNum;
			this.sex = sex;
		}

		private TextView studentName;
		private TextView gradeAndClass;
		private TextView studentNum;
		private ImageView sex;

		public TextView getStudentName() {
			return studentName;
		}

		public TextView getGradeAndClass() {
			return gradeAndClass;
		}

		public TextView getStudentNum() {
			return studentNum;
		}

		public ImageView getSex() {
			return sex;
		}

	}
}
