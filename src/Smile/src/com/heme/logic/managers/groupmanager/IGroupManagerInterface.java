package com.heme.logic.managers.groupmanager;

import java.util.List;

import android.os.Handler;

import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupRequest;
import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;

public interface IGroupManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	public void getGroupInfo(List<Integer> groupIdList,Handler handler);
	public void createPermanentGroup(AreaInfo areaInfo,SchoolCombine schoolInfo,String groupName,CreatePermanentGroupRequest.VERIFYTYPE type,List<Long> targetIdList,Handler handler);
	public void createTempGroup(String groupName,List<Long> systemIdList,Handler handler);
	public void updateGroupName(String groupName,int groupId,Handler handler);
	public void delGroup(int groupId,Handler handler);
	public void addMemberToGroup(int groupId,List<Long> memberIdList,Handler handler);
	public void delMemberInGroup(int groupId,List<Long> memberIdList,Handler handler);
	
}
