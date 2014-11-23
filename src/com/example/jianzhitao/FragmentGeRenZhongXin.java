package com.example.jianzhitao;

import com.example.jianzhitao.activity.FragmentShouYe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentGeRenZhongXin extends Fragment {
	private View layout;
	private Button btn_exit;
	private static FragmentGeRenZhongXin fragGRZX;
	public static FragmentGeRenZhongXin newInstance() {
		if(fragGRZX==null){
			fragGRZX=new FragmentGeRenZhongXin();
		}
		return fragGRZX;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layout=inflater.inflate(R.layout.fragment_gerenzhongxin, container, false);
		btn_exit=(Button) layout.findViewById(R.id.btn_exit);
		//退出登陆:更改sharepreferences/并且返回主界面
		btn_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "退出登陆成功", 1).show();
				getActivity().getSharedPreferences("user", 0).edit().putBoolean("isLogin", false).commit();
				FragmentTransaction tran=getFragmentManager().beginTransaction();
				tran.hide(fragGRZX);
				 MainActivity mMain = (MainActivity) getActivity();
				tran.show(FragmentShouYe.newInstance()).commit();
				mMain.curState=1;
			}
		});
		return layout;
	}

}
