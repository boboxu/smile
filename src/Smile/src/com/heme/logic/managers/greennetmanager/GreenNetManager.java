package com.heme.logic.managers.greennetmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.greennet.SendCommandRequest;
import com.heme.logic.httpprotocols.greennet.SendCommandRequest.COMMANDTYPE;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class GreenNetManager extends BaseBusinessLogicManager implements IGreenNetManagerInterface{

	@Override
	protected void onSuccessResponse(BaseResponse response,
			Handler handler) {
		// TODO Auto-generated method stub
		
	}
	
	private void sendCommand(SendCommandRequest.COMMANDTYPE commandType,Handler handler)
	{
		SendCommandRequest request = new SendCommandRequest(LogicManager.accountManager().getCurrentSessionId(), LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setCommandInfo(commandType);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendRebootCommand(Handler handler) {
		this.sendCommand(COMMANDTYPE.CmdReboot, handler);
	}

	@Override
	public void sendShutdownCommand(Handler handler) {
		this.sendCommand(COMMANDTYPE.CmdShutDown, handler);
		
	}

	@Override
	public void sendCloseProcessCommand(Handler handler) {
		this.sendCommand(COMMANDTYPE.CmdCloseProcess, handler);
	}

	@Override
	public void sendInterceptCommand(Handler handler) {
		this.sendCommand(COMMANDTYPE.CmdIntercept, handler);
	}
}
