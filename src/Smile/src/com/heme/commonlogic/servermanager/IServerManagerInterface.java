package com.heme.commonlogic.servermanager;

public interface IServerManagerInterface {
	/**
	 * sendRequest 发送请求
	 * @param request BaseRequest或者其子类
	 * @return requestID
	 */
	public int sendRequest(BaseRequest request);
	
	/**
	 * 取消请求
	 * @param requestId requestid
	 * @param delegate 回调
	 */
	void cancelRequest(int requestId, IServerManagerListener listener);
}
