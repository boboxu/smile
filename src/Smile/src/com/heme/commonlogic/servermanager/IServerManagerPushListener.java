package com.heme.commonlogic.servermanager;

import com.heme.logic.module.Message.MessageOpr;

public interface IServerManagerPushListener
{
	public void onRecivedPushMsg(MessageOpr msgOpr);
}
