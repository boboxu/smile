package com.heme.logic.managers.usermanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetUserInfoResponse;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetVerboseUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.getuserinfo.GetVerboseUserInfoResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class UserManager extends BaseBusinessLogicManager implements
		IUserManagerInterface {

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
	public void getUserInfo(List<Long> systemIdList, Handler handler) {
		GetUserInfoRequest request = new GetUserInfoRequest();
		request.setTargetId(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}
	
	public void getVerboseUserInfo(List<Long> systemIdList,Handler handler)
	{
		GetVerboseUserInfoRequest request = new GetVerboseUserInfoRequest();
		request.setTargetId(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void updateUserIcon(String iconName, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSignature(String signature, Handler handler) {
		// TODO Auto-generated method stub
		
	}

}
