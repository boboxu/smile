package com.heme.smile;

import java.io.InputStream;

import com.heme.smile.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class IntroduceActivity extends BaseActivity {
	private TextView mRuleTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.userrule);
		((TextView)findViewById(R.id.titleTextView)).setText("功能介绍");
		mRuleTextView = (TextView)findViewById(R.id.rule_text);
		try {
			InputStream is = this.getAssets().open("introduce.txt");
			String ruleContent = Util.inputStream2String(is);
			is.close();
			mRuleTextView.setText(ruleContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
