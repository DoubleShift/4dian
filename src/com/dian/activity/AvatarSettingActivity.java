package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


public class AvatarSettingActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.avatar_setting);
    	
    	Button back = (Button) findViewById(R.id.avatar_setting_back);
        back.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			AvatarSettingActivity.this.finish();

    		}
    	});
        
        Button save = (Button) findViewById(R.id.avatar_setting_save);
        save.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			
    			
    			AvatarSettingActivity.this.finish();

    		}
    	});
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}


}
