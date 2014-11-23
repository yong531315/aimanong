package com.example.jianzhitao.activity;

import com.example.jianzhitao.MainActivity;
import com.example.jianzhitao.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
public class FragmentGuanYu extends Fragment {
	private RelativeLayout layout;
	private static FragmentGuanYu frag;
	public static FragmentGuanYu newInstance(){
		if(frag==null){
			frag=new FragmentGuanYu();
		}
			return frag;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layout=(RelativeLayout) inflater.inflate(R.layout.fragment_guanyuwomen, container, false);
		ListView list_guanyuwomen=(ListView) layout.findViewById(R.id.list_guanyuwomen);
		list_guanyuwomen.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 4:
					Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567"));
					startActivity(intent);
					break;

				default:
					break;
				}
				
			}
		});
		return layout;
	}

}
