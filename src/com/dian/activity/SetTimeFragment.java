package com.dian.activity;

import com.me.dian.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SetTimeFragment extends Fragment {
    private static final String TAG = "SetTimeFragment";


    static SetTimeFragment newInstance() {
    	SetTimeFragment newFragment = new SetTimeFragment();
        return newFragment;

    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "SetTimeFragment-----onCreate");
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "SetTimeFragment-----onCreateView");
        View view = inflater.inflate(R.layout.timemanagement_set_time_layout, container, false);       
    	init(view);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SetTimeFragment-----onDestroy");
    }
    
    public void init(View view){
    	//初始化
    }

	

}