package com.heme.logic.managers.heartbeatmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IHeartBeatManagerInterface extends IBaseBusinessLogicManagerInterface {
	public void beat(Handler handler);
}
