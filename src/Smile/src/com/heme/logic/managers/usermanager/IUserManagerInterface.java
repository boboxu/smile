package com.heme.logic.managers.usermanager;

import java.util.List;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IUserManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	public void getUserInfo(List<Long> systemIdList,Handler handler);
	public void getVerboseUserInfo(List<Long> systemIdList,Handler handler);
	public void updateUserIcon(String iconName,Handler handler);
	public void updateSignature(String signature,Handler handler);
}
