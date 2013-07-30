package com.heme.logic.managers.feedbackmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IFeedBackManagerInterface extends IBaseBusinessLogicManagerInterface{
	public void feedback(String content,Handler handler);
}
