package com.heme.logic.managers.loginmanager;

import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.httpprotocols.login.LoginRequest.LoginType;
import com.heme.logic.httpprotocols.login.LoginResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.LoginRsp;

public class LoginManager extends BaseBusinessLogicManager implements
		ILoginManagerInterface {

	@Override
	protected void onSuccessResponse(BaseResponse response,
			IBaseLogicManagerListener listener) {
		LoginRsp loginRsp = ((LoginResponse)response).getLoginRsp();
		//取出数据来写数据库
		
		if (null != listener) 
		{
			((ILoginManagerListener)listener).onLoginSuccess();
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response,
			IBaseLogicManagerListener listener) {
		if (null != listener) {
			((ILoginManagerListener)listener).onLoginFailed(response.getmError().getErrMsg());
		}
		return super.onFailedResponse(response, listener);
	}

	@Override
	public int Login(String id, String pwd, LoginType type,
			ILoginManagerListener listener) {
		LoginRequest request = new LoginRequest();
		request.setLoginInfo(id, pwd, type);
		sendRequest(request, listener, getClass().getName(), _FUNC_());
		return 0;
	}

}
