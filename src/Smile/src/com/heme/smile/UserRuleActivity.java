package com.heme.smile;

import java.io.InputStream;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.heme.utils.Util;

public class UserRuleActivity extends BaseActivity {
	private TextView mRuleTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.userrule);
findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		((TextView)findViewById(R.id.titleTextView)).setText("使用条款");
		mRuleTextView = (TextView)findViewById(R.id.rule_text);
		try {
			InputStream is = this.getAssets().open("user_rule.txt");
			String ruleContent = Util.inputStream2String(is);
			is.close();
			mRuleTextView.setText(ruleContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
