package com.heme.logic.managers.loginmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.httpprotocols.login.LoginRequest.LOGINTYPE;
import com.heme.logic.httpprotocols.login.LoginResponse;
import com.heme.logic.httpprotocols.status.UpdateStatusRequest;
import com.heme.logic.httpprotocols.status.UpdateStatusResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.LoginReq;
import com.heme.logic.module.Status.EStatus;
import com.heme.logic.module.Status.SetStatusRsp;
import com.heme.utils.FileUtil;
import com.heme.utils.StringUtil;

public class LoginManager extends BaseBusinessLogicManager implements
		ILoginManagerInterface {
	public static final String LOGINREQDATANAME = "loginreqinfo";
	
	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof LoginResponse) 
		{
			LoginResponse logresponse = (LoginResponse) response;
			if (logresponse.getLoginRsp().getErrCode() == 0) {
				LogicManager.accountManager().setCurrentAccount(
						logresponse.getLoginRsp());
				handleresponse(Constans.LOGIN_SUCCESS, logresponse.getLoginRsp(),
						handler);
				FileUtil.writeToFile(LOGINREQDATANAME,((LoginRequest)logresponse.getmRequest()).getmLoginDataBuilder().build(),false);
				
			} else {
				handleresponse(Constans.LOGIN_FAILED,
						((LoginResponse) response).getLoginRsp(), handler);
			}
		}
		else if (response instanceof UpdateStatusResponse) 
		{
			UpdateStatusResponse usresponse = (UpdateStatusResponse)response;
			SetStatusRsp setStatusRsp = usresponse.getmSetStatusRsp();
			if (setStatusRsp.getUint32Result() == 0) 
			{
				//正常退出
			}
			else
			{
				//未正常退出
			}
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
		request.setLoginInfo(id, StringUtil.MD5Encode(pwd), type);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
		return 0;
	}

	
	@Override
	public void logout(Handler handler) {
		UpdateStatusRequest request = new UpdateStatusRequest(LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setStatus(EStatus.OFFLINE);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
		//其实是应该放到网络回掉里面去干这个事情的，怕网络的异常太多，就放这先。
		FileUtil.deleteFile(LOGINREQDATANAME);
	}

	@Override
	public boolean LoginWithSavedData(Handler handler) {
		LoginReq req = FileUtil.readLoginRspFromFile(LOGINREQDATANAME);
		if (req == null) {
			return false;
		}
		LoginRequest request = new LoginRequest();
		request.setLoginInfo(req);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
		return true;
	}
}
