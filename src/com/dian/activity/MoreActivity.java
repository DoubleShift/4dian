package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

public class MoreActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_more);

		// Ŀ���趨
		TableRow row0 = (TableRow) findViewById(R.id.more_page_row0);
		row0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						GoalSettingActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});

		// ������Ϣ
		TableRow row2 = (TableRow) findViewById(R.id.more_page_row2);
		row2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						PersonalActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});

		// ͷ������
		TableRow row3 = (TableRow) findViewById(R.id.more_page_row3);
		row3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						AvatarSettingActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});

		// �޸�����
		TableRow row4 = (TableRow) findViewById(R.id.more_page_row4);
		row4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						ChangePasswordActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});

		// �����
		TableRow row5 = (TableRow) findViewById(R.id.more_page_row5);
		row5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						FeedbackActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});
		// ����
		TableRow row7 = (TableRow) findViewById(R.id.more_page_row7);
		row7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						AboutActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});
		
		Button btn_logout = (Button) findViewById(R.id.btn_logout);
		btn_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MoreActivity.this,
						LoginActivity.class);
				MoreActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				MoreActivity.this.finish();
				

			}
		});
	}
}
