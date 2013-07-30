
package com.heme.foundation.net;

/**
 * @author ottozheng
 *
 */
public interface INetworkEngineInterface
{
	
	/**
	 * 发送一个请求
	 * @param request
	 * @return
	 */
	public boolean sendNetworkRequest(NetworkRequest request);
	
	/**
	 * 取消一个请求
	 * @param request
	 */
	public void cancelNetworkRequest(NetworkRequest request);
	
}
