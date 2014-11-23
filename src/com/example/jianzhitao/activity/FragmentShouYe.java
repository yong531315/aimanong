package com.example.jianzhitao.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.jianzhitao.MainActivity;
import com.example.jianzhitao.R;
import com.example.jianzhitao.adapter.AdapterArticle;
import com.example.jianzhitao.bean.article;
import com.example.jianzhitao.dal.ArticleService;
import com.example.jianzhitao.inter.FragDataSave;
import com.example.jianzhitao.thread.MyThreadGetData;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentShouYe extends Fragment implements OnRefreshListener {

	private View view;
	private ListView list_shouye;
	public SwipeRefreshLayout swipe_container;
	private static FragmentShouYe frag;
	private List<article> listArticle;
	private AdapterArticle adapter;
	private int sum=5;//记录总数
	private int num=5;//指定一页加载多少
	private MyThreadGetData myThread;//执行从网络获取数据的线程
	private TextView footer;//listview的脚
	MainActivity main;
	private boolean isLoading=false;
	private FragDataSave fragDataSave;
	public static FragmentShouYe newInstance(){
		if(frag==null){
			frag=new FragmentShouYe();
			
		}
			return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.fragment_shouye, container, false);
		swipe_container= (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
		list_shouye=(ListView) view.findViewById(R.id.list_shouye);
		 //添加footer
		footer=(TextView) inflater.inflate(R.layout.shouyelistfooter, null, false);
			//点击加载更多
			footer.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(isLoading==false){
						isLoading=true;//标记是否正在加载
					swipe_container.setRefreshing(true);
					footer.setText("正在粪力加载.......");
					getdata(sum, sum+num);
					sum=sum+num;
					}
					
				}
			});
		//在createview开始的时候创建article的list,并初始化数据,为空数据,并在onresume()的时候加载第一批数据
		//这样的话，也不用判断是不是第一次加载的标记了
			listArticle=new ArrayList<article>();
				adapter=new AdapterArticle(getActivity(), listArticle);
				list_shouye.addFooterView(footer); //第一次加载的时候不显示
				 list_shouye.setAdapter(adapter);
/*				getdata(0,0,1);
				try {思想值得鼓励-->学以致用
					myThread.join();//第一次的时候必须是同步的，让其在数据读取完成之后再显示
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		swipe_container.setOnRefreshListener(this);
		swipe_container.setColorScheme(android.R.color.holo_orange_light, android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_red_light);
	
		return view;
	}
	@Override
	public void onRefresh() {
//		handler.postDelayed(new Runnable() {
//						@Override
//						public void run() {
//						
//						}
//							
//						}
//					}, 5000);
		Toast.makeText(getActivity(), "niai", 1).show();
		if(isLoading==false){
			isLoading=true;
		//	listArticle.clear();//因该是从已加载的位置到最新的数据
			getdata(0, 1);
			
		}
		
		
		
	}
	

//handler处理线程返回的数据
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
         switch(msg.what){
         case 0://标明是getdata发来的数据
        	 //把返回的list添加到现有的list中去
        	 listArticle.addAll((List<article>)msg.obj);
        	    footer.setText("猛击加载更多");
				adapter.notifyDataSetChanged();
				isLoading=false;
				swipe_container.setRefreshing(false);
				Toast.makeText(getActivity(), "加载成功", 1).show();
				//加载完第一批数据后，让footer可见
				footer.setVisibility(View.VISIBLE);
        	 break;
        	 
         default:
        		 break;
         }
			
		};
	};
			
	public  void getdata( int star, int off) {//异步从网络获取资源
		//开启线程去网上的数据库读取数据
		myThread=	new MyThreadGetData( handler,star, off);
		myThread.start();
	}
}
