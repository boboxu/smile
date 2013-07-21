package com.heme.logic.managers.greennetmanager;

import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class GreenNetManager extends BaseBusinessLogicManager {

	enum CommandType
	{
		CmdReboot,	//重启
		CmdShutDonw,//关机
		CmdCloseProcess,//关闭进程
		CmdIntercept,//中断
		
	};
	@Override
	protected void onSuccessResponse(BaseResponse response,
			IBaseLogicManagerListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void sendRebootCommand()
	{
		
	}
	
	public void sendShutdownCommand()
	{
		
	}
	
	public void sendCloseProcessCommand()
	{
		
	}
	
	public void sendInterceptCommand()
	{
		
	}
	
	private void sendCommand(CommandType commandType)
	{
		
	}
}
