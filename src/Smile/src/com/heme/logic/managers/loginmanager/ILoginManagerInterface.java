package com.heme.logic.managers.loginmanager;

import android.R.bool;
import android.os.Handler;

import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface ILoginManagerInterface extends IBaseBusinessLogicManagerInterface{
	public int Login(String id,String pwd,LoginRequest.LOGINTYPE type,Handler handler);
	public void logout(Handler handler);
	public boolean LoginWithSavedData(Handler handler);
}
