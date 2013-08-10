package com.heme.logic.managers.groupmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.friend.delfriend.DelFriendRequest;
import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupRequest;
import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupResponse;
import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupRequest.VERIFYTYPE;
import com.heme.logic.httpprotocols.groupinfo.creategroup.temp.CreateTempGroupRequest;
import com.heme.logic.httpprotocols.groupinfo.creategroup.temp.CreateTempGroupResponse;
import com.heme.logic.httpprotocols.groupinfo.deletegroup.DeleteGroupRequest;
import com.heme.logic.httpprotocols.groupinfo.deletegroup.DeleteGroupResponse;
import com.heme.logic.httpprotocols.groupinfo.getgroup.GetGroupInfoRequest;
import com.heme.logic.httpprotocols.groupinfo.getgroup.GetGroupInfoResponse;
import com.heme.logic.httpprotocols.groupinfo.updategroup.UpdateGroupRequest;
import com.heme.logic.httpprotocols.groupinfo.updategroup.UpdateGroupResponse;
import com.heme.logic.httpprotocols.groupinfo.updategroup.addmember.AddGroupMemberRequest;
import com.heme.logic.httpprotocols.groupinfo.updategroup.addmember.AddGroupMemberResponse;
import com.heme.logic.httpprotocols.groupinfo.updategroup.delmember.DelGroupMemberRequest;
import com.heme.logic.httpprotocols.groupinfo.updategroup.delmember.DelGroupMemberResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class GroupManager extends BaseBusinessLogicManager implements
		IGroupManagerInterface {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof GetGroupInfoResponse) {
			handleresponse(Constans.GET_GROUPINFO_SUCCESS,
					((GetGroupInfoResponse) response).getmGetGroupInfoRsp(),
					handler);
		}
		else if (response instanceof AddGroupMemberResponse) 
		{
			
		}
		else if (response instanceof CreatePermanentGroupResponse) {
			
		}
		else if (response instanceof CreateTempGroupResponse) {
			
		}
		else if (response instanceof DeleteGroupResponse) {
			
		}
		else if(response instanceof DelGroupMemberResponse){
			
		}
		else if (response instanceof DelGroupMemberResponse) {
			
		}
		else if (response instanceof UpdateGroupResponse) {
			
		}
		else
		{
			
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		if (response instanceof GetGroupInfoResponse) {
			handleresponse(Constans.GET_GROUPINFO_FAILED,
					((GetGroupInfoResponse) response).getmGetGroupInfoRsp(),
					handler);
		}
		else if (response instanceof AddGroupMemberResponse) 
		{
			
		}
		else if (response instanceof CreatePermanentGroupResponse) {
			
		}
		else if (response instanceof CreateTempGroupResponse) {
			
		}
		else if (response instanceof DeleteGroupResponse) {
			
		}
		else if(response instanceof DelGroupMemberResponse){
			
		}
		else if (response instanceof DelGroupMemberResponse) {
			
		}
		else if (response instanceof UpdateGroupResponse) {
			
		}
		return super.onFailedResponse(response, handler);

	}

	@Override
	public void getGroupInfo(List<Integer> groupIdList, Handler handler) {
		GetGroupInfoRequest request = new GetGroupInfoRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setGroupId(groupIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void createPermanentGroup(AreaInfo areaInfo,
			SchoolCombine schoolInfo, String groupName, VERIFYTYPE type,
			List<Long> targetIdList, Handler handler) {
		CreatePermanentGroupRequest request = new CreatePermanentGroupRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setGroupInfo(areaInfo.getmCountryName(), schoolInfo.getSchoolName(), groupName, type, targetIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void createTempGroup(String groupName, List<Long> systemIdList,
			Handler handler) {
		CreateTempGroupRequest request = new CreateTempGroupRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setGroupInfo(groupName, systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void updateGroupName(String groupName, int groupId, Handler handler) {
		UpdateGroupRequest request = new UpdateGroupRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setGroupInfo(groupName, groupId);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void delGroup(int groupId, Handler handler) {
		DeleteGroupRequest request = new DeleteGroupRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setGroupInfo(groupId);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void addMemberToGroup(int groupId, List<Long> memberIdList,
			Handler handler) {
		AddGroupMemberRequest request = new AddGroupMemberRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setAddInfo(groupId, memberIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void delMemberInGroup(int groupId, List<Long> memberIdList,
			Handler handler) {
		DelGroupMemberRequest request = new DelGroupMemberRequest(LogicManager.accountManager().getCurrentSessionId(),LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setDelInfo(groupId, memberIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

}
