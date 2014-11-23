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

public class FragmentXunZhaoRenCai extends Fragment implements IXListViewListener {

	private static FragmentXunZhaoRenCai fragXunZhaoRenCai;
	private View layout;
	private XListView xlist_xunzhaorencai;
	private Handler mHandler;
	public static FragmentXunZhaoRenCai newInstance(){
		if(fragXunZhaoRenCai==null){
			fragXunZhaoRenCai=new FragmentXunZhaoRenCai();
		}
		return fragXunZhaoRenCai;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layout=inflater.inflate(R.layout.fragmeng_xunzhaorencai, container, false);
		xlist_xunzhaorencai=(XListView) layout.findViewById(R.id.xlist_xunzhaorencai);
		String[] strs=getResources().getStringArray(R.array.barname);
		ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1, strs);
		xlist_xunzhaorencai.setAdapter(adapter);
		xlist_xunzhaorencai.setXListViewListener(this);
		mHandler=new Handler();
		return layout;
		
	}
	
	private void onLoad() {
		xlist_xunzhaorencai.stopRefresh();
		xlist_xunzhaorencai.stopLoadMore();
		xlist_xunzhaorencai.setRefreshTime("刚刚");
	}

	// 刷新
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

	// 加载更多
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
}
