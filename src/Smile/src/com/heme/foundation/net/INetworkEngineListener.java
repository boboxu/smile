
package com.heme.foundation.net;


/**
 * @author ottozheng
 *
 */
public interface INetworkEngineListener
{

	/**
	 * 请求成功回调
	 * @param response
	 * @param data
	 */
	public void onRequestSuccess(NetworkResponse response, byte[] dataArray);
	
	/**
	 * 请求失败回调
	 * @param response
	 */
	public void onRequestFail(NetworkResponse response, int errorCode);
}
