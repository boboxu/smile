
package com.heme.foundation.net;

/**
 * @author ottozheng
 *
 */
public interface IHttpEngineInterface
{
	
	/**
	 * 发送一个请求
	 * @param request
	 * @return
	 */
	public boolean sendHttpRequest(NetworkRequest request);
	
	/**
	 * 取消一个请求
	 * @param request
	 */
	public void cancelNetworkRequest(NetworkRequest request);
	
}
