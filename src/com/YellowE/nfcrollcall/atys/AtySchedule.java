package com.YellowE.nfcrollcall.atys;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.YellowE.nfcrollcall.R;

public class AtySchedule extends Activity {
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t[] = new TextView[7];// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private ViewPager mPager; // 页卡内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitTextView();
		InitViewPager();
		InitImageView();
	}

	/**
	 * 初始化头标
	 */
	private void InitTextView() {
		t[0] = (TextView) findViewById(R.id.main_course_txv_monday);
		t[1] = (TextView) findViewById(R.id.main_course_txv_tuesday);
		t[2] = (TextView) findViewById(R.id.main_course_txv_wednesday);
		t[3] = (TextView) findViewById(R.id.main_course_txv_thursday);
		t[4] = (TextView) findViewById(R.id.main_course_txv_friday);
		t[5] = (TextView) findViewById(R.id.main_course_txv_saturday);
		t[6] = (TextView) findViewById(R.id.main_course_txv_sunday);
		
		t[0].setOnClickListener(new MyOnClickListener(0));
		t[1].setOnClickListener(new MyOnClickListener(1));
		t[2].setOnClickListener(new MyOnClickListener(2));
		t[3].setOnClickListener(new MyOnClickListener(3));
		t[4].setOnClickListener(new MyOnClickListener(4));
		t[5].setOnClickListener(new MyOnClickListener(5));
		t[6].setOnClickListener(new MyOnClickListener(6));
	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.main_course_vpgr_table_view);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.item1, null));
		listViews.add(mInflater.inflate(R.layout.item2, null));
		listViews.add(mInflater.inflate(R.layout.item3, null));
		listViews.add(mInflater.inflate(R.layout.item4, null));
		listViews.add(mInflater.inflate(R.layout.item5, null));
		listViews.add(mInflater.inflate(R.layout.item6, null));
		listViews.add(mInflater.inflate(R.layout.item7, null));
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	/**
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 7) + 1 ;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(15, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
			System.out.println("切换到页面：" + index);
		}
	};

	/**
	 * 页卡切换监听
	 */
	@SuppressLint("ResourceAsColor")
	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			float fromXDelta = currIndex * offset;
			float toXDelta = arg0 * offset;
			
			animation = new TranslateAnimation(fromXDelta, toXDelta, 0, 0);
			
			t[currIndex].setTextColor(0xff2e94da);
			
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
			
			t[arg0].setTextColor(R.color.white);
			
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
