package com.example.jianzhitao;

import java.util.ArrayList;
import java.util.List;

import com.example.jianzhitao.activity.AreaOptions;
import com.example.jianzhitao.activity.FragmentFaBuJianZhi;
import com.example.jianzhitao.activity.FragmentGuanYu;
import com.example.jianzhitao.activity.FragmentShouYe;
import com.example.jianzhitao.activity.FragmentXunZhaoJianZhi;
import com.example.jianzhitao.activity.FragmentXunZhaoRenCai;
import com.example.jianzhitao.activity.Login;
import com.example.jianzhitao.adapter.MenuDrawerAdapter;
import com.example.jianzhitao.bean.article;
import com.example.jianzhitao.inter.FragDataSave;
import com.example.jianzhitao.myview.MyTextView;

import android.R.anim;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.GpsStatus.NmeaListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
import com.baidu.location.LocationClientOption.LocationMode;

public class MainActivity extends FragmentActivity  {
	private ActionBar bar;
	private DrawerLayout mDrawerLayout;
	private CharSequence mTitle="首页";
	private ActionBarDrawerToggle mDrawerToggle;
	private FragmentManager fragManager;
	private FragmentTransaction fragTransaction;
	private long exitTime=0;
	private FragmentShouYe fragShouYe;
	private String[] barNames;
	private LayoutInflater inflater;
	private LinearLayout left_drawer;//左边的抽屉布局
	private Handler handler;
	private MyTextView tv_action_area;//显示定位的actionview
	private LocationClient locClient;
	private LocationClientOption locOption;
	private ConnectivityManager mConnectivityManager;//网络管理服务
	private NetworkInfo mNetworkInfo;
	public boolean isFirstLoaded=true;//是否第一次加载
	public View mDrawerView;//左侧drawer的view对象
	public String mDingweiText="正在定位.....";
	//下边是用于leftdrawer的
	private RelativeLayout relative_getenzhongxin;
	private LinearLayout linear_footer;
	private ListView list_menu;
	private String[] menunames;
	public int state=1;
	public int curState=1;//当前的页面状态
	private FrameLayout frame_login;
	private TextView nihao;
	private ImageView img_userimg;
	private Button btn_login;
	private FragmentGuanYu fragGuanYu;
	private FragmentGeRenZhongXin fragGRZX;
	private FragmentFaBuJianZhi fragFBJZ;
	private FragmentXunZhaoRenCai fragXunZhaoRenCai;
	private FragmentXunZhaoJianZhi fragXunZhaoJianZhi;
	//
	 Fragment[] fragments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_fade_in);
		setContentView(R.layout.activity_main);
		fragManager=getSupportFragmentManager();
	    mConnectivityManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
		mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		init();//初始化一些事情
		initFragMent();
		//启动的时候把首页添加进去
		if(savedInstanceState==null){
				FragmentTransaction tran=fragManager.beginTransaction();
				tran.add(R.id.content_frame, fragShouYe).commit();
		}
//		第一次进入的时候刷新加载首页的第一批数据
		//我也不知道为什么非要这样才行
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				fragShouYe.swipe_container.setRefreshing(true);				
			}
		}, 0);
		
		fragShouYe.getdata(0, 11);
	}

	private void init() {
		//启动的时候判断网络状态，如果有就进行定位，没有就不定位，并把actionview_area设置为定位失败
	      //还是onOptionsCreate是在onCreate之后调用的
	      if(mNetworkInfo==null){
				toast("网络异常或者没有链接，悲剧了");
				mDingweiText="定位失败";
			}else{
				initMap();
			}
		mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
		barNames=getResources().getStringArray(R.array.barname);
		bar=getActionBar();
		bar.setTitle(barNames[1]);
		bar.setBackgroundDrawable(getResources().getDrawable(android.R.color.holo_blue_dark));
		// set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
     // enable ActionBar app icon to behave as action to toggle nav drawer
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
        {
        	@Override
        	public void onDrawerClosed(View drawerView) {
        		super.onDrawerClosed(drawerView);
        		bar.setTitle(barNames[state]);
        		invalidateOptionsMenu();
        		
        	}
        	@Override
        	public void onDrawerOpened(View drawerView) {
        		super.onDrawerOpened(drawerView);
        		//当打开的时候隐藏一些actionview
        		mDrawerView=drawerView;
        		bar.setTitle("我爱码农");
        		invalidateOptionsMenu();
        	}
        	
        }
        ;
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //加载actionbar定位item布局
        inflater=LayoutInflater.from(this);
        //找到左边布局，并设置里边view的点击事件
        left_drawer= (LinearLayout) findViewById(R.id.left_drawer);
       initLeft();
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if(mDrawerView!=null){
		boolean isOpen=mDrawerLayout.isDrawerOpen(mDrawerView);
		menu.findItem(R.id.action_area_img).setVisible(!isOpen);
		menu.findItem(R.id.action_area).setVisible(!isOpen);}
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
       MenuItem action_area= menu.findItem(R.id.action_area);
      MenuItem action_area_img= menu.findItem(R.id.action_area_img);
      tv_action_area= (MyTextView) MenuItemCompat.getActionView(action_area);
      ImageView img_action_area_img= (ImageView) MenuItemCompat.getActionView(action_area_img);
      img_action_area_img.setImageResource(R.drawable.ic_arrow_white_down);
      tv_action_area.setTextColor(getResources().getColor(R.color.white));
      tv_action_area.setTextSize(15);
      tv_action_area.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 跳转到地区选择activity
			Intent intent=new Intent(MainActivity.this,AreaOptions.class);
			MainActivity.this.startActivity(intent);
			
		}
	});
      tv_action_area.setText(mDingweiText);
    
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	private void replaceFragment(Fragment frag) {
		fragTransaction=fragManager.beginTransaction();
	//	fragTransaction.replace(R.id.content_frame,frag);
		if(state==curState){//这种情况下用replace
			//肯定已经被add了，不做任何处理就好了
		}else{//正常处理
		fragTransaction.hide(fragments[curState]);//隐藏当前
		if(frag.isAdded()==false){
			fragTransaction.add(R.id.content_frame, frag);
		}else if(frag.isHidden()){
			fragTransaction.show(frag);
		}}
		fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
		curState=state;
		if(mDrawerLayout.isDrawerOpen(left_drawer)){
			mDrawerLayout.closeDrawers();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//实现从其他页按返回键进入首页
			switch (state) {
			
			case 1:
				exit();
				break;
			case 2:
				state=1;
				fragShouYe=FragmentShouYe.newInstance();
				replaceFragment(fragShouYe);
				break;
			case 3:
				state=1;
				fragShouYe=FragmentShouYe.newInstance();
				replaceFragment(fragShouYe);
				break;
			case 4:
				state=1;
				fragShouYe=FragmentShouYe.newInstance();
				replaceFragment(fragShouYe);
				break;
			case 5:
				state=1;
				fragShouYe=FragmentShouYe.newInstance();
				replaceFragment(fragShouYe);
				break;
			case 6:
				state=1;
				fragShouYe=FragmentShouYe.newInstance();
				replaceFragment(fragShouYe);
				break;
			case 0:
				state=1;
				fragShouYe=FragmentShouYe.newInstance();
				replaceFragment(fragShouYe);
				break;
			
			default:
				break;
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}

	}
private void toast(String str){
	Toast.makeText(getApplicationContext(), str, 1).show();
}

//启动应用的时候开始定位
private void initMap(){
	locClient=new LocationClient(getApplicationContext());
	 locOption=new LocationClientOption();
	 //设置参数
	 locOption.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
	 locOption.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
	 locOption.setIsNeedAddress(true);//返回的定位结果包含地址信息
	 locOption.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
	 //把需求参数传递给locClient核心操作类
	 
		locClient.setLocOption(locOption);
		//设置监听器，取得返回的BDLocation
		locClient.registerLocationListener(new MyLocationListener());
		locClient.start();
		locClient.requestLocation();
}

	
    
private class MyLocationListener implements BDLocationListener{

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if (location == null)
            return ;
	StringBuffer sb=new StringBuffer();
	if(location.getLocType()==63){
		Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
	}else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
		//返回区域district
		String district=location.getDistrict();
		//把actionview_area设置为district
		mDingweiText=district;
		tv_action_area.setText(mDingweiText);
		//根据district从数据库查询响应的信息。。。。
	
		
	} 
	}}
