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

public class HomeActivity extends FragmentActivity {
	private static final String TAG = "HomeActivity";
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private ImageView ivBottomLine;
	private TextView home_tab_personl_info, home_tab_getup, home_tab_time_rate;

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one;
	private int position_two;
	private Resources resources;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_home);
		resources = getResources();
		InitWidth();
		InitTextView();
		InitViewPager();
	}

	private void InitTextView() {
		home_tab_personl_info = (TextView) findViewById(R.id.home_tab_personl_info);
		home_tab_getup = (TextView) findViewById(R.id.home_tab_getup);
		home_tab_time_rate = (TextView) findViewById(R.id.home_tab_time_rate);

		home_tab_personl_info.setOnClickListener(new MyOnClickListener(0));
		home_tab_getup.setOnClickListener(new MyOnClickListener(1));
		home_tab_time_rate.setOnClickListener(new MyOnClickListener(2));
	}

	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.home_vPager);
		fragmentsList = new ArrayList<Fragment>();
		
		Fragment personalInfoFragment = PersonalInfoFragment.newInstance();
		Fragment getupFragment = GetupFragment.newInstance();
		Fragment timeRateFragment = TimeRateFragment.newInstance();
		
		fragmentsList.add(personalInfoFragment);
		fragmentsList.add(getupFragment);
		fragmentsList.add(timeRateFragment);

		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private void InitWidth() {
		ivBottomLine = (ImageView) findViewById(R.id.home_cursor);
		bottomLineWidth = BitmapFactory.decodeResource(getResources(), R.drawable.tab_selected).getWidth();
		Log.d(TAG, "cursor imageview width=" + bottomLineWidth);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / 3.0 - bottomLineWidth) / 2);
		Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        ivBottomLine.setImageMatrix(matrix);
		Log.i("HomeActivity", "offset=" + offset);

		position_one = (int) (screenW / 3.0);
		position_two = position_one * 2;
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
					home_tab_getup.setTextColor(resources.getColor(R.color.black));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, 0, 0, 0);
					home_tab_time_rate.setTextColor(resources.getColor(R.color.black));
				} 
				home_tab_personl_info.setTextColor(resources.getColor(R.color.sw_button_bg));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					home_tab_personl_info.setTextColor(resources.getColor(R.color.black));
					
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_one, 0, 0);
					home_tab_time_rate.setTextColor(resources.getColor(R.color.black));

				}
				home_tab_getup.setTextColor(resources.getColor(R.color.sw_button_bg));
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_two, 0,
							0);
					home_tab_personl_info.setTextColor(resources.getColor(R.color.black));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_two, 0, 0);
					home_tab_getup.setTextColor(resources.getColor(R.color.black));
				} 
				home_tab_time_rate.setTextColor(resources.getColor(R.color.sw_button_bg));
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