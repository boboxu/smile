package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.utils.Util;

public class AboutActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "AboutActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.about);
		((ImageView)findViewById(R.id.backImg)).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("关于傻逼");
		findViewById(R.id.updateapp).setOnClickListener(this);
		findViewById(R.id.introduce).setOnClickListener(this);
		findViewById(R.id.userrule).setOnClickListener(this);
		findViewById(R.id.help).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;
		case R.id.updateapp:
			Util.showToast(this, "当前已是最新版本");
			break;
		case R.id.introduce:
			startActivity(new Intent(this, IntroduceActivity.class));
			break;
		case R.id.userrule:
			startActivity(new Intent(this, UserRuleActivity.class));
			break;
		case R.id.help:
			startActivity(new Intent(this, HelpActivity.class));
			break;
		default:
			break;
		}
	}
}
