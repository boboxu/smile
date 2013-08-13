package com.heme.smile;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
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
	private ProgressDialog mDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mTitle = getIntent().getExtras().getString(TITLE);
		mUrl = getIntent().getExtras().getString(URL);
		createDialog();
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
			@Override
			public void onPageFinished(WebView view, String url) {
				mDialog.dismiss();
			}
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				mDialog.dismiss();
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				mDialog.show();
				super.onPageStarted(view, url, favicon);
			}
		});
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setPluginsEnabled(true);   
		mWebView.loadUrl(mUrl);
	}
	protected Dialog createDialog() {
		mDialog = new ProgressDialog(this);
		mDialog.setIndeterminate(true);
		mDialog.setMessage("加载中,请稍候...");
		mDialog.setCancelable(true);
		return mDialog;
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
