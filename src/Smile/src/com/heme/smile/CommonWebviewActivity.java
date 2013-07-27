package com.heme.smile;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

//公共的webview 
public class CommonWebviewActivity extends BaseActivity {
	private static final String TAG = "CommonWebviewActivity";
	public static final String TITLE = "title";
	public static final String URL = "url";
	
	private String mTitle,mUrl;
	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mTitle = getIntent().getExtras().getString(TITLE);
		mUrl = getIntent().getExtras().getString(URL);
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.commonwebview);
		findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		((TextView)findViewById(R.id.titleTextView)).setText(mTitle);
		mWebView = (WebView)findViewById(R.id.webview);
		mWebView.setWillNotCacheDrawing(false);
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(mUrl);
	}
	 @Override
	 public boolean onKeyDown(int keyCoder,KeyEvent event){
		if (keyCoder == KeyEvent.KEYCODE_BACK) {
			if (mWebView.canGoBack()) {
				mWebView.goBack();
			} else {
				finish();
			}
			return true;
		}
		return false;
 }


}
