package com.heme.logic.managers.loginmanager;

import com.heme.logic.httpprotocols.login.LoginRequest;
import com.heme.logic.managers.base.IBusinessLogicManagerListener;

public interface ILoginManagerInterface {
	public int Login(String id,String pwd,LoginRequest.LoginType type,ILoginManagerListener listener);
}
