package com.heme.logic.managers.passwordmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IPassworManagerInterface extends IBaseBusinessLogicManagerInterface{
	public void resetPassword(String verifyCode,Handler handler);
}
