package com.dian.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.me.dian.R;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements
		OnCheckedChangeListener {
	public static TabHost tabHost;
	private RadioGroup radioGroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initTab();

	}

	public void initTab() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
				.setContent(new Intent(this, HomeActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("timeManagement")
				.setIndicator("timeManagement")
				.setContent(new Intent(this, TimeManagementActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("habit").setIndicator("habit")
				.setContent(new Intent(this, HabitActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("voice").setIndicator("voice")
				.setContent(new Intent(this, VoiceActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more")
				.setContent(new Intent(this, MoreActivity.class)));

		radioGroup = (RadioGroup) findViewById(R.id.main_tab_group);
		radioGroup.setOnCheckedChangeListener(this);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {

		int currentTab = tabHost.getCurrentTab();
		switch (checkedId) {
		case R.id.main_tab_home:
			setCurrentTabWithAnim(currentTab, 0, "home");
			break;
		case R.id.main_tab_timeManagement:
			setCurrentTabWithAnim(currentTab, 1, "timeManagement");
			break;
		case R.id.main_tab_habit:
			setCurrentTabWithAnim(currentTab, 2, "habit");
			break;
		case R.id.main_tab_voice:
			setCurrentTabWithAnim(currentTab, 3, "voice");
			break;
		case R.id.main_tab_more:
			setCurrentTabWithAnim(currentTab, 4, "more");
			break;
		default:
			break;
		}
	}

	private void setCurrentTabWithAnim(int now, int next, String tag) {
		// ��������ǹؼ������ж϶��������ķ���
		if (now > next) {
			tabHost.getCurrentView().startAnimation(
					AnimationUtils.loadAnimation(this, R.anim.push_right_out));
			tabHost.setCurrentTabByTag(tag);
			tabHost.getCurrentView().startAnimation(
					AnimationUtils.loadAnimation(this, R.anim.push_right_in));
		} else {
			tabHost.getCurrentView().startAnimation(
					AnimationUtils.loadAnimation(this, R.anim.push_left_out));
			tabHost.setCurrentTabByTag(tag);
			tabHost.getCurrentView().startAnimation(
					AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
				&& event.getAction() != KeyEvent.ACTION_UP) {
			exitBy2Click(); // ����˫���˳�����
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * ˫���˳�����
	 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // ׼���˳�
			Toast.makeText(this,R.string.exitToast, Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // ȡ���˳�
				}
			}, 2000); // ���2������û�а��·��ؼ���������ʱ��ȡ����ղ�ִ�е�����

		} else {
			MainActivity.this.finish();
			System.exit(0);
		}
	}

	public Dialog exitDialog() {
		Dialog dialog = null;
		CustomDialog.Builder alertDialog = new CustomDialog.Builder(
				MainActivity.this);
		alertDialog.setTitle(MainActivity.this.getString(R.string.app_alert));
		alertDialog.setMessage(MainActivity.this.getString(R.string.app_close));
		alertDialog.setPositiveButton(
				MainActivity.this.getString(R.string.btn_ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.this.finish();
						System.exit(0);
					}
				});
		alertDialog.setNegativeButton(
				MainActivity.this.getString(R.string.btn_cancel),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		dialog = alertDialog.create();
		return dialog;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int item_id = item.getItemId();
		switch (item_id) {
		case R.id.menu_exit:
			exitDialog().show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}