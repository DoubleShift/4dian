package com.dian.activity;

import com.me.dian.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class VoiceActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_voice);
		WebView wv = (WebView) findViewById(R.id.wv);
		wv.loadUrl("http://4dian.sinaapp.com/community/voice");
		wv.getSettings().setJavaScriptEnabled(false);
	}

}
