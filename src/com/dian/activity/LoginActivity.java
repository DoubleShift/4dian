package com.dian.activity;

import java.util.Map;
import java.util.Set;

import com.dian.util.DBManager;
import com.me.dian.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText edtEmail;
	private EditText edtPassWord;

	private String StrEmail;
	private String StrPassword;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		edtEmail = (EditText) findViewById(R.id.login_email);
		edtPassWord = (EditText) findViewById(R.id.login_password);

		Button login = (Button) findViewById(R.id.btn_login_page);
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				StrEmail = edtEmail.getText().toString();
				StrPassword = edtPassWord.getText().toString();

				if (StrEmail == null || StrPassword == null) {
					Toast.makeText(LoginActivity.this, "邮箱或者密码为空请重新填写！", 3000)
							.show();
				}

				else {

					DBManager db = new DBManager(LoginActivity.this);
					boolean isSuccess = false;
					isSuccess = db.login(StrEmail, StrPassword);
					if (isSuccess == true) {
						
						//登录成功把uid写入share
						SharedPreferences share = LoginActivity.this.getSharedPreferences("4dian", 0);
						SharedPreferences.Editor localEditor = share.edit();
						localEditor.putString("uid", "1");
						localEditor.commit();
						
					
						Toast.makeText(LoginActivity.this, "登录成功！", 3000)
						.show();
						Intent intent = new Intent(LoginActivity.this,
								MainActivity.class);
						LoginActivity.this.startActivity(intent);
						overridePendingTransition(R.anim.push_left_in,
								R.anim.push_left_out);
						LoginActivity.this.finish();
					}
					else{
						Toast.makeText(LoginActivity.this, "登录失败，请重新登录！", 3000)
						.show();
					}
					db.closeDB();

				}

			}
		});

		Button sign_up = (Button) findViewById(R.id.login_signup);
		sign_up.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent2 = new Intent(LoginActivity.this,
						SignUpActivity.class);
				LoginActivity.this.startActivity(intent2);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				LoginActivity.this.finish();
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

}
