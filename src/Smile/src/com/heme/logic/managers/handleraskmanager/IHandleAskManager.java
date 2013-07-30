package com.heme.logic.managers.handleraskmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IHandleAskManager extends IBaseBusinessLogicManagerInterface {
	public void HandleSocialGroup(long systemId,int groupId,Handler handler);
}
