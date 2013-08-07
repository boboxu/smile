/*
 * NetworkEngine.java
 * TCMobileAnalyticsLibrary
 * 
 * Description: 
 * 
 * Created by ottozheng on 2013-4-11.  
 * Copyright (c) 2012 Tencent. All rights reserved.
 *
 * Change Logs:
 * 1.2013-4-11	ottozheng		create this file
 *
 */
package com.heme.foundation.net;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.heme.foundation.net.NetworkService.ConnectionThread;
import com.heme.foundation.net.NetworkService.INetworkServiceListener;
import com.ning.http.client.ListenableFuture;

/**
 * 简单异步网络请求管理
 * @author ottozheng
 *
 */

public class NetworkEngine implements IHttpEngineInterface, IProtocolEngineInterface, INetworkServiceListener
{
	private static final String TAG = "NetworkEngine";
	
	protected static NetworkEngine gNetworkEngine = null;
			
	protected ConnectionThread mConnectionThread = null;
	protected Boolean isConnected = false;
	
//	protected NetworkHandler mHandler = new NetworkHandler();
	
    public static NetworkEngine getEngine()
    {
	    if (gNetworkEngine == null)
        {
	        gNetworkEngine = new NetworkEngine();
        }
	    return gNetworkEngine;
    }

    /**
     * 
     */
    public NetworkEngine()
    {
    	
    }
       
    public boolean sendHttpRequest(NetworkRequest request)
    {
    	synchronized (this)
        {
        	try
        	{
//        		NetworkRequest request = new NetworkRequest("http://202.96.170.123:28080", "POST", null, null);
//        		request.setFileData("/sdcard/yca.txt", "yac");
        		ListenableFuture<NetworkResponse> returnVal = null;
        		request.mCallerLooper = Looper.myLooper();
        		returnVal = request.makeURLConnectionConnection(null).prepareRequest(request.mRequest).execute(request.mHandler);
        		if (returnVal == null)
        		{
        			request.mHandler.onTimeOut(request);
        		}
        	}
        	catch (IOException e)
        	{
        		request.mHandler.onTimeOut(request);
        		e.printStackTrace();
        		return false;

        	}
        	catch (Exception e) {
        		request.mHandler.onThrowable(e);
        		e.printStackTrace();
        		
        		return false;
        	}
        }
    	return true;
    }

	@Override
	public void sendProtocolBuffer(byte[] buffer)
	{
    	if (isConnected && mConnectionThread != null)
		{
			try
			{
				mConnectionThread.sendBuffer(buffer);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void onConnceted(ConnectionThread thread)
	{
		mConnectionThread = thread;
		isConnected = true;
	}

	@Override
	public void onRecvData(byte[] recvBuffer)
	{
		Log.d(TAG, recvBuffer.toString());
	}
	
    
    @SuppressLint("HandlerLeak")
	public class NetworkHandler extends Handler
    {
    	public static final String BUNDLE_KEY_DATA = "DATA";
    	
    	/**
         * 
         */
        public NetworkHandler()
        {
        	super();
        }
    	
    	/**
		 * @param looper
		 */
        public NetworkHandler(Looper looper)
        {
	        super(looper);
        }

		/* (non-Javadoc)
    	 * @see android.os.Handler#handleMessage(android.os.Message)
    	 */
    	@Override
    	public void handleMessage(Message msg)
    	{  	    
    	}
    }

}
