package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GuideActivity extends Activity implements OnClickListener {

	private Button btn_signup;

	private Button btnLogin;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		btn_signup = (Button) findViewById(R.id.btn_signup);
		btnLogin = (Button) findViewById(R.id.btn_login);

		btn_signup.setOnClickListener(this);
		btnLogin.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_signup: {
			Intent intent = new Intent(GuideActivity.this, SignUpActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.flipper_flip_in, R.anim.flipper_flip_in);
			GuideActivity.this.finish();
		}
			break;
		case R.id.btn_login: {
			Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.flipper_flip_in, R.anim.flipper_flip_in);
			GuideActivity.this.finish();
		}
			break;
		default:
			break;
		}
	}

}
