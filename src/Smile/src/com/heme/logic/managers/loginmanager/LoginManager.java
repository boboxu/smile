package com.heme.logic.managers.loginmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.httpprotocols.login.LoginRequest.LOGINTYPE;
import com.heme.logic.httpprotocols.login.LoginResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class LoginManager extends BaseBusinessLogicManager implements
		ILoginManagerInterface {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		LoginResponse logresponse = (LoginResponse) response;
		if (logresponse.getLoginRsp().getErrCode() == 0) {
			LogicManager.accountManager().setCurrentAccount(
					logresponse.getLoginRsp());
			handleresponse(Constans.LOGIN_SUCCESS, logresponse.getLoginRsp(),
					handler);
		} else {
			handleresponse(Constans.LOGIN_FAILED,
					((LoginResponse) response).getLoginRsp(), handler);
		}

	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		handleresponse(Constans.LOGIN_FAILED,
				((LoginResponse) response).getLoginRsp(), handler);
		return super.onFailedResponse(response, handler);
	}

	@Override
	public int Login(String id, String pwd, LOGINTYPE type, Handler handler) {
		LoginRequest request = new LoginRequest();
		request.setLoginInfo(id, pwd, type);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
		return 0;
	}
}
