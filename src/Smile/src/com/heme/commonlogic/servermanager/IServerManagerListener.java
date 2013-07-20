package com.heme.commonlogic.servermanager;

public interface IServerManagerListener 
{
	/***
	 * 
	 * @param response
	 */
	public void onRequestSuccess(BaseResponse response);
	
	/***
	 * 
	 * @param response
	 */
	public void onRequestFail(BaseResponse response);
}
