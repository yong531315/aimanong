package com.example.jianzhitao.adapter;

import java.util.List;

import com.example.jianzhitao.R;
import com.example.jianzhitao.bean.article;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterArticle extends BaseAdapter {

	private List<article> listArticle;
	private Context mContext;
	private LayoutInflater inflater;
	private ViewHolder viewholder;
	private TextView tv;
	public AdapterArticle(Context pconContext,List<article> listArticle){
		this.listArticle=listArticle;
		this.mContext=pconContext;
		this.inflater=LayoutInflater.from(pconContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.i("size", listArticle.size()+"1");		
		return listArticle.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listArticle.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=inflater.inflate(R.layout.lay_articleitem, parent,false);
			viewholder=new ViewHolder();
			viewholder.tv_article_title=(TextView) convertView.findViewById(R.id.tv_article_title);
			viewholder.tv_article_precont=(TextView) convertView.findViewById(R.id.tv_article_precont);
			viewholder.tv_pinglun=(TextView) convertView.findViewById(R.id.tv_pinglun);
			convertView.setTag(viewholder);
		}else{
			viewholder=(ViewHolder) convertView.getTag();
		}
		article article=listArticle.get(position);
		viewholder.tv_article_title.setText(article.getTitle());
		viewholder.tv_article_precont.setText(article.getContpre());
		viewholder.tv_pinglun.setText("评论    "+20);
		return convertView;
	}
	
	private class ViewHolder{
		public TextView tv_article_title;
		public TextView tv_article_precont;
		public TextView tv_pinglun;
		
	}

}
