package com.heme.foundation.net;

public interface INetworkManagerInterface 
{
	/**
	 * 发送网络请求
	 */
	public Boolean sentNetworkRequest(NetworkRequest request);
	
	/**
	 * 取消网络请求
	 */
	public void cancelNetworkRequest(NetworkRequest request);
}
