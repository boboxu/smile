package com.heme.logic.managers.groupmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupRequest.VERIFYTYPE;
import com.heme.logic.httpprotocols.groupinfo.getgroup.GetGroupInfoRequest;
import com.heme.logic.httpprotocols.groupinfo.getgroup.GetGroupInfoResponse;
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
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		if (response instanceof GetGroupInfoResponse) {
			handleresponse(Constans.GET_GROUPINFO_FAILED,
					((GetGroupInfoResponse) response).getmGetGroupInfoRsp(),
					handler);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTempGroup(String groupName, List<Long> systemIdList,
			Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGroupName(String groupName, int groupId, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delGroup(int groupId, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMemberToGroup(int groupId, List<Long> memberIdList,
			Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delMemberInGroup(int groupId, List<Long> memberIdList,
			Handler handler) {
		// TODO Auto-generated method stub
		
	}

}
