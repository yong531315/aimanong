package com.test;

import java.util.List;

import com.example.jianzhitao.bean.article;
import com.example.jianzhitao.dal.ArticleService;

import android.test.AndroidTestCase;
import android.util.Log;

public class testnet extends AndroidTestCase {
List<article> list;
 public	void test(){
		ArticleService ss=new ArticleService();
		try {
			list=ss.getData(0, 0);
			article ar=list.get(0);
			String a=ar.getTitle();
		} catch (Exception e) {
			Log.i("nihao", "niaho");
			e.printStackTrace();
		}
	}
}
