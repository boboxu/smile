package com.heme.smile.protocol.runner;

import java.util.List;
import java.util.Map;

import com.heme.smile.protocol.tools.IDataLoaderParser;
import com.heme.smile.protocol.tools.UrlLoader;
import com.heme.smile.protocol.tools.UrlLoader.IOnUrlLoaderCompleteListener;
import com.heme.smile.protocol.tools.UrlLoader.IOnUrlLoaderErrorListener;

import android.util.Log;

public class DataLoader extends Future {
	public interface IOnDataLoaderCompleteListener {
		public void onComplete(int errCode, Object obj, Map<String, List<String>> headers, DataLoader loader);
	}
	private static final String TAG = "BpDataLoader";
	private IOnDataLoaderCompleteListener mOnDataLoaderCompleteListener;
	private UrlLoader mUrlLoader;
	protected IDataLoaderParser mParser;
	protected int mError = 0;
	private String mHttpMethod;
	private String mContentIfPost;

	public DataLoader(String url, IDataLoaderParser parser) {
		mParser = parser;
		mUrlLoader = new UrlLoader(url);
		mHttpMethod = "GET";
		mContentIfPost = null;
		
		mUrlLoader.setBpOnUrlLoaderCompleteListener(new IOnUrlLoaderCompleteListener() {
			
			public void onComplete(int respCode, Map<String, List<String>> headers,
					byte[] content, UrlLoader loader)	{
				Object obj = null;
				if(respCode == 200) {
					String str = new String(content);
					Log.i(TAG, str);
					obj = mParser.parse(content);
					mError = mParser.getError();
				} else {
					mError = respCode;
				}

				if(null != mOnDataLoaderCompleteListener) {
					mOnDataLoaderCompleteListener.onComplete(mError, obj, headers, DataLoader.this);
				}
			}
		});
		
		mUrlLoader.setBpOnUrlLoaderErrorListener(new IOnUrlLoaderErrorListener() {
			
			public void onError(int errCode, UrlLoader loader) {
				mError = errCode;
				if(null != mOnDataLoaderCompleteListener){
					mOnDataLoaderCompleteListener.onComplete(mError, null, null, DataLoader.this);
				}
			}
		});
		
		mUrlLoader.setHttpMethod(mHttpMethod, mContentIfPost);
	}
	
	public void setHttpMethod(String httpMethod, String contentIfPost) {
		if(null != httpMethod) {
			mHttpMethod = httpMethod;
		}
		mContentIfPost = contentIfPost;
		mUrlLoader.setHttpMethod(mHttpMethod, mContentIfPost);
	}
	@Override
	public void run() {
		Log.i(TAG, "run------------");
		if(null != mUrlLoader){
			mUrlLoader.run();
		}
	}

	@Override
	public void cancel() {
		if(null != mUrlLoader){
			mUrlLoader.cancel();
			super.cancel();
		}
	}
	
	public void setOnDataLoaderCompleteListener(IOnDataLoaderCompleteListener listener) {
		if(null != listener){
			mOnDataLoaderCompleteListener = listener;
		}
	}
}
