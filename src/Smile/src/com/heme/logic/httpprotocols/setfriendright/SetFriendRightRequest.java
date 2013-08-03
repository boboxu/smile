package com.heme.logic.httpprotocols.setfriendright;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetFriendRightReq;

public class SetFriendRightRequest extends BaseLoginedBusinessRequest {
	public SetFriendRightRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	// 自己验证类型
	public enum VERIFYTYPE {
		ANYONEPERMITTED, // 允许任何人
		NEEDVERIFY, // 验证
		NOONEPERMITTED;// 不允许任何人
		public static int value(VERIFYTYPE version) {
			switch (version) {
			case ANYONEPERMITTED:
				return 1;
			case NEEDVERIFY:
				return 2;
			case NOONEPERMITTED:
				return 3;
			default:
				return 1;
			}
		}
	}
	
	
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SetFriendRightReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetFriendRightReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {

		((SetFriendRightReq.Builder)mDataBuilder).setVersionNo(version);
		((SetFriendRightReq.Builder)mDataBuilder).setClientType(clientType);
		
	}

	/***
	 * 
	 * @param type 别人加我的时候的是否需要验证
	 * @param isAutoAddFriend 回复陌生人是否自动加好友
	 * @param isCanBeSearched 是否允许被搜索到
	 * @param isRecommend 是否接收推荐的好友
	 */
	public void setFriendRight(VERIFYTYPE type,boolean isAutoAddFriend,boolean isCanBeSearched,boolean isRecommend) 
	{
		((SetFriendRightReq.Builder)mDataBuilder).setVerifyType(VERIFYTYPE.value(type));
		((SetFriendRightReq.Builder)mDataBuilder).setAutoAddFriend(isAutoAddFriend?1:0);
		((SetFriendRightReq.Builder)mDataBuilder).setSearchType(isCanBeSearched?1:0);
		((SetFriendRightReq.Builder)mDataBuilder).setRecommendType(isRecommend?1:0);
		super.buildAccessReq(((SetFriendRightReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetFriendRightReq.newBuilder();
	}
}
