package com.test;

import java.io.IOException;

import com.example.jianzhitao.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityMy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listitem);
		ImageView img=(ImageView) findViewById(R.id.img);
		//img.setImageDrawable(getResources().getDrawable(R.drawable.launcher));
		try {
			byte[] data=ImageService.getImage("http://10.0.2.2:8080/test/nihao.jpg");
			Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
			img.setImageBitmap(bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
