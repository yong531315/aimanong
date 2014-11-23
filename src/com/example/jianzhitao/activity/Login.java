package com.example.jianzhitao.activity;

import com.example.jianzhitao.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Window;

public class Login extends Activity{

	private Editor edit;
	private SharedPreferences preference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//登录：
		//从输入框中获得数据--->验证数据不为空切用户名为11位的手机号--->验证成功后将数据封装到user对象，然后
		//封装成json数据发送到服务器进行验证--->得到验证后的结果——>如果成功---设置登录状态为真-->获取用户数据
		
		 preference=getApplicationContext().getSharedPreferences("user", 0);
		edit=preference.edit();
		edit.putBoolean("isLogin", true);
		edit.commit();
		
	}
}
