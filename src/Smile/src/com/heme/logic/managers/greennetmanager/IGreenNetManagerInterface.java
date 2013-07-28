package com.heme.logic.managers.greennetmanager;

import android.os.Handler;

import com.heme.logic.httpprotocols.greennet.SendCommandRequest.COMMANDTYPE;
import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IGreenNetManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	public void sendCommand(COMMANDTYPE type,Handler handler);
}
