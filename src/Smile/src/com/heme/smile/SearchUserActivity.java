package com.heme.smile;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.heme.logic.common.Constans;
import com.heme.utils.Util;

public class SearchUserActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "SearchUserActivity";
	private ListView mListview;
	private EditText mKeyEditText;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.SEARCH_USER_SUCCESS:
				dismissDialog();
				//先模拟
				Util.showToast(SearchUserActivity.this, "无搜索结果");
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.searchuser);
		mListview = (ListView)findViewById(R.id.listview);
		((TextView)findViewById(R.id.titleTextView)).setText("添加好友");
		findViewById(R.id.backImg).setOnClickListener(this);
		mKeyEditText = (EditText)findViewById(R.id.keyword);
		mKeyEditText.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId==EditorInfo.IME_ACTION_SEARCH) {
					mHandler.removeMessages(Constans.SEARCH_USER_SUCCESS);
					doSearch();
					return true;
				}
				return false;
			}
		});
	}
	private void doSearch(){
		String kw = mKeyEditText.getText().toString().trim();
		if (kw.equals("")) {
			Util.showToast(this, "请输入搜索关键字");
			return;
		}
		showWaitDialog("搜索中,请稍候...");
		mHandler.sendEmptyMessageDelayed(Constans.SEARCH_USER_SUCCESS, 2000);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;

		default:
			break;
		}
	}
}
