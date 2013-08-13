package com.heme.foundation.net;

public interface IProtocolEngineDelegate
{
	/**
	 * 有协议返回
	 * @param buffer
	 */
	public void onRecvProtocolBuffer(byte[] buffer);
	
	/**
	 * 需要发送心跳包
	 */
	public void onNeedSendHeartBeat();
	
	/**
	 * 服务器连接失败
	 */
	public void onProtocolConnectFailed();
	
	/**
	 * 服务器连接超时
	 */
	public void onProtocolConnectTimeout();
	
}
