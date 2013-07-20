package com.heme.foundation.net;

import java.nio.ByteBuffer;

public interface INetworkManagerListener 
{
	/**
	 * 网络请求完成
	 * @param request
	 * @param buffer
	 */
	public void onRequestSuccess(NetworkRequest request, ByteBuffer buffer);
	
	/**
	 * 网络请求失败
	 * @param request
	 * @param errorCode
	 */
	public void onRequestFail(NetworkRequest request, int errorCode);
	
}
