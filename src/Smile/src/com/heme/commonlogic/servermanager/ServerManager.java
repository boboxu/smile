package com.heme.commonlogic.servermanager;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.error.ProtoError;
import com.heme.foundation.net.INetworkEngineListener;
import com.heme.foundation.net.NetworkRequest;
import com.heme.foundation.net.NetworkResponse;

public class ServerManager implements IServerManagerInterface ,INetworkEngineListener{
	private static final String TAG = "ServerManager";
	private static ServerManager g_Instance = null;
	private Map<String, BaseRequest> mRequestMap;
	private final RequestIdGenerator mRequestIdGenerator;
	private final static String HOSTSTR = "202。96.170.123";
	private final static int PORT = 8080;
	public static ServerManager shareInstance() {
		if (g_Instance == null) {
			g_Instance = new ServerManager();
		}
		return g_Instance;
	}

	protected ServerManager() {
		mRequestMap = new HashMap<String, BaseRequest>();
		mRequestIdGenerator = new RequestIdGenerator();
	}

	@Override
	public int sendRequest(BaseRequest request) {
		if (request.getmDataBuffer() == null || request.getmRequestListener() == null) 
		{
			Log.e(TAG, "request构造失败");
			return -1;
		}
		//构造连接，到net模块了
		NetworkRequest networkRequest = new NetworkRequest(HOSTSTR, PORT, request.getmDataBuffer(), this);
//		//测试代码begin，直接调用回掉看看
//		String requestClassName = request.getClass().getName();
//		StringBuilder sbBuilder = new StringBuilder();
//		String responseClassName = sbBuilder.append(requestClassName.substring(0,requestClassName.indexOf("Request"))).append("Response").toString();
//		BaseResponse response = null;
//		try
//		{
//			@SuppressWarnings("unchecked")
//			Class<BaseResponse> responseClass = (Class<BaseResponse>) Class
//			        .forName(responseClassName);
//			try
//			{
//				response = responseClass.newInstance();
//				response.setmDataBuffer(null);
//			}
//			catch (IllegalAccessException e)
//			{
//				e.printStackTrace();
//			}
//			catch (InstantiationException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		catch (ClassNotFoundException e)
//		{
//			e.printStackTrace();
//
//			//找不到响应类
//			response = new BaseResponse();		
//			response.setmRequest(request);
//			
//			ProtoError error = new ProtoError(ProtoError.ERRCODE_ERRRESPONSENAME, "");
//			response.setmError(error);	
//		}
//		response.setmRequest(request); 
//		request.getmRequestListener().onRequestSuccess(response);
//		//测试代码end
		
		//赋予ID
		request.setmRequestID(mRequestIdGenerator.generateRequestId());
		request.setmRequest(networkRequest);
		//保存起来
		addRequest(request);
		return 0;
	}

	@Override
	public void cancelRequest(int requestId, IServerManagerListener listener) {
		if (requestId == BaseRequest.INVALID_REQUEST_ID) 
		{
			return;
		}
		BaseRequest request = mRequestMap.get(String.valueOf(requestId));
		if (request != null && request.getmRequestListener().equals(listener) ) 
		{
			delRequest(requestId);
		}
	}

	private void addRequest(BaseRequest request)
	{
		mRequestMap.put(String.valueOf(request.getmRequestID()), request);
	}
	
	private void delRequest(int requestid)
	{
		mRequestMap.remove(String.valueOf(requestid));
	}
	
	protected BaseRequest getRequestFromMap(NetworkRequest request)
	{
		Set<String> keys = this.mRequestMap.keySet();
		Iterator<String> keysIter = keys.iterator();
		while (keysIter.hasNext())
		{
			String key = (String) keysIter.next();
			BaseRequest baseRequest = this.mRequestMap.get(key);
			if (baseRequest.getmRequest().equals(request))
			{
				return baseRequest;
			}
		}
		return null;
	}
	
	private final class RequestIdGenerator {
		static final int MOD = 100000;
		protected int mIdCount;
		/**
	 	 * 
	 	 */
		public RequestIdGenerator() {
			super();
			this.mIdCount = 1;
		}

		public int generateRequestId() {
			int n = (++mIdCount) % MOD;
			return n;
		}

	}

	@Override
	public void onRequestSuccess(NetworkResponse netresponse, byte[] data) {
		Log.d(TAG,"finish data"+(new String(data)));

		BaseRequest baseRequest = getRequestFromMap(netresponse.getmRequest());
		if (baseRequest == null)
		{
			// 有可能操作被取消
			return;
		}

		BaseResponse response = parseRequestToResponse(baseRequest, data);
		response.setmRequest(baseRequest);
		try {
			response.parseData();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setmRet(BaseResponse.RET_ERROR);
			response.setmError(new ProtoError(ProtoError.ERRCODE_INVALID_PROTOBUFFER,e.getMessage()));
		}

		if (response.getmRet() == BaseResponse.RET_SUCCESS)
		{
			baseRequest.getmRequestListener().onRequestSuccess(response);
		}
		else
		{
			baseRequest.getmRequestListener().onRequestFail(response);
		}

		delRequest(baseRequest.getmRequestID());
		
	}

	@Override
	public void onRequestFail(NetworkResponse netresponse, int errorCode) {
		BaseRequest baseRequest = getRequestFromMap(netresponse.getmRequest());
		if (baseRequest == null)
		{
			// 有可能操作被取消
			return;
		}

		BaseResponse response = new BaseResponse();
		response.setmRequest(baseRequest);
		response.setmRet(BaseResponse.RET_ERROR);
		
		ProtoError error = new ProtoError(errorCode);
		response.setmError(error);
		
		baseRequest.getmRequestListener().onRequestFail(response);

		delRequest(baseRequest.getmRequestID());
		
	}
	
	//根据
	private BaseResponse parseRequestToResponse(BaseRequest request,
	        byte[] responseData)
	{
		BaseResponse response = null;
		
		if (responseData == null)
		{
			//返回数据为空
			response = new BaseResponse();
			response.setmRequest(request);

			ProtoError error = new ProtoError(
					ProtoError.ERRCODE_NORESPONSE_CONTENT, "");
			response.setmError(error);
			
			return response;
		}

		StringBuilder sbBuilder = new StringBuilder();
		String requestClassName = request.getClass().getName();
		String responseClassName = sbBuilder.append(requestClassName.substring(0,requestClassName.indexOf("Request"))).append("Response").toString();
		
		try
		{
			@SuppressWarnings("unchecked")
			Class<BaseResponse> responseClass = (Class<BaseResponse>) Class
			        .forName(responseClassName);
			try
			{
				response = responseClass.newInstance();
				response.setmDataBuffer(responseData);
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();

			//找不到响应类
			response = new BaseResponse();		
			response.setmRequest(request);
			
			ProtoError error = new ProtoError(ProtoError.ERRCODE_ERRRESPONSENAME, "");
			response.setmError(error);	
			return response;
		}
			
		return response;
	}

}
