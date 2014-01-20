package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome);

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }.start();
    }

}