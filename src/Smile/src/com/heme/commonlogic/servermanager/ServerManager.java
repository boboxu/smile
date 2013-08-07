package com.heme.commonlogic.servermanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.error.ProtoError;
import com.heme.foundation.net.INetworkEngineListener;
import com.heme.foundation.net.IProtocolEngineDelegate;
import com.heme.foundation.net.NetworkEngine;
import com.heme.foundation.net.NetworkRequest;
import com.heme.foundation.net.NetworkResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.login.LoginResponse;
import com.heme.logic.module.Trans.TransProto;
import com.heme.utils.ByteUtil;

public class ServerManager implements IServerManagerInterface,
		INetworkEngineListener, IProtocolEngineDelegate {
	private static final String TAG = "ServerManager";
	private static ServerManager g_Instance = null;
	private Map<String, BaseRequest> mRequestMap;
	private final RequestIdGenerator mRequestIdGenerator;

	public static ServerManager shareInstance() {
		if (g_Instance == null) {
			g_Instance = new ServerManager();
		}
		return g_Instance;
	}

	protected ServerManager() {
		mRequestMap = new HashMap<String, BaseRequest>();
		mRequestIdGenerator = new RequestIdGenerator();

		NetworkEngine.getEngine().setmProtocolEngineDelegate(this);
	}

	@Override
	public int sendRequest(BaseRequest request) {
		if (request.getmDataBuffer() == null
				|| request.getmRequestListener() == null) {
			Log.e(TAG, "request构造失败");
			return -1;
		}
		// 构造连接，到net模块了

		NetworkEngine.getEngine().sendProtocolBuffer(request.getmDataBuffer());
		// //测试代码begin，直接调用回掉看看
		// String requestClassName = request.getClass().getName();
		// StringBuilder sbBuilder = new StringBuilder();
		// String responseClassName =
		// sbBuilder.append(requestClassName.substring(0,requestClassName.indexOf("Request"))).append("Response").toString();
		// BaseResponse response = null;
		// try
		// {
		// @SuppressWarnings("unchecked")
		// Class<BaseResponse> responseClass = (Class<BaseResponse>) Class
		// .forName(responseClassName);
		// try
		// {
		// response = responseClass.newInstance();
		// response.setmDataBuffer(null);
		// }
		// catch (IllegalAccessException e)
		// {
		// e.printStackTrace();
		// }
		// catch (InstantiationException e)
		// {
		// e.printStackTrace();
		// }
		// }
		// catch (ClassNotFoundException e)
		// {
		// e.printStackTrace();
		//
		// //找不到响应类
		// response = new BaseResponse();
		// response.setmRequest(request);
		//
		// ProtoError error = new ProtoError(ProtoError.ERRCODE_ERRRESPONSENAME,
		// "");
		// response.setmError(error);
		// }
		// response.setmRequest(request);
		// request.getmRequestListener().onRequestSuccess(response);
		// //测试代码end

		// 赋予ID
		request.setmRequestID(mRequestIdGenerator.generateRequestId());
		// 保存起来
		addRequest(request);

		return 0;
	}

	@Override
	public void cancelRequest(int requestId, IServerManagerListener listener) {
		if (requestId == BaseRequest.INVALID_REQUEST_ID) {
			return;
		}
		BaseRequest request = mRequestMap.get(String.valueOf(requestId));
		if (request != null && request.getmRequestListener().equals(listener)) {
			delRequest(requestId);
		}
	}

	@Override
	public int sendProtocolRequest(BasePbRequest pbRequest) {
		int seqId = mRequestIdGenerator.generateRequestId();
		pbRequest.setSeqId(seqId);
		pbRequest.setmRequestID(seqId);
		pbRequest.buildTransData();
		
		if (pbRequest.getmDataBuffer() == null
				|| pbRequest.getmRequestListener() == null) {
			Log.e(TAG, "request构造失败");
			return -1;
		}
		// 构造连接，到net模块了

		NetworkEngine.getEngine()
				.sendProtocolBuffer(pbRequest.getmDataBuffer());

		// pbRequest.setmRequestID(mRequestIdGenerator.generateRequestId());

		// 保存起来
		addRequest(pbRequest);

		return 0;
	}

	private void addRequest(BaseRequest request) {
		mRequestMap.put(String.valueOf(request.getmRequestID()), request);
	}

	private void delRequest(int requestid) {
		mRequestMap.remove(String.valueOf(requestid));
	}

	protected BaseRequest getRequestFromMap(NetworkRequest request) {
		Set<String> keys = this.mRequestMap.keySet();
		Iterator<String> keysIter = keys.iterator();
		while (keysIter.hasNext()) {
			String key = (String) keysIter.next();
			BaseRequest baseRequest = this.mRequestMap.get(key);
			if (baseRequest.getmRequest().equals(request)) {
				return baseRequest;
			}
		}
		return null;
	}

	private BaseRequest getRequestFromSeqId(int seqId) {
		return mRequestMap.get(String.valueOf(seqId));
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
		Log.d(TAG, "finish data" + (new String(data)));

		BaseRequest baseRequest = getRequestFromMap(null);
		if (baseRequest == null) {
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
			response.setmError(new ProtoError(
					ProtoError.ERRCODE_INVALID_PROTOBUFFER, e.getMessage()));
		}

		baseRequest.getmRequestListener().onRequestSuccess(response);

		delRequest(baseRequest.getmRequestID());

	}

	@Override
	public void onRequestFail(NetworkResponse netresponse, int errorCode) {
		BaseRequest baseRequest = getRequestFromMap(null);
		if (baseRequest == null) {
			// 有可能操作被取消
			return;
		}

		BaseResponse response = parseRequestToResponse(baseRequest, null);
		response.setmRet(BaseResponse.RET_ERROR);

		ProtoError error = new ProtoError(errorCode);
		response.setmError(error);

		baseRequest.getmRequestListener().onRequestFail(response);

		delRequest(baseRequest.getmRequestID());

	}

	// 根据
	private BaseResponse parseRequestToResponse(BaseRequest request,
			byte[] responseData) {
		BaseResponse response = null;
		StringBuilder sbBuilder = new StringBuilder();
		String requestClassName = request.getClass().getName();
		String responseClassName = sbBuilder
				.append(requestClassName.substring(0,
						requestClassName.indexOf("Request")))
				.append("Response").toString();

		try {
			@SuppressWarnings("unchecked")
			Class<BaseResponse> responseClass = (Class<BaseResponse>) Class
					.forName(responseClassName);
			try {
				response = responseClass.newInstance();
				response.setmRequest(request);
				if (responseData == null) {
					// 返回数据为空
					ProtoError error = new ProtoError(
							ProtoError.ERRCODE_NORESPONSE_CONTENT, "");
					response.setmError(error);
				} else
					response.setmDataBuffer(responseData);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

			// 找不到响应类
			response = new BaseResponse();
			response.setmRequest(request);

			ProtoError error = new ProtoError(
					ProtoError.ERRCODE_ERRRESPONSENAME, "");
			response.setmError(error);
			return response;
		}

		return response;
	}

	@Override
	public void onRecvProtocolBuffer(byte[] buffer) {
		int length = ByteUtil.byteArrayToInt(buffer, 0);
		if (length + 4 != buffer.length) {
			Log.e(TAG, "网络回包的长度，数据不正确");
			return;
		}

		// Length占四个字节，后面的都是数据
		byte[] _respData = new byte[length];
		for (int i = 0; i < _respData.length; i++) {
			_respData[i] = buffer[4 + i];
		}

		BaseRequest baseRequest = null;
		try {
			TransProto _tranProto;
			_tranProto = TransProto.parseFrom(_respData);
			int seqid = _tranProto.getUint32Seq();
			baseRequest = getRequestFromSeqId(seqid);
			if (baseRequest == null) {
				// 有可能操作被取消
				return;
			}
		} catch (InvalidProtocolBufferException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

		BaseResponse response = parseRequestToResponse(baseRequest, buffer);
		response.setmRequest(baseRequest);
		try {
			response.parseData();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setmRet(BaseResponse.RET_ERROR);
			response.setmError(new ProtoError(
					ProtoError.ERRCODE_INVALID_PROTOBUFFER, e.getMessage()));
		}

		baseRequest.getmRequestListener().onRequestSuccess(response);

		delRequest(baseRequest.getmRequestID());

	}

	@Override
	public void onNeedSendHeartBeat()
	{
		// TODO Auto-generated method stub
		
	}

}
