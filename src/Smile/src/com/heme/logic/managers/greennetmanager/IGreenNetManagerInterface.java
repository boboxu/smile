package com.heme.logic.managers.greennetmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IGreenNetManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	public void sendRebootCommand(Handler handler);
	
	public void sendShutdownCommand(Handler handler);
	
	public void sendCloseProcessCommand(Handler handler);
	
	public void sendInterceptCommand(Handler handler);
}
