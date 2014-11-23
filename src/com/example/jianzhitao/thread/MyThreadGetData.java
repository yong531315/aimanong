package com.example.jianzhitao.thread;

import java.util.ArrayList;
import java.util.List;

import com.example.jianzhitao.bean.article;
import com.example.jianzhitao.dal.ArticleService;

import android.os.Handler;
import android.util.Log;

public class MyThreadGetData extends Thread {
	private int mStar;
	private int mOff;
	private Handler mHandler;
	private List<article> list;
//	int mIsFirst;//鏍囪瘑鏄惁鏄涓�娆″姞杞給涓篺alse,1涓簍rue
	public MyThreadGetData(Handler handler, int star, int off) {
		mStar=star;
		mOff=off;
		mHandler=handler;
		list=new ArrayList<article>();
	}
	
	@Override
	public void run() {
		super.run();
		//浠庣綉缁滄暟鎹簱璇诲彇鏁版嵁
		ArticleService artService=new ArticleService();
		try {
			list=artService.getData(mStar,mOff);
		} catch (Exception e) {
			Log.i("result", "璇诲彇澶辫触");
			e.printStackTrace();
		}
		mHandler.sendMessage(mHandler.obtainMessage( 0, list));
		
	}
	
	

}