private void initLeft(){
	menunames=getResources().getStringArray(R.array.menuname);
	relative_getenzhongxin=(RelativeLayout) findViewById(R.id.relative_getenzhongxin);
	//�ҵ�fragleft�ĵ�0���ϵĿؼ�
	 btn_login= (Button) relative_getenzhongxin.findViewById(R.id.btn_login);
	nihao=(TextView) relative_getenzhongxin.findViewById(R.id.nihao);
	 img_userimg=(ImageView) relative_getenzhongxin.findViewById(R.id.img_userimg);
	linear_footer=(LinearLayout) findViewById(R.id.linear_footer);
	list_menu=(ListView) findViewById(R.id.list_menu);
	list_menu.setAdapter(new MenuDrawerAdapter(MainActivity.this,menunames ));
	//��½��ť����¼�
	btn_login.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			state=0;
			EnterFragment(state);
		}
	});
	linear_footer.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			state=6;
			EnterFragment(state)	;}	
	});
	list_menu.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			switch (position) {
			case 0:
				state=1;
				break;
			case 1:
				state=2;
				break;
			case 2:
				state=3;
				break;
			case 3:
				state=4;
				break;
			case 4:
				state=5;
				break;

			default:
				break;
			}
			
			EnterFragment(state)	;			
		}
	} );
}

//处理fragmentLeft点击之后的行为
private void EnterFragment(int state){
	switch (state) {
	case 5:
		replaceFragment(fragGuanYu);
		break;
	case 1:
		replaceFragment(fragShouYe);
		break;
	case 2:
		replaceFragment(fragXunZhaoJianZhi);
		break;
	case 3:
		replaceFragment(fragXunZhaoRenCai);
		break;
	case 4:
		replaceFragment(fragFBJZ);
		break;
	case 0:
		if(getApplicationContext().getSharedPreferences("user",0).getBoolean("isLogin", false)==false){
			mDrawerLayout.closeDrawers();
		Intent intent=new Intent(getApplicationContext(), Login.class);
		startActivity(intent);
		}else{
			replaceFragment(fragGRZX);
		};
	default:
		break;
	}
}
private void initFragMent(){
	 fragGuanYu=FragmentGuanYu.newInstance();
	 fragShouYe=FragmentShouYe.newInstance();
	 fragXunZhaoJianZhi=FragmentXunZhaoJianZhi.newInstance();
	 fragXunZhaoRenCai=FragmentXunZhaoRenCai.newInstance();
	 fragFBJZ=FragmentFaBuJianZhi.newInstance();
	 fragGRZX=FragmentGeRenZhongXin.newInstance();
	fragments=new Fragment[]{fragGRZX,fragShouYe,fragXunZhaoJianZhi,fragXunZhaoRenCai,fragFBJZ,fragGuanYu };
}
@Override//test
protected void onSaveInstanceState(Bundle outState) {
	// TODO Auto-generated method stub
	super.onSaveInstanceState(outState);
	outState.putString("nihao", "你好吗");
}


}
