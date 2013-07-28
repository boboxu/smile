package com.heme.logic.managers.loginmanager;

import android.os.Handler;
import android.os.Message;

import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.httpprotocols.login.LoginRequest.LOGINTYPE;
import com.heme.logic.httpprotocols.login.LoginResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.LoginRsp;

public class LoginManager extends BaseBusinessLogicManager implements
		ILoginManagerInterface {

	@Override
	protected void onSuccessResponse(BaseResponse response,
			Handler handler) {
		//取出数据来写数据库
		
		if (null != handler) 
		{
			Message msgMessage = handler.obtainMessage();
			msgMessage.what = Constans.LOGIN_SUCCESS;
			msgMessage.obj = ((LoginResponse)response).getLoginRsp();  
			handler.sendMessage(msgMessage);
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response,
			Handler handler) {
		if (null != handler) {
			Message msgMessage = handler.obtainMessage();
			msgMessage.what = Constans.LOGIN_SUCCESS;
			msgMessage.obj = ((LoginResponse)response).getLoginRsp();
			handler.sendMessage(msgMessage);
		}
		return super.onFailedResponse(response, handler);
	}

	@Override
	public int Login(String id, String pwd, LOGINTYPE type,
			Handler handler) {
		LoginRequest request = new LoginRequest();
		request.setLoginInfo(id, pwd, type);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
		return 0;
	}

}
