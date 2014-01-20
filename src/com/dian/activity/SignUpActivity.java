package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SignUpActivity  extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.sign_up);
    	
    	Button sign_up = (Button) findViewById(R.id.btn_singup_page);
    	sign_up.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			
    			Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
    			startActivity(intent);
    			overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    			SignUpActivity.this.finish();
    		}
    	});
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

}