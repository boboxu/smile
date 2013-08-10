package com.heme.foundation.net;


public interface INetworkManagerDelegate
{
	/**
	 * 请求成功回调
	 * @param response
	 * @param data
	 */
	public void onRequestSuccess(NetworkRequest request, byte[] buffer);
	
	/**
	 * 请求失败回调
	 * @param response
	 */
	public void onRequestFail(NetworkRequest request, int errorCode);
	
}
