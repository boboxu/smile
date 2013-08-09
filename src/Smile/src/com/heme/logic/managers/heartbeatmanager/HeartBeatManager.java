package com.heme.logic.managers.heartbeatmanager;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.heartbeat.HeartBeatRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

import android.os.Handler;

public class HeartBeatManager extends BaseBusinessLogicManager implements IHeartBeatManagerInterface {

	@Override
	public void beat(Handler handler) {
		HeartBeatRequest request = new HeartBeatRequest(LogicManager.accountManager().getCurrentAccoutSystemId());
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		// TODO Auto-generated method stub
		
	}

}
