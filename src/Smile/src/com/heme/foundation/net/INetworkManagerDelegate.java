package com.heme.foundation.net;

import java.nio.ByteBuffer;

public interface INetworkManagerDelegate
{
	/**
	 * 请求成功回调
	 * @param response
	 * @param data
	 */
	public void onRequestSuccess(NetworkRequest request, ByteBuffer buffer);
	
	/**
	 * 请求失败回调
	 * @param response
	 */
	public void onRequestFail(NetworkRequest request, int errorCode);
}
