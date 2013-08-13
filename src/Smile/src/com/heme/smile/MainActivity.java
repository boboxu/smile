package com.heme.smile;

import com.heme.smile.R;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

public class MainActivity extends TabActivity {
	private static final String TAG = "MainActivity";
	private TabHost mTabHost;
	private LocalActivityManager mActivityManager;
	private TabWidget mTabWidget;
	private long mExitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
		initTabHost();
	}
	public void backToDesk(){
		Intent intent= new Intent(Intent.ACTION_MAIN);
		 intent.addCategory(Intent.CATEGORY_HOME);
		 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 startActivity(intent);
		 System.exit(0);
	}
	private void addTab(int iconResId,String tag,Intent intent){
		RelativeLayout indicator = (RelativeLayout)LayoutInflater.from(this).inflate(R.layout.text_tab_indicator, null);
		ImageView icon = (ImageView)indicator.findViewById(R.id.tab_icon);
		icon.setImageResource(iconResId);
		TabSpec tSpec = mTabHost.newTabSpec(tag).setIndicator(indicator).setContent(intent);
		mTabHost.addTab(tSpec);
	}
	private void updateTagImg(){
		for (int i = 0; i < mTabWidget.getTabCount(); i++) {
			View v = mTabWidget.getChildTabViewAt(i);
			ImageView selectBgImageView = (ImageView)v.findViewById(R.id.select_bg);
			selectBgImageView.setVisibility(View.GONE);
			ImageView icon = (ImageView)v.findViewById(R.id.tab_icon);
			if (i==0) {
				icon.setImageResource(R.drawable.toolbar_microschool_icon);
			}else if (i==1) {
				icon.setImageResource(R.drawable.toolbar_message_icon);
			}else if (i==2) {
				icon.setImageResource(R.drawable.toolbar_addressbook_icon);
			}else if (i==3) {
				icon.setImageResource(R.drawable.toolbar_more_icon);
			}
		}
		int resId = R.drawable.navigationbar_home_highlighted;
		int currentTab = mTabHost.getCurrentTab();
		if (currentTab==0) {
			resId = R.drawable.navigationbar_home_highlighted;
		}else if (currentTab==1) {
			resId = R.drawable.tabbar_message_center_highlighted;
		}else if (currentTab==2) {
			resId = R.drawable.tabbar_profile_highlighted;
		}else if (currentTab==3) {
			resId = R.drawable.tabbar_more_highlighted;
		}
		mTabHost.getCurrentTabView().findViewById(R.id.select_bg).setVisibility(View.VISIBLE);
		((ImageView)mTabHost.getCurrentTabView().findViewById(R.id.tab_icon)).setImageResource(resId);
	}
	private void initTabHost(){
		mTabHost = this.getTabHost();
		mActivityManager = new LocalActivityManager(this, false);
		mTabHost.setup();
		mTabWidget = (TabWidget)findViewById(android.R.id.tabs);
		
		Intent microSchoolIntent = new Intent(MainActivity.this, MyMicroSchoolActivity.class);
		Intent messageIntent = new Intent(MainActivity.this, MessageActivity.class);
		Intent addressBookIntent = new Intent(MainActivity.this, AddressBookActivity.class);
		Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
		
		addTab(R.drawable.toolbar_microschool_icon, "microschool", microSchoolIntent);
		addTab(R.drawable.toolbar_message_icon, "message", messageIntent);
		addTab(R.drawable.toolbar_addressbook_icon, "addressbook", addressBookIntent);
		addTab(R.drawable.toolbar_more_icon, "setting", settingIntent);
		
		mTabHost.setCurrentTab(0);
		updateTagImg();
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				updateTagImg();
			}
		});
	}
	private void initUI(){
		setContentView(R.layout.main);
	}
}
