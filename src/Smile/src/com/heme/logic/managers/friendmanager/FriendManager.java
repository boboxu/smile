package com.heme.logic.managers.friendmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetUserInfoResponse;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetVerboseUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetVerboseUserInfoResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class FriendManager extends BaseBusinessLogicManager implements
		IFriendManagerInterface {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof GetUserInfoResponse) {
			handleresponse(Constans.GET_USERINFO_SUCCESS,
					((GetUserInfoResponse) response).getmGetUserInfoRsp(),
					handler);
		}
		else if (response instanceof GetVerboseUserInfoResponse) {
			handleresponse(Constans.GET_VERBOSEUSERINFO_SUCCESS,
					((GetVerboseUserInfoResponse) response).getmGetVerboseInfoRsp(),
					handler);
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {

		if (response instanceof GetUserInfoResponse) {
			handleresponse(Constans.GET_USERINFO_FAILED,
					((GetUserInfoResponse) response).getmGetUserInfoRsp(),
					handler);
		}
		else if (response instanceof GetVerboseUserInfoResponse) {
			handleresponse(Constans.GET_VERBOSEUSERINFO_FAILED,
					((GetVerboseUserInfoResponse) response).getmGetVerboseInfoRsp(),
					handler);
		}
		return super.onFailedResponse(response, handler);
	}

	@Override
	public void getFriendInfo(List<Long> systemIdList, Handler handler) {
		GetUserInfoRequest request = new GetUserInfoRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setTargetId(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}
	
	public void getVerboseFriendInfo(List<Long> systemIdList,Handler handler)
	{
		GetVerboseUserInfoRequest request = new GetVerboseUserInfoRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setTargetId(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void addFriend(long systemId, String verifyMsg, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFriendRemark(long systemId, String remark, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delFriend(long systemId, Handler handler) {
		// TODO Auto-generated method stub
		
	}

}
