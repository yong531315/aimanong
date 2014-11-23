package com.example.jianzhitao.activity;

import com.example.jianzhitao.R;
import com.example.jianzhitao.lxlistview.XListView;
import com.example.jianzhitao.lxlistview.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class FragmentXunZhaoJianZhi extends Fragment implements IXListViewListener {

	private static FragmentXunZhaoJianZhi fragXunZhaoJianZhi;
	private View layout;
	private XListView xlist_xunzhaojianzhi;
	Handler mHandler;
	public static FragmentXunZhaoJianZhi newInstance(){
		if(fragXunZhaoJianZhi==null){
			fragXunZhaoJianZhi=new FragmentXunZhaoJianZhi();
		}
		return fragXunZhaoJianZhi;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(getActivity(), "onDestroyxun", 1).show();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layout=inflater.inflate(R.layout.fragment_xunzhaojianzhi, container, false);
		xlist_xunzhaojianzhi=(XListView) layout.findViewById(R.id.xlist_xunzhaojianzhi);
		String[] strs=getResources().getStringArray(R.array.barname);
		ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1, strs);
		xlist_xunzhaojianzhi.setPullLoadEnable(true);
		xlist_xunzhaojianzhi.setAdapter(adapter);
		xlist_xunzhaojianzhi.setXListViewListener(this);
		mHandler=new Handler();
		return layout;
		
	}
	private void onLoad() {
		xlist_xunzhaojianzhi.stopRefresh();
		xlist_xunzhaojianzhi.stopLoadMore();
		xlist_xunzhaojianzhi.setRefreshTime("�ո�");
	}

	// ˢ��
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				//getData();
				//mListView.setAdapter(mAdapter1);
				onLoad();
			}
		}, 2000);
	}

	// ���ظ��
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				//getData();
				//mAdapter1.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
	private void getData() {
		// TODO Auto-generated method stub
		
	}
}
