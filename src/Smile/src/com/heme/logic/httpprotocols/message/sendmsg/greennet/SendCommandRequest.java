package com.heme.logic.httpprotocols.message.sendmsg.greennet;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;
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
	
	public SendCommandRequest(long systemId, String sessionId,List<Long> mTargetId) {
		super(systemId,sessionId,mTargetId,new ArrayList<Long>(),MessageType.MT_NetGuard);
	}
	
	public void setNetGuardMsgInfo(NetGuardInfo msgInfo,ByteString context) 
	{
		mCommonMsgBuilder.setMsgMoitorMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
}
