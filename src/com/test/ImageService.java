package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageService {

	public static byte[] getImage(String url) throws IOException {
		URL mUrl=new URL(url);
		HttpURLConnection conn=(HttpURLConnection) mUrl.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		int re=conn.getResponseCode();
		if(conn.getResponseCode()==200){
		InputStream input=conn.getInputStream();
		byte[] data=StreamTool.read(input);
		return data;
		}
		return null;
	}

}
