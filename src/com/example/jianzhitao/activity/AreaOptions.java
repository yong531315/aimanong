package com.example.jianzhitao.activity;

import com.example.jianzhitao.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class AreaOptions extends ActionBarActivity {

	private ActionBar bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.areaoptions);
		bar = getSupportActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.holo_green_dark));
		bar.setDisplayShowHomeEnabled(true);
		bar.setDisplayHomeAsUpEnabled(true);// ��Ҫ�������ļ����ƶ���Ҫ���ص�activity
		bar.setTitle("ѡ������");
	}
}
