package com.heme.logic.managers.loginmanager;

import com.heme.logic.managers.base.IBusinessLogicManagerListener;

public interface ILoginManagerListener extends IBusinessLogicManagerListener{
	public void onLoginSuccess();
	public void onLoginFailed(String errmsg);
}
