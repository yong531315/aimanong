package com.example.jianzhitao.adapter;

import com.example.jianzhitao.R;
import com.example.jianzhitao.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuDrawerAdapter extends BaseAdapter {

	private Context context;
	private String[] menunames;
	private TextView tv_listitem;;
	private LayoutInflater inflater;
	private int[] imgs;
	private ViewHolder viewholder;

	public MenuDrawerAdapter(Context pContext,String[] pMenuNames){
		this.context=pContext;
		this.menunames=pMenuNames;
		imgs=new int[]{R.drawable.ic_menu_more_on,R.drawable.ic_menu_deal_on,R.drawable.ic_menu_user_on,R.drawable.ic_menu_poi_on,R.drawable.ic_menu_more_on};
		inflater=LayoutInflater.from(pContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return	menunames.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return menunames[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			convertView=inflater.inflate(R.layout.drawer_list_item, parent, false);
			viewholder=new ViewHolder();
			viewholder.img=(ImageView) convertView.findViewById(R.id.img_listitem);
			viewholder.tv=(TextView) convertView.findViewById(R.id.tv_listitem);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
			viewholder.img.setImageResource(imgs[position]);
			viewholder.tv.setText(menunames[position]);
			return convertView;
	}
	
	private class ViewHolder{
		public ImageView img;
		public TextView tv;
	}

}
