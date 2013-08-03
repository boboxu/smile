package com.heme.logic.httpprotocols.greennet;

import com.heme.logic.httpprotocols.base.message.BaseSendMsgRequest;
import com.heme.logic.module.Message.NetGuardInfo;

public class SendCommandRequest extends BaseSendMsgRequest {

	protected SendCommandRequest(long systemId, int sessionId) {
		super(systemId, sessionId);
		
	}

	public enum COMMANDTYPE
	{
		CmdUpLoad,	//上报
		CmdReboot,	//重启
		CmdShutDown,//关机
		CmdCloseProcess,//关闭进程
		CmdIntercept;//信息拦截
		public static int value(COMMANDTYPE type)
		{
			switch (type) {
			case CmdUpLoad:
				return 1;
			case CmdReboot:
				return 2;
			case CmdShutDown:
				return 3;
			case CmdCloseProcess:
				return 4;
			case CmdIntercept:
				return 5;
			default:
				return 1;
			}
		}
	}
	
	public void setCommandInfo(COMMANDTYPE type,int eventId,String reportInfo)
	{
//		((PcCtrlReq.Builder)mDataBuilder).setPcCtrlCmd(COMMANDTYPE.value(type));
//		super.buildAccessReq(((PcCtrlReq.Builder)mDataBuilder).build().toByteString());
		NetGuardInfo.Builder msgBuilder = NetGuardInfo.newBuilder();
		msgBuilder.setUint32Action(COMMANDTYPE.value(type));
		msgBuilder.setUint32EventId(eventId);
		msgBuilder.setStrReportInfo(reportInfo);
		
		
	}
	
}
