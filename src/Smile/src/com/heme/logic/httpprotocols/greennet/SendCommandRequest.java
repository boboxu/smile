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
	
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((PcCtrlReq.Builder)mDataBuilder).setClientType(version);
		((PcCtrlReq.Builder)mDataBuilder).setVersionNo(version);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((PcCtrlReq.Builder)mDataBuilder).setSessionId(sessionId);
		((PcCtrlReq.Builder)mDataBuilder).setSystemId(systemId);
	};
	
	public void setCommandInfo(COMMANDTYPE type)
	{
		((PcCtrlReq.Builder)mDataBuilder).setPcCtrlCmd(COMMANDTYPE.value(type));
		super.buildAccessReq(((PcCtrlReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = PcCtrlReq.newBuilder();
		
	}
}
