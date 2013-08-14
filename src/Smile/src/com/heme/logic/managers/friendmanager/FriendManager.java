package com.heme.logic.managers.friendmanager;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

import com.google.protobuf.ByteString;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.friend.addfriend.AddFriendRequest;
import com.heme.logic.httpprotocols.friend.addfriend.AddFriendResponse;
import com.heme.logic.httpprotocols.friend.delfriend.DelFriendRequest;
import com.heme.logic.httpprotocols.friend.delfriend.DelFriendResponse;
import com.heme.logic.httpprotocols.friend.updatefriend.UpdateFriendRequest;
import com.heme.logic.httpprotocols.friend.updatefriend.UpdateFriendResponse;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetUserInfoResponse;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetVerboseUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetVerboseUserInfoResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Message.VerifyRequest;

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
		else if (response instanceof AddFriendResponse) {
			handleresponse(((AddFriendResponse) response).getSendMsgRes().getUint32Result() == 0?Constans.ADD_FRIEND_SUCCESS:Constans.ADD_FRIEND_FAILED, ((AddFriendResponse) response).getSendMsgRes(), handler);
		}
		else if (response instanceof DelFriendResponse) {
			
		}
		else if (response instanceof UpdateFriendResponse) {
			
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
		else if (response instanceof AddFriendResponse) {
			
		}
		else if (response instanceof DelFriendResponse) {
			
		}
		else if (response instanceof UpdateFriendResponse) {
			
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
		List<Long> targetList = new ArrayList<Long>();
		targetList.add(Long.valueOf(systemId));
		AddFriendRequest request = new AddFriendRequest(LogicManager.accountManager().getCurrentAccoutSystemId(),LogicManager.accountManager().getCurrentSessionId(),targetList);
		VerifyRequest.Builder verifyMsgInfobuilder = VerifyRequest.newBuilder();
		verifyMsgInfobuilder.setUint32VerifyType(0);
		verifyMsgInfobuilder.setStrVerifyInfo(verifyMsg);
		request.setVerifyRequest(verifyMsgInfobuilder.build(), ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void updateFriendRemark(long systemId, String remark, Handler handler) {
		UpdateFriendRequest request = new UpdateFriendRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setFriendDescription(systemId, remark);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void delFriend(List<Long> systemIdList, Handler handler) {
		DelFriendRequest request = new DelFriendRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setTargetSystemId(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void getFriendInfo(Long systemId, Handler handler) {
		GetUserInfoRequest request = new GetUserInfoRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		List<Long> systemIdList = new ArrayList<Long>();
		systemIdList.add(systemId);
		request.setTargetId(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

}
