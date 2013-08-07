package com.heme.foundation.net;

public interface IProtocolEngineDelegate
{
	/**
	 * 有协议返回
	 * @param buffer
	 */
	public void onRecvProtocolBuffer(byte[] buffer);
}
