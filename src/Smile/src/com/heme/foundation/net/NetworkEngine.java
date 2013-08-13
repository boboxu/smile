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
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

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
	
	protected static final int MSG_RECV_BUF = 1;
	protected static final int MSG_NEED_HEART = 2;
	protected static final int MSG_CONNECT_TIMEOUT = 3;
	protected static final int MSG_CONNECT_FAILED = 4;

	protected static final String BUNDLE_RECV_BUF = "BUNDLE_RECV_BUF";
	
	
	protected IProtocolEngineDelegate mProtocolEngineDelegate = null;
	
//	protected NetworkHandler mHandler = new NetworkHandler();
	
    public void setmProtocolEngineDelegate(
			IProtocolEngineDelegate mProtocolEngineDelegate)
	{
		this.mProtocolEngineDelegate = mProtocolEngineDelegate;
	}

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
    public void cancelNetworkRequest(NetworkRequest request)
    {
    	// TODO Auto-generated method stub
    	
    }

	@Override
	public void sendProtocolBuffer(byte[] buffer)
	{
    	if (isConnected && mConnectionThread != null)
		{

			mConnectionThread.sendBuffer(buffer);
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
		if (recvBuffer != null)
		{
			Bundle bundle = new Bundle();
			bundle.putByteArray(BUNDLE_RECV_BUF, recvBuffer);
			
			Message msg = new Message();
			msg.what = MSG_RECV_BUF;
			msg.setData(bundle);
			mHandler.sendMessage(msg);
		}
	}
	
	@Override
	public void onNeedBeatHeart()
	{
		mHandler.sendEmptyMessage(MSG_NEED_HEART);
	}


	@Override
	public void onConnectTimeout()
	{
		mHandler.sendEmptyMessage(MSG_CONNECT_TIMEOUT);
	}

	@Override
	public void onConnectFailed()
	{
		mHandler.sendEmptyMessage(MSG_CONNECT_FAILED);
	}
	
	
	@SuppressLint("HandlerLeak")
	protected Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg) 
		{
			if (mProtocolEngineDelegate == null)
			{
				return;
			}
			switch (msg.what)
			{
			case MSG_NEED_HEART:
				mProtocolEngineDelegate.onNeedSendHeartBeat();	
				break;
			case MSG_RECV_BUF:
				if (msg.getData() != null)
				{
					Bundle bundle = msg.getData();
					if(bundle != null)
					{
						byte[] recBuf = bundle.getByteArray(BUNDLE_RECV_BUF);
						mProtocolEngineDelegate.onRecvProtocolBuffer(recBuf);
					}
				}
				break;
			case MSG_CONNECT_TIMEOUT:
				mProtocolEngineDelegate.onProtocolConnectTimeout();
				break;
			case MSG_CONNECT_FAILED:
				mProtocolEngineDelegate.onProtocolConnectFailed();
				break;
			default:
				break;
			}
		};
	};

}
