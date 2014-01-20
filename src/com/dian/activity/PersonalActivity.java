package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class PersonalActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.personal_setting);
    	
    	Button back = (Button) findViewById(R.id.personal_setting_back);
        back.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			PersonalActivity.this.finish();

    		}
    	});
        
        Button save = (Button) findViewById(R.id.personal_setting_save);
        save.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			
    			
    			PersonalActivity.this.finish();

    		}
    	});
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}


}
