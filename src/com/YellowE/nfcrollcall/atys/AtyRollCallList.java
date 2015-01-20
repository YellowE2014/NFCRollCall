package com.YellowE.nfcrollcall.atys;

import java.util.Iterator;

import android.app.AlertDialog;
import android.app.ListActivity;
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
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.R;
import com.YellowE.nfcrollcall.bean.RollCallRecord;
import com.YellowE.nfcrollcall.dao.RollCallRecordDao;
import com.YellowE.nfcrollcall.util.Coverter;

public class AtyRollCallList extends ListActivity {

	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	private Button btnNormal;
	private Button btnLate;
	private TextView txvTimes;

	private int courseID;
	private int times;

	private int status;

	private AtyRollCallListAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_rollcall_list);

		Intent data = getIntent();
		courseID = Integer.parseInt(data.getStringExtra(Config.KEY_COURSE_ID));

		adapter = new AtyRollCallListAdapter(this);
		setListAdapter(adapter);

		mAdapter = NfcAdapter.getDefaultAdapter(this);

		btnNormal = (Button) findViewById(R.id.roll_call_status_btn_normal);
		btnLate = (Button) findViewById(R.id.roll_call_status_btn_late);
		txvTimes = (TextView) findViewById(R.id.roll_call_times_txv_text);

		times = 1;
		
		status = 0;
		setStatus();

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

		btnNormal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				status = 0;
				setStatus();
			}
		});

		btnLate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				status = 1;
				setStatus();
			}
		});

		findViewById(R.id.roll_call_times).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						NumberPicker mPicker = new NumberPicker(AtyRollCallList.this);
		                mPicker.setMinValue(1);
		                mPicker.setMaxValue(100);
		                mPicker.setOnValueChangedListener(new OnValueChangeListener() {
		                    
		                    @Override
		                    public void onValueChange(NumberPicker picker, int oldVal, int newVal)
		                    {
		                    	txvTimes.setText("第"+ newVal +"次");
		                    	times = newVal;
		                    }
		                });
		                
		                
		                AlertDialog mAlertDialog = new AlertDialog.Builder(AtyRollCallList.this)
		                .setTitle("请选择点名次数").setView(mPicker).setPositiveButton("确定",null).create();
		                mAlertDialog.show();
					}
				});
		//
		// findViewById(R.id.roll_call_cancel).setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// finish();
		// }
		// });
		//
		// findViewById(R.id.roll_call_confirm).setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// RollCallRecord rcRecord = null;
		//
		// Iterator<RollCallRecord> it =
		// adapter.getRollCallRecords().iterator();
		// while (it.hasNext()) {
		// rcRecord = it.next();
		// new RollCallRecordDao(AtyRollCallList.this).insertData(rcRecord);
		// }
		// finish();
		// }
		// });
	}

	// private void showTimesPicker() {
	//
	// final NumberPicker numberPicker;
	//
	// LayoutInflater inflater = (LayoutInflater)
	// getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// View contentview = inflater.inflate(
	// R.layout.pop_window_number_picker_view, null);
	// contentview.setFocusable(true); // 这个很重要
	// contentview.setFocusableInTouchMode(true);
	// timesChooser = new PopupWindow(contentview, LayoutParams.WRAP_CONTENT,
	// LayoutParams.WRAP_CONTENT);
	// timesChooser.setFocusable(true);
	// timesChooser.setOutsideTouchable(false);
	// // 设置允许在外点击消失
	// timesChooser.setOutsideTouchable(true);
	// timesChooser.setBackgroundDrawable(new BitmapDrawable());
	// contentview.setOnKeyListener(new View.OnKeyListener() {
	//
	// @Override
	// public boolean onKey(View v, int keyCode, KeyEvent event) {
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// timesChooser.dismiss();
	//
	// return true;
	// }
	// return false;
	// }
	// });
	// timesChooser.showAtLocation(findViewById(R.id.roll_call_times),
	// Gravity.CENTER, 0, 0);
	// numberPicker = (NumberPicker) findViewById(R.id.roll_call_numberPicker);
	// numberPicker.setMaxValue(100);
	// numberPicker.setMinValue(1);
	// }

//	private void showPopWindow(Context context, View parent) {
//		LayoutInflater inflater = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		final View vPopWindow = inflater.inflate(
//				R.layout.pop_window_number_picker_view, null, false);
//		// 宽300 高300
//		final PopupWindow popWindow = new PopupWindow(vPopWindow, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
//		
//		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker_view_numberPicker);
//		
//		
//		
//		
//		Button okButton = (Button) vPopWindow.findViewById(R.id.number_picker_view_btn_submit);
//		okButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//			}
//		});
//
//		Button cancleButton = (Button) vPopWindow.findViewById(R.id.number_picker_view_btn_cancel);
//		cancleButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				popWindow.dismiss(); // Close the Pop Window
//			}
//		});
//
//		popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
//
//	}

	private void setStatus() {
		if (status == 0) {
			btnNormal.setSelected(true);
			btnLate.setSelected(false);
		} else if (status == 1) {
			btnNormal.setSelected(false);
			btnLate.setSelected(true);
		}
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
		// System.out.println(adapter.getPosition(uid.toString()));
		if (adapter.contains(uid.toString())) {
			Toast.makeText(this, "此学生已签到", Toast.LENGTH_SHORT).show();
		} else if (adapter.add(uid.toString(), String.valueOf(courseID), status , times)) {

			Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

		} else {

			Toast.makeText(this, "此课程没有该生", Toast.LENGTH_SHORT).show();
		}

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
			RollCallRecord rcRecord = null;
			Iterator<RollCallRecord> it = adapter.getRollCallRecords()
					.iterator();
			while (it.hasNext()) {
				rcRecord = it.next();
				new RollCallRecordDao(AtyRollCallList.this)
						.insertData(rcRecord);
			}
			finish();
			break;
		default:
			break;
		}

		return true;
	}
}
