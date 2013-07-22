package com.heme.smile.protocol.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.heme.smile.protocol.runner.Future;

import android.util.Log;

public class UrlLoader extends Future
{
	public interface IOnUrlLoaderErrorListener {
		public void onError(int errCode, UrlLoader loader);
	}
	public interface IOnUrlLoaderCompleteListener	{
		public void onComplete(int respCode, Map<String, List<String>> headers, 
						byte[] content, UrlLoader loader);
	}
	
	private static final String TAG = "BpUrlLoader";
	private static final int DEFAULT_CONNECT_TIME_OUT = 6000;
	private static final int DEFAULT_READ_TIME_OUT = 12000;
	
	private IOnUrlLoaderErrorListener mOnErrorListener;
	private IOnUrlLoaderCompleteListener mOnCompleteListener;
	
	private HttpURLConnection mHttpConn;
	private String mUrl;
	private Map<String, String> mHttpHeaders;
	private String mHttpMethod;
	private String mPostContent;
	private int mConnectionTimeOut;
	private int mReadTimeOut;
	
	private boolean mCancel = false;
	
	private Thread mThread = null;
	
	public UrlLoader(String url) {
		mUrl = url;
		mHttpMethod = "GET";
		mConnectionTimeOut = DEFAULT_CONNECT_TIME_OUT;
		mReadTimeOut = DEFAULT_READ_TIME_OUT;
		mCancel = false;
		mHttpHeaders = new HashMap<String, String>();
	}
	
	@Override
	public void run()
	{
		Log.i(TAG, "run--------------");
		try	{
			mThread = Thread.currentThread();
			sailing();
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void cancel() {
		super.cancel();
		if(null != mThread && !mThread.isInterrupted())	{
			mThread.interrupt();
			mThread = null;
		}
	}
	
	public void setBpOnUrlLoaderErrorListener(IOnUrlLoaderErrorListener listener)	{
		if(null != listener)
		{
			mOnErrorListener = listener;
		}
	}
	
	public void setBpOnUrlLoaderCompleteListener(IOnUrlLoaderCompleteListener listener) {
		if(null != listener)
		{
			mOnCompleteListener = listener;
		}
	}
	
	public void setHttpMethod(String method, String contentIfPost) {
		mHttpMethod = method;
		mPostContent = contentIfPost;
	}
	
	public void setTimeOut(int connectionTime, int readTime) {
		if(connectionTime > 0) {
			mConnectionTimeOut = connectionTime;
		}
		if(readTime > 0) {
			mReadTimeOut = readTime;
		}
	}
	
	private void sailing() throws InterruptedException {
		if(mCancel) {
			return;
		}
		if(!openConnection()) {
			if(null != mOnErrorListener && !mCancel) {
				mOnErrorListener.onError(-1, this);
			}
			return;
		}
		if(mHttpMethod.equals("POST")) {
			OutputStream os = null;
			try {
				os = mHttpConn.getOutputStream();
				os.write(mPostContent.getBytes());
				os.flush();
				os.close();
			} catch (IOException e)	{
				closeConnection(null, os);
				if(null != mOnErrorListener && !mCancel) {
					mOnErrorListener.onError(-1, this);
				}
				return;
			}
		}
		
		Map<String, List<String>> headers = null;
		int respCode = -1;
		InputStream is = null;
		byte[] content = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		
		try	{
			respCode = mHttpConn.getResponseCode();
			headers = mHttpConn.getHeaderFields();
			is = mHttpConn.getInputStream();
			
			int readedBytes = 0;
			byte[] b = new byte[16384];
			while(!Thread.interrupted() && -1 != (readedBytes = is.read(b))) {
				bos.write(b, 0, readedBytes);
				Thread.sleep(200);
			}
			content = bos.toByteArray();
			bos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			closeConnection(is, bos);
			if(null != mOnErrorListener && !mCancel) {
				mOnErrorListener.onError(-1, this);
			}
			return;
		}
		
		closeConnection(is, null);
		if(null != mOnCompleteListener && !mCancel)	{
			mOnCompleteListener.onComplete(respCode, headers, content, this);
		}
	}
	
	private boolean openConnection() {
		try
		{
			Log.i(TAG, mUrl);
			URL url = new URL(mUrl);
			mHttpConn = (HttpURLConnection) url.openConnection();
			mHttpConn.setDoInput(true);
			mHttpConn.setUseCaches(false);
			Set<String> keys = mHttpHeaders.keySet();
			for(String key : keys)
			{
				mHttpConn.setRequestProperty(key, mHttpHeaders.get(key));
			}
			mHttpConn.setRequestProperty("Referer", "http://xlpan.com");
			mHttpConn.setConnectTimeout(mConnectionTimeOut);
			mHttpConn.setReadTimeout(mReadTimeOut);
			mHttpConn.setRequestMethod(mHttpMethod);
			if(mHttpMethod.equals("POST")) {
				mHttpConn.setDoOutput(true);
			}
			
		} catch (MalformedURLException e) {
			closeHttpConnection();
			return false;
		} catch (IOException e)	{
			closeHttpConnection();
			return false;
		}
		
		return true;
	}
	
	private void closeHttpConnection() {
		if(null != mHttpConn) {
			mHttpConn.disconnect();
			mHttpConn = null;
		}
	}
	
	private void closeConnection(InputStream is, OutputStream os) {
		if(null != is){
			try	{
				is.close();
			} catch (IOException e){
				
			}
		}
		if(null != os){
			try	{
				os.close();
			} catch (IOException e){
				
			}
		}
	}
}
