package com.YellowE.nfcrollcall.atys;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.Student;
import com.YellowE.nfcrollcall.dao.StudentDao;
import com.YellowE.nfcrollcall.util.Coverter;

public class AtyEditStudent extends Activity{
	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;
	
	private int studentID;
	private EditText studentName;
	private EditText StudnetNum;
	private EditText gradeAndClass;
	private EditText cardID;
	private Button boy;
	private Button girl;
	
	private String sex;
	private boolean sexFlag;
	
	private int courseID;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_edit_student);
		
		studentName = (EditText) findViewById(R.id.add_student_data_txv_name);
		StudnetNum = (EditText) findViewById(R.id.add_student_data_txv_stuNumber);
		gradeAndClass = (EditText) findViewById(R.id.add_student_data_txv_class);
		boy = (Button) findViewById(R.id.roll_call_status_btn_normal);
		girl = (Button) findViewById(R.id.roll_call_status_btn_late);
		cardID = (EditText) findViewById(R.id.add_student_data_txv_cardNum);

		Intent data = getIntent();
		courseID = data.getIntExtra(Config.KEY_COURSE_ID, 0);
		studentID = data.getIntExtra(Config.KEY_STUDENT_ID, 0);
		sex = data.getStringExtra(Config.KEY_STUDENT_SEX);
		studentName.setText(data.getStringExtra(Config.KEY_STUDENT_NAME));
		StudnetNum.setText(data.getStringExtra(Config.KEY_STUDENT_NUM));
		gradeAndClass.setText(data.getStringExtra(Config.KEY_STUDENT_CLASS));
		cardID.setText(data.getStringExtra(Config.KEY_STUDENT_CARDNUM));
		
		sexFlag = sex.equals("男")? true : false;
		setSex();
		
		findViewById(R.id.edit_student_rlyt_delete_student).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new StudentDao(AtyEditStudent.this).deleteCourseStudent(courseID, studentID);
				setResult(Config.ACTIVITY_RESULT_CODE_NEED_REFRESH);
				Toast.makeText(AtyEditStudent.this, "成功删除", Toast.LENGTH_LONG).show();
				finish();
			}
		});
		
		boy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sexFlag = true;
				setSex();
			}
		});
		
		girl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sexFlag = false;
				setSex();
			}
		});
		
		mAdapter = NfcAdapter.getDefaultAdapter(this);

		// Create a generic PendingIntent that will be deliver
		// to this activity. The NFC stack
		// will fill in the intent with the details of the
		// discovered tag before delivering to
		// this activity.
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		// Setup an intent filter for all MIME based dispatches
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		try {
			ndef.addDataType("*/*");
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("fail", e);
		}
		mFilters = new IntentFilter[] { ndef, };

		// Setup a tech list for all MifareClassic tags
		mTechLists = new String[][] { new String[] { MifareClassic.class
				.getName() } };
	}
	
	private void setSex() {
		boy.setSelected(sexFlag);
		girl.setSelected(!sexFlag);
		sex = sexFlag ? "男" : "女";
	}

	@Override
	public void onResume() {
		super.onResume();
		mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
				mTechLists);
	}
	
	@Override
	public void onNewIntent(Intent intent) {
		String uid = Coverter.getUid(intent);
		String text = "本标签的UID为" + "【" + uid + "】";
//		System.out.println(adapter.getPosition(uid.toString()));
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		cardID.setText("");
		cardID.setText(uid);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_aty_add_student, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.meneShowAtyAddStudent:
			new StudentDao(this).updataData(new Student(studentID,StudnetNum.getText().toString(), studentName.getText().toString(), sex, gradeAndClass.getText().toString(), cardID.getText().toString()));
			setResult(Config.ACTIVITY_RESULT_CODE_NEED_REFRESH);
			finish();
			break;
		default:
			break;
		}
		
		return true;
	}
}
