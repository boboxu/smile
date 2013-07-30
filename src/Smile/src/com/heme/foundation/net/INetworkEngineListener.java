
package com.heme.foundation.net;

import java.nio.ByteBuffer;

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
	public void onRequestSuccess(NetworkResponse response, ByteBuffer data);
	
	/**
	 * 请求失败回调
	 * @param response
	 */
	public void onRequestFail(NetworkResponse response, int errorCode);
}
