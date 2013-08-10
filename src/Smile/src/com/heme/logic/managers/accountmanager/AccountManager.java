package com.heme.logic.managers.accountmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.httpprotocols.login.LoginResponse;
import com.heme.logic.httpprotocols.setfriendright.SetFriendRightRequest;
import com.heme.logic.httpprotocols.setfriendright.SetFriendRightRequest.VERIFYTYPE;
import com.heme.logic.httpprotocols.status.UpdateStatusRequest;
import com.heme.logic.httpprotocols.userinfo.updatesignature.UpdateSignatureRequest;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserIconRequest;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.LoginRsp;
import com.heme.logic.module.Status.EStatus;
import com.heme.utils.FileUtil;

public class AccountManager extends BaseBusinessLogicManager implements
		IAccountManagerInterface {
	
	LoginRsp mLoginRsp = null;

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
			
	}

	@Override
	public void updateUserIcon(String iconName, Handler handler) {
		UpdateUserIconRequest request = new UpdateUserIconRequest(mLoginRsp.getSessionId(), mLoginRsp.getSystemId());
		request.setIconName(iconName);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void updateSignature(String signature, Handler handler) {
		UpdateSignatureRequest request = new UpdateSignatureRequest(mLoginRsp.getSessionId(), mLoginRsp.getSystemId());
		request.setSignature(signature);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void updateFriendRight(VERIFYTYPE type, boolean isAutoAddFriend,
			boolean isCanBeSearched, boolean isRecommend, Handler handler) {
		SetFriendRightRequest request = new SetFriendRightRequest(mLoginRsp.getSessionId(),mLoginRsp.getSystemId());
		request.setFriendRight(type, isAutoAddFriend, isCanBeSearched, isRecommend);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void updateUserInfo(SEXTYPE type, String birthday, String job,
			String local, String email, String interest, Handler handler) {
		UpdateUserInfoRequest request = new UpdateUserInfoRequest(mLoginRsp.getSessionId(), mLoginRsp.getSystemId());
		request.setSelfInfo(type, birthday, job, local, email, interest);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public long getCurrentAccoutSystemId() {
		if (mLoginRsp == null) 
		{
			mLoginRsp = FileUtil.readLoginRspFromFile(LoginResponse.LOGINRSPDATAFILEPATH);
		}
		return mLoginRsp.getSystemId();
	}

	@Override
	public void removeCurrentAccount() {
		//清空账户信息的数据库
	}

	@Override
	public void setCurrentAccount(LoginRsp loginrsp) {
		if (loginrsp == null) {
			LoginRsp.Builder logRspbuilder = LoginRsp.newBuilder();
			logRspbuilder.setErrCode(0);
			logRspbuilder.setErrString("");
			logRspbuilder.setSystemId(123);
			logRspbuilder.setSessionId("1234");
			logRspbuilder.addFriendSystemId(321);
			logRspbuilder.addGroupId(12);
			loginrsp = logRspbuilder.build();
		}
		mLoginRsp = loginrsp;
		FileUtil.writeToFile(LoginResponse.LOGINRSPDATAFILEPATH, loginrsp);
	}

	

	@Override
	public String getCurrentSessionId() {
		if (mLoginRsp == null) 
		{
			mLoginRsp = FileUtil.readLoginRspFromFile(LoginResponse.LOGINRSPDATAFILEPATH);
		}
		return mLoginRsp.getSessionId();
	}
}
