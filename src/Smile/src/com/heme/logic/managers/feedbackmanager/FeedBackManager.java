package com.heme.logic.managers.feedbackmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.feedback.FeedBackRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class FeedBackManager extends BaseBusinessLogicManager implements
		IFeedBackManagerInterface {

	@Override
	public void feedback(String content, Handler handler) {
		FeedBackRequest request = new FeedBackRequest(LogicManager
				.accountManager().getCurrentSessionId(), LogicManager
				.accountManager().getCurrentAccoutSystemId());
		request.setContent(content);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}
	
	

}
