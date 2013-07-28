package com.heme.commonlogic.logicmanager;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.protobuf.GeneratedMessage;
import com.heme.commonlogic.servermanager.BaseRequest;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.commonlogic.servermanager.IServerManagerListener;
import com.heme.commonlogic.servermanager.ServerManager;
import com.heme.foundation.error.BaseError;

public abstract class BaseLogicManager implements IServerManagerListener {
	private static final String TAG = "BaseLogicManager";
	private Map<String, Handler> mListenerMap = new HashMap<String, Handler>();
	private NetResponseHandler mResponseHandler = new NetResponseHandler();

	protected void saveListener(Handler handler, String key) {
		mListenerMap.put(key, handler);
	}

	protected void delListener(String key) {
		mListenerMap.remove(key);
	}

	protected Handler findHandler(String key) {
		return mListenerMap.get(key);
	}

	@Override
	public void onRequestSuccess(BaseResponse response) {
		String key = response.getmRequest().getmRequestKey();
		Handler handler = findHandler(key);
		if (handler == null) 
		{
			Log.i(TAG, "没有回掉");
			return;
		}

		onSuccessResponse(response, handler);

		delListener(key);
		response = null;
	}

	@Override
	public void onRequestFail(BaseResponse response) {
		String key = response.getmRequest().getmRequestKey();
		Handler handler = findHandler(key);
		if (handler == null) 
		{
			Log.i(TAG, "没有回掉");
			return;
		}

		onFailedResponse(response, handler);

		delListener(key);
		response = null;
	}

	/**
	 * 处理成功请求,子类必需实现
	 * 
	 * @param response
	 * @param listener
	 */
	protected abstract void onSuccessResponse(BaseResponse response,
			Handler handler);

	/**
	 * 出错信息处理 如果要自定义处理,可实现覆盖此方法 如果返回结果不为空，则会回调listener的onLogicManagerError方法
	 * 
	 * @param response
	 * @param listener
	 * @return
	 */
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		BaseError error = response.getmError();
		if (error == null) {
			error = new BaseError();
		}
		return error;
	}

	protected String createListenerKeyByFunName(String funname) {
		return this.getClass().getName() + funname;
	}

	protected String createListenerKey() {
		return this.createListenerKeyByFunName("");
	}

	protected void sendRequest(final BaseRequest request, Handler handler,
			String key, String methodName) {
		if (key == null || key.length() == 0) {
			Log.i(TAG, "key 都是空的，干鸟啊");
			return;
		}
		saveListener(handler, key);
		request.setmRequestKey(key);
		request.setmRequestListener(this);
		new Thread(TAG) {
			@Override
			public void run() {
				ServerManager.shareInstance().sendRequest(request);
			}
		}.start();
	}

	@SuppressLint("HandlerLeak")
	private class NetResponseHandler extends Handler {
		public static final String MSG_OBJECT_KEY_RESPONSE = "MSG_OBJECT_KEY_RESPONSE";
		public static final String MSG_OBJECT_KEY_LISTENERKEY = "MSG_OBJECT_KEY_LISTENERKEY";
		public static final int MSG_CODE_REQUEST_SUCCESS = 0;
		public static final int MSG_CODE_REQUEST_FAILED = -1;

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			Map<String, Object> objectMap = (Map<String, Object>) msg.obj;
			BaseResponse response = (BaseResponse) objectMap
					.get(MSG_OBJECT_KEY_RESPONSE);
			String key = (String) objectMap.get(MSG_OBJECT_KEY_LISTENERKEY);

			Handler listener = findHandler(key);

			if (response == null) {
				Log.e(TAG, "response is null");
				return;
			}

			switch (msg.what) {
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

	protected String _FUNC_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		return traceElement.getMethodName();
	}
	
	protected void handleresponse(int msgWhat, Object msgObj,
			Handler handler) {
		if (null != handler) 
		{
			Message msgMessage = handler.obtainMessage();
			msgMessage.what = msgWhat;
			msgMessage.obj = msgObj;
			handler.sendMessage(msgMessage);
		}
	}
}
