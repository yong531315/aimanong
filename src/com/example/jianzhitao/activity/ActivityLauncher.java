package com.example.jianzhitao.activity;

import com.example.jianzhitao.MainActivity;
import com.example.jianzhitao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class ActivityLauncher extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.launcher);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent=new Intent(ActivityLauncher.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 1500);
	}

}
