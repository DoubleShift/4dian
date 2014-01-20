package com.dian.activity;

import com.me.dian.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class PersonalInfoFragment extends Fragment {
    private static final String TAG = "PersonalInfoFragment";


    static PersonalInfoFragment newInstance() {
    	PersonalInfoFragment newFragment = new PersonalInfoFragment();
        return newFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d(TAG, "PersonalInfoFragment-----onCreate");
        
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "PersonalInfoFragment-----onCreateView");
        View view = inflater.inflate(R.layout.home_personal_info_layout, container, false);       
    	init(view);
        return view;
        

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "PersonalInfoFragment-----onDestroy");
    }
    
    public void init(View view){
    	ProgressBar progressBarInvest = (ProgressBar) view.findViewById(R.id.progressBar_invest);
    	progressBarInvest.setProgress(16);
    	
    	
    	ProgressBar progressBarWaste = (ProgressBar) view.findViewById(R.id.progressBar_waste);
    	progressBarWaste.setProgress(7);
    	
    	ProgressBar progressBarTotal = (ProgressBar) view.findViewById(R.id.progressBar_total);
    	progressBarTotal.setProgress(25);
    	
    	Button getup = (Button) view.findViewById(R.id.button_get1up);
    	getup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						LoginActivity.class);
				getActivity().startActivity(intent);
				
				getActivity().finish();
				
			}
		});
    }

	

}