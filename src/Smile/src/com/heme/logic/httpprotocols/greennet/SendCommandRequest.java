package com.heme.logic.httpprotocols.greennet;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;

public class SendCommandRequest extends BaseBusinessRequest {
	public enum CommandType
	{
		CmdReboot,	//重启
		CmdShutDonw,//关机
		CmdCloseProcess,//关闭进程
		CmdIntercept,//中断
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		// TODO Auto-generated method stub
		
	};
}
