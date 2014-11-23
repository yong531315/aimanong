package com.example.jianzhitao.dal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.jianzhitao.bean.News;
import com.example.jianzhitao.bean.article;

/**
 * @author ysnow
 *操纵网络数据库的类
 */
public class ArticleService {

	public  List<article> getData(int mStar, int mOff) throws  Exception {
		String uri="http://gaishi.lam01.ectomcat.com/AiMaNong/servlet/GetData?star="+mStar+"&off="+mOff;
		HttpClient client=new DefaultHttpClient();
		HttpGet get=new HttpGet(uri);
		HttpResponse res=client.execute(get);
		int status=res.getStatusLine().getStatusCode();
		Log.i("status", status+"");
		if(status==200){
			HttpEntity entity=res.getEntity();
			String json=EntityUtils.toString(entity);
			if(json!=null){
				List<article> list=new ArrayList<article>();
				JSONArray jArray=new JSONArray(json);
				for(int i=0;i<jArray.length();i++){
					JSONObject jObj=jArray.getJSONObject(i);
					int id=jObj.getInt("id");
					String title=jObj.getString("title");
					String contpre=jObj.getString("contpre");
					String contmain=jObj.getString("contmain");
					String pdata=jObj.getString("pdata");
					list.add(new article(id, title, contpre, contmain, pdata));
				}
				return list;
			}
		}
			
		return null;
	}


}
