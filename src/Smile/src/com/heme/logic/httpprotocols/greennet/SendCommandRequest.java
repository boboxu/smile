package com.heme.logic.httpprotocols.greennet;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.PcCtrlReq;

public class SendCommandRequest extends BaseLoginedBusinessRequest {
	public enum CommandType
	{
		CmdReboot,	//重启
		CmdShutDonw,//关机
		CmdCloseProcess,//关闭进程
		CmdIntercept,//中断
	}
	
	PcCtrlReq.Builder mPcCtrlReqBuilder = PcCtrlReq.newBuilder();
	
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mPcCtrlReqBuilder.setClientType(version);
		mPcCtrlReqBuilder.setVersionNo(version);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mPcCtrlReqBuilder.setSessionId(sessionId);
	};
}
