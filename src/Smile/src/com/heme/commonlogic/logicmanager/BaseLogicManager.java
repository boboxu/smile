package com.heme.commonlogic.logicmanager;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.commonlogic.servermanager.IServerManagerListener;
import com.heme.foundation.error.BaseError;

public abstract class BaseLogicManager implements IServerManagerListener{
	private static final String TAG = "BaseLogicManager";
	private Map<String, IBaseLogicManagerListener> mListenerMap = new HashMap<String, IBaseLogicManagerListener>();
	private NetResponseHandler mResponseHandler = new NetResponseHandler();
	protected void saveListener (IBaseLogicManagerListener listener,String key) 
	{
		mListenerMap.put(key, listener);
	}
	
	protected void delListener(String key) 
	{
		mListenerMap.remove(key);
	}

	protected IBaseLogicManagerListener findListener(String key) {
		return mListenerMap.get(key);
	}
	
	@Override
	public void onRequestSuccess(BaseResponse response) {
		String key = response.getmRequest().getmRequestKey();
		IBaseLogicManagerListener listener = findListener(key);
		if (listener == null)
		{
			Log.i(TAG, "没有回掉");
			return;
		}
		
		if (mResponseHandler != null)
		{
			// msg里面的数据有可能会有性能问题，如果有这个问题的话，再来看解决方法
			Map<String, Object> objectMap = new HashMap<String, Object>();
			objectMap.put(NetResponseHandler.MSG_OBJECT_KEY_RESPONSE, response);
			objectMap.put(NetResponseHandler.MSG_OBJECT_KEY_LISTENERKEY, key);
			Message msg = mResponseHandler.obtainMessage(NetResponseHandler.MSG_CODE_REQUEST_SUCCESS, objectMap);
			mResponseHandler.sendMessage(msg);
		}
	}

	@Override
	public void onRequestFail(BaseResponse response) {
		String key = response.getmRequest().getmRequestKey();
		IBaseLogicManagerListener listener = findListener(key);
		if (listener == null)
		{
			Log.i(TAG, "没有回掉");
			return;
		}

		if (mResponseHandler != null)
		{
			// msg里面的数据有可能会有性能问题，如果有这个问题的话，再来看解决方法
			Map<String, Object> objectMap = new HashMap<String, Object>();
			objectMap.put(NetResponseHandler.MSG_OBJECT_KEY_RESPONSE, response);
			objectMap.put(NetResponseHandler.MSG_OBJECT_KEY_LISTENERKEY, key);
			Message msg = mResponseHandler.obtainMessage(NetResponseHandler.MSG_CODE_REQUEST_FAILED, objectMap);
			mResponseHandler.sendMessage(msg);
		}
	}
	
	/**
	 * 处理成功请求,子类必需实现
	 * 
	 * @param response
	 * @param delegate
	 */
	protected abstract void onSuccessResponse(BaseResponse response,
	        IBaseLogicManagerListener listener);

	/**
	 * 出错信息处理 如果要自定义处理,可实现覆盖此方法 如果返回结果不为空，则会回调delegate的onLogicManagerError方法
	 * 
	 * @param response
	 * @param delegate
	 * @return
	 */
	protected BaseError onFailedResponse(BaseResponse response,
			IBaseLogicManagerListener listener)
	{
		BaseError error = response.getmError();
		if (error == null)
        {
	        error = new BaseError();
        }
		return error;
	}

	
	@SuppressLint("HandlerLeak")
	private class NetResponseHandler extends Handler
	{
		public static final String MSG_OBJECT_KEY_RESPONSE = "MSG_OBJECT_KEY_RESPONSE";
		public static final String MSG_OBJECT_KEY_LISTENERKEY = "MSG_OBJECT_KEY_LISTENERKEY";
		public static final int MSG_CODE_REQUEST_SUCCESS = 0;
		public static final int MSG_CODE_REQUEST_FAILED = -1;
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg)
		{
			Map<String, Object> objectMap = (Map<String, Object>) msg.obj;
			BaseResponse response = (BaseResponse) objectMap
			        .get(MSG_OBJECT_KEY_RESPONSE);
			String key = (String) objectMap.get(MSG_OBJECT_KEY_LISTENERKEY);

			IBaseLogicManagerListener listener = (IBaseLogicManagerListener) findListener(key);

			if (response == null)
			{
				Log.e(TAG, "response is null");
				return;
			}

			switch (msg.what)
			{
			case MSG_CODE_REQUEST_SUCCESS:
				onSuccessResponse(response, listener);
				break;
			case MSG_CODE_REQUEST_FAILED:
				onFailedResponse(response, listener);
			}
			msg.obj = null;
			msg = null;
			delListener(key);
			response = null;
		}
	}
}
