package com.heme.logic.managers.loginmanager;

import android.os.Handler;

import com.heme.logic.httpprotocols.login.LoginRequest;

public interface ILoginManagerInterface {
	public int Login(String id,String pwd,LoginRequest.LOGINTYPE type,Handler handler);
}
