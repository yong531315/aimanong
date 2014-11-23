package com.example.jianzhitao.activity;

import com.example.jianzhitao.R;
import com.example.jianzhitao.lxlistview.XListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFaBuJianZhi extends Fragment {

	private static FragmentFaBuJianZhi fragFBJZ;
	private View layout;
	public static FragmentFaBuJianZhi newInstance(){
		if(fragFBJZ==null){
			fragFBJZ=new FragmentFaBuJianZhi();
		}
		return fragFBJZ;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layout=inflater.inflate(R.layout.fragment_fabujianzhi, container, false);
		return layout;
		
	}
}
