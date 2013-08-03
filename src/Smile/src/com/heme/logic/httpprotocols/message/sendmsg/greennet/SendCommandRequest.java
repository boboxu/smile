package com.heme.logic.httpprotocols.message.sendmsg.greennet;

import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.NetGuardInfo;

public class SendCommandRequest extends BaseMessageRequest {

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
	
	//应该也不需要targetid和targetgid
	//内容类型又是神马？
	protected SendCommandRequest(long systemId, int sessionId) {
		super(systemId,sessionId,null,null,MSGTYPE.TYPENETGUARD);
	}
	
	public void setCommandInfo(COMMANDTYPE type,int eventId,String reportInfo,ByteString context)
	{
		NetGuardInfo.Builder msgBuilder = NetGuardInfo.newBuilder();
		msgBuilder.setUint32Action(COMMANDTYPE.value(type));
		msgBuilder.setUint32EventId(eventId);
		msgBuilder.setStrReportInfo(reportInfo);
		super.setNetGuardMsgInfo(msgBuilder.build(), context);		
	}
	
}
