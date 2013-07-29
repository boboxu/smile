package com.heme.logic.httpprotocols.greennet;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.PcCtrlReq;

public class SendCommandRequest extends BaseLoginedBusinessRequest {
	public enum COMMANDTYPE
	{
		CmdReboot,	//重启
		CmdShutDonw,//关机
		CmdCloseProcess,//关闭进程
		CmdIntercept;//信息拦截
		public static int value(COMMANDTYPE type)
		{
			switch (type) {
			case CmdReboot:
				return 1;
			case CmdShutDonw:
				return 2;
			case CmdCloseProcess:
				return 3;
			case CmdIntercept:
				return 4;
			default:
				return 1;
			}
		}
	}
	
	PcCtrlReq.Builder mPcCtrlReqBuilder = PcCtrlReq.newBuilder();
	
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mPcCtrlReqBuilder.setClientType(version);
		mPcCtrlReqBuilder.setVersionNo(version);
		super.setBody(mPcCtrlReqBuilder.build().toByteString());
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mPcCtrlReqBuilder.setSessionId(sessionId);
		mPcCtrlReqBuilder.setSystemId(systemId);
		super.setBody(mPcCtrlReqBuilder.build().toByteString());
	};
	
	public void setCommandInfo(COMMANDTYPE type)
	{
		mPcCtrlReqBuilder.setPcCtrlCmd(COMMANDTYPE.value(type));
		super.setBody(mPcCtrlReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
