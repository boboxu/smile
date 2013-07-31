package com.heme.logic.managers.handleraskmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IHandleAskManagerInterface extends IBaseBusinessLogicManagerInterface {
	public void HandleSocialGroup(int groupId,Handler handler);
	public void HandleFriend(long systemId,Handler handler);
}
