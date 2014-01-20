package com.dian.activity;

import java.util.ArrayList;

import com.dian.adapter.MyFragmentPagerAdapter;
import com.me.dian.R;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class TimeManagementActivity extends FragmentActivity {
	private static final String TAG = "TimeManagementActivity";
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private ImageView ivBottomLine;
	private TextView timemanagement_set_time, timemanagement_show_rate;

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one;
	private Resources resources;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_timemanagement);
		resources = getResources();
		InitWidth();
		InitTextView();
		InitViewPager();
	}

	private void InitTextView() {
		timemanagement_set_time = (TextView) findViewById(R.id.timemanagement_set_time);
		timemanagement_show_rate = (TextView) findViewById(R.id.timemanagement_show_rate);
	

		timemanagement_set_time.setOnClickListener(new MyOnClickListener(0));
		timemanagement_show_rate.setOnClickListener(new MyOnClickListener(1));
		
	}

	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.timemanagement_vPager);
		fragmentsList = new ArrayList<Fragment>();
		Fragment setTimeFragment = SetTimeFragment.newInstance();
		Fragment showRateFragment = ShowRateFragment.newInstance();
		

		fragmentsList.add(setTimeFragment);
		fragmentsList.add(showRateFragment);


		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private void InitWidth() {
		ivBottomLine = (ImageView) findViewById(R.id.timemanagement_cursor );
		bottomLineWidth = BitmapFactory.decodeResource(getResources(), R.drawable.tab_selected).getWidth();
		Log.d(TAG, "cursor imageview width=" + bottomLineWidth);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / 2.0 - bottomLineWidth) / 2);
		Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        ivBottomLine.setImageMatrix(matrix);
		Log.i("TimeManagementActivity", "offset=" + offset);
		

		position_one = (int) (screenW / 2.0);
	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, 0, 0, 0);
					timemanagement_show_rate.setTextColor(resources.getColor(R.color.black));
				} 
				timemanagement_set_time.setTextColor(resources
						.getColor(R.color.sw_button_bg));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					timemanagement_set_time.setTextColor(resources.getColor(R.color.black));
					
				} 
				timemanagement_show_rate.setTextColor(resources.getColor(R.color.sw_button_bg));
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			ivBottomLine.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
