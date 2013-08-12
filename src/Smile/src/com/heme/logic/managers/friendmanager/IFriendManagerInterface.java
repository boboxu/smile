package com.heme.logic.managers.friendmanager;

import java.util.List;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IFriendManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	public void getFriendInfo(List<Long> systemIdList,Handler handler);
	public void getVerboseFriendInfo(List<Long> systemIdList,Handler handler);
	public void addFriend(long systemId,String verifyMsg,Handler handler);
	public void getFriendInfo(Long systemId,Handler handler);
	/***
	 * 
	 * @param systemId 目标ID
	 * @param remark 备注
	 * @param handler 回掉
	 */
	public void updateFriendRemark(long systemId,String remark,Handler handler);
	
	public void delFriend(List<Long> targetIdList,Handler handler);
}
