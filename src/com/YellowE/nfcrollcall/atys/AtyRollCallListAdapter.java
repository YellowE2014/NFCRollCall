package com.YellowE.nfcrollcall.atys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.RollCallRecord;
import com.YellowE.nfcrollcall.bean.Student;
import com.YellowE.nfcrollcall.dao.StudentDao;

public class AtyRollCallListAdapter extends BaseAdapter {
	private Context context = null;
	private List<Student> students = new ArrayList<Student>();
	private List<String> cardIDs = new ArrayList<String>();
	private List<RollCallRecord> rcRecords = new ArrayList<RollCallRecord>();
	
	private String[] strStatus= {"正常","迟到"};
	
	public AtyRollCallListAdapter(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public List<Student> getStudents() {
		return students;
	}
	
	public List<RollCallRecord> getRollCallRecords() {
		return rcRecords;
	}

	@Override
	public int getCount() {
		return rcRecords.size();
	}

	@Override
	public RollCallRecord getItem(int position) {
		return rcRecords.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.lstv_item_rollcall, null);
			convertView.setTag(new ListCell((TextView) convertView
					.findViewById(R.id.rollcall_item_txv_studentName),
					(TextView) convertView
							.findViewById(R.id.rollcall_item_txv_studentNum),
					(TextView) convertView
							.findViewById(R.id.rollcall_item_txv_status),
					(TextView) convertView
							.findViewById(R.id.rollcall_item_txv_date),
					(TextView) convertView
							.findViewById(R.id.rollcall_item_txv_time)));
		}

		ListCell lc = (ListCell) convertView.getTag();

		RollCallRecord rcRecord = getItem(position);

		lc.getStudentName().setText(rcRecord.getStudent().getStudentName());
		// lc.getRollCallStatus().setText(student.getGradeAndClass());
		lc.getStudentNum().setText(rcRecord.getStudent().getStudentNum());
		lc.getRollCallDate().setText(rcRecord.getDate());
		lc.getRollCallTime().setText(rcRecord.getTime());
		lc.getRollCallStatus().setText(strStatus[rcRecord.getStatus()]);

		return convertView;
	}

	public void addAll(List<Student> student) {
		this.students = student;
		notifyDataSetChanged();
	}

	public void clear() {
		cardIDs.clear();
		students.clear();
		notifyDataSetChanged();
	}

	public void add(Student student) {
		students.add(student);
		notifyDataSetChanged();
	}

	public boolean contains(String string) {
		return cardIDs.contains(string);
	}

	public boolean add(String cardID, String courseID, int status, int times) {
		Student student = new StudentDao(context).selcetDataByCardID(cardID,
				courseID);
		if (student != null) {
			cardIDs.add(cardID);
			students.add(student);
			System.out.println("第"+times + "次");
			rcRecords.add(new RollCallRecord(student, courseID, String.valueOf(student.getId()),times ,new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new SimpleDateFormat("hh:mm").format(new Date()), status) );
			notifyDataSetChanged();
			return true;
		} else
			return false;

	}

	private static class ListCell {
		public ListCell(TextView studentName, TextView studentNum,
				TextView rollCallStatus, TextView rollCallDate,
				TextView rollCallTime) {
			this.studentName = studentName;
			this.rollCallStatus = rollCallStatus;
			this.studentNum = studentNum;
			this.rollCallDate = rollCallDate;
			this.rollCallTime = rollCallTime;
		}

		private TextView studentName;
		private TextView studentNum;
		private TextView rollCallStatus;
		private TextView rollCallDate;
		private TextView rollCallTime;

		public TextView getRollCallDate() {
			return rollCallDate;
		}

		public TextView getRollCallTime() {
			return rollCallTime;
		}

		public TextView getStudentName() {
			return studentName;
		}

		public TextView getRollCallStatus() {
			return rollCallStatus;
		}

		public TextView getStudentNum() {
			return studentNum;
		}

	}

}
