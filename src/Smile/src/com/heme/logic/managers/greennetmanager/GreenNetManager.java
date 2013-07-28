package com.heme.logic.managers.greennetmanager;

import android.os.Handler;

import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.httpprotocols.greennet.SendCommandRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class GreenNetManager extends BaseBusinessLogicManager {

	@Override
	protected void onSuccessResponse(BaseResponse response,
			Handler handler) {
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
	
	private void sendCommand(SendCommandRequest.COMMANDTYPE commandType)
	{
		
	}
}
