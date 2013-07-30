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

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * 简单异步网络请求管理
 * @author ottozheng
 *
 */

public class NetworkEngine implements INetworkEngineInterface
{
	private static final String TAG = "NetworkEngine";
	protected static final int DEFAULT_MAX_RUNNING_NUM = 10;
	
	private List<NetworkRequest> mWaitingList = new ArrayList<NetworkRequest>();
	private List<NetworkRequest> mRunningList = new ArrayList<NetworkRequest>();

	protected static NetworkEngine gNetworkEngine = null;
	
	protected int mMaxRunningNum = DEFAULT_MAX_RUNNING_NUM;
	
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
    NetworkEngine()
    {
    	
    }
    
    protected NetworkHandler getNetworkHandler()
    {
    	NetworkHandler handler = null;
    	Looper looper = Looper.myLooper();
    	if(looper == null)
    	{
    		looper = Looper.getMainLooper();
    		Log.w(TAG, "the looper is null, use mainLoop");
    	}
    	
    	handler = new NetworkHandler(looper);
    	return handler;
    }

	/* (non-Javadoc)
	 * @see com.tencent.ibg.mobileanalytics.library.foundation.network.INetworkEngineInterface#sendNetworkRequest(com.tencent.ibg.mobileanalytics.library.foundation.network.NetworkRequest)
	 */
    @Override
    public boolean sendNetworkRequest(NetworkRequest request)
    {
    	if (mRunningList.size() < mMaxRunningNum)
        {
	        pushRunningTask(request);
        }
    	else 
    	{
			pushWaitingTask(request);
		}

	    return false;
    }

	/**
	 * @return the mMaxRunningNum
	 */
	public int getmMaxRunningNum()
	{
		return mMaxRunningNum;
	}

	/**
	 * @param mMaxRunningNum the mMaxRunningNum to set
	 */
	public void setmMaxRunningNum(int mMaxRunningNum)
	{
		this.mMaxRunningNum = mMaxRunningNum;
	}

	/* (non-Javadoc)
	 * @see com.tencent.ibg.mobileanalytics.library.foundation.network.INetworkEngineInterface#cancelNetworkRequest(com.tencent.ibg.mobileanalytics.library.foundation.network.NetworkRequest)
	 */
    @Override
    public void cancelNetworkRequest(NetworkRequest request)
    {
	    // TODO Auto-generated method stub
	    
    }
    
    protected void pushRunningTask(NetworkRequest request)
    {
    	synchronized (this)
        {
        	mRunningList.add(request);
    	    
        	NetworkTask task = new NetworkTask(request, getNetworkHandler());
        	task.start();
        }
    }
    
    protected NetworkRequest popRunningTask(int requestId)
    {
	    synchronized (this)
        {
	        for (int i = 0; i < mRunningList.size(); i++)
            {
	        	NetworkRequest request = mRunningList.get(i);
	            if (request != null && request.getmId() == requestId)
                {
	            	mRunningList.remove(i);
	                return request;
                }
            }
	        return null;
        }
    }
    
    protected void pushWaitingTask(NetworkRequest request)
    {
    	synchronized (request)
        {
        	mWaitingList.add(request);
        }
    }
    
    protected void moveWatingTaskToRunning()
    {
    	synchronized (this)
        {
        	int waitingSize = mWaitingList.size();
        	int runningSize = mRunningList.size();
        	if (waitingSize > 0 && runningSize < mMaxRunningNum)
            {
    	        NetworkRequest request = mWaitingList.get(0);
    	        if (request != null)
                {
    	            mWaitingList.remove(request);
    	            pushRunningTask(request);
                }
            }
        }
    }
    
    @SuppressLint("HandlerLeak")
	public class NetworkHandler extends Handler
    {
    	public static final String BUNDLE_KEY_REQUEST_ID = "REQUEST_ID";
    	public static final String BUNDLE_KEY_SUCCESS = "SUCCESS";
    	public static final String BUNDLE_KEY_STATUS_CODE = "STATUS_CODE";
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
    	    Bundle bundle = msg.getData();

    	    int requestId = bundle.getInt(BUNDLE_KEY_REQUEST_ID);
    	    NetworkRequest request = popRunningTask(requestId);   
    	    moveWatingTaskToRunning();
    	    
    	    if (request != null && request.mDelegate != null)
            {
        	    int statusCode = bundle.getInt(BUNDLE_KEY_STATUS_CODE);
        	    boolean isSuccess = bundle.getBoolean(BUNDLE_KEY_SUCCESS);
        	    
	            NetworkResponse response = new NetworkResponse(request, statusCode);
        	    
	            if (isSuccess)
                {
//		            String responseString = bundle.getString(BUNDLE_KEY_DATA);
	            	byte[] dataArray = bundle.getByteArray(BUNDLE_KEY_DATA);
		            response.setmDataBytes(dataArray);
		            request.mDelegate.onRequestSuccess(response, dataArray);
                }
	            else 
	            {
	            	request.mDelegate.onRequestFail(response, -1);
				}
            }
    	}
    }

}
