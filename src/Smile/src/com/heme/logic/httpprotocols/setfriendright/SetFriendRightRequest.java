package com.heme.logic.httpprotocols.setfriendright;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetFriendRightReq;

public class SetFriendRightRequest extends BaseLoginedBusinessRequest {
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
	
	SetFriendRightReq.Builder mSetFriendRightReqBuilder = SetFriendRightReq
			.newBuilder();

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetFriendRightReqBuilder.setSessionId(sessionId);
		mSetFriendRightReqBuilder.setSystemId(systemId);
		super.setBody(mSetFriendRightReqBuilder.build().toByteString());

	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {

		mSetFriendRightReqBuilder.setVersionNo(version);
		mSetFriendRightReqBuilder.setClientType(clientType);
		super.setBody(mSetFriendRightReqBuilder.build().toByteString());

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
		mSetFriendRightReqBuilder.setVerifyType(VERIFYTYPE.value(type));
		mSetFriendRightReqBuilder.setAutoAddFriend(isAutoAddFriend?1:0);
		mSetFriendRightReqBuilder.setSearchType(isCanBeSearched?1:0);
		mSetFriendRightReqBuilder.setRecommendType(isRecommend?1:0);
		super.setBody(mSetFriendRightReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
