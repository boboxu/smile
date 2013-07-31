package com.heme.logic.managers.handleraskmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.handleask.HandleFriendAskRequest;
import com.heme.logic.httpprotocols.handleask.HandleFriendAskResponse;
import com.heme.logic.httpprotocols.handleask.HandleGroupAskRequest;
import com.heme.logic.httpprotocols.handleask.HandleGroupAskResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class HandlerAskManager extends BaseBusinessLogicManager implements
		IHandleAskManagerInterface {

	@Override
	public void HandleSocialGroup(int groupId, Handler handler) {
		HandleGroupAskRequest request = new HandleGroupAskRequest(LogicManager.accountManager().getCurrentSessionId(), LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setTargetId(groupId);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof HandleGroupAskResponse) {
			
		}
		else if (response instanceof HandleFriendAskResponse) {
			
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		if (response instanceof HandleGroupAskResponse) {
			
		}
		else if (response instanceof HandleFriendAskResponse) {
			
		}
		return super.onFailedResponse(response, handler);
	}
	
	@Override
	public void HandleFriend(long systemId, Handler handler) {
		HandleFriendAskRequest request = new HandleFriendAskRequest(LogicManager.accountManager().getCurrentSessionId(), LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setTargetId(systemId);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

}
