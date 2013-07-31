package com.heme.logic.managers.passwordmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.password.modifypwd.ModifyPwdRequest;
import com.heme.logic.httpprotocols.password.resetpwd.ResetPwdRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class PasswordManager extends BaseBusinessLogicManager implements
		IPassworManagerInterface {

	@Override
	public void resetPassword(String verifyCode, Handler handler) {
		ResetPwdRequest request = new ResetPwdRequest();
		request.setVerifyCode(verifyCode);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void modifyPassword(String oldpwd, String newpwd, Handler handler) {
		ModifyPwdRequest request = new ModifyPwdRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setPassword(newpwd, oldpwd);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		// TODO Auto-generated method stub

	}

}
