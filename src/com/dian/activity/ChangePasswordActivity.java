package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class ChangePasswordActivity extends Activity {
	
	private Button back ;
	private Button save ;
	private Button savepwd ;
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.change_password);
    	
    	
    	savepwd = (Button) findViewById(R.id.btn_change_password_savepwd);
    	savepwd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    	back = (Button) findViewById(R.id.change_password_back);
        back.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			ChangePasswordActivity.this.finish();

    		}
    	});
        
        save = (Button) findViewById(R.id.change_password_save);
        save.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			
    			
    			ChangePasswordActivity.this.finish();

    		}
    	});
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

}
