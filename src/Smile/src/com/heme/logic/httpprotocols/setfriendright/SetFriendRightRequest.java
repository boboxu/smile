package com.heme.logic.httpprotocols.setfriendright;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.SetFriendRightReq;

public class SetFriendRightRequest extends BaseLoginedBusinessRequest {

	SetFriendRightReq.Builder mSetFriendRightBuilder;

	public SetFriendRightRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
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
		mSetFriendRightBuilder.setSessionId(sessionId);
		mSetFriendRightBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {

		mSetFriendRightBuilder.setVersionNo(version);
		mSetFriendRightBuilder.setClientType(clientType);

	}

	/***
	 * 
	 * @param type
	 *            别人加我的时候的是否需要验证
	 * @param isAutoAddFriend
	 *            回复陌生人是否自动加好友
	 * @param isCanBeSearched
	 *            是否允许被搜索到
	 * @param isRecommend
	 *            是否接收推荐的好友
	 */
	public void setFriendRight(VERIFYTYPE type, boolean isAutoAddFriend,
			boolean isCanBeSearched, boolean isRecommend) {
		mSetFriendRightBuilder.setVerifyType(VERIFYTYPE.value(type));
		mSetFriendRightBuilder.setAutoAddFriend(isAutoAddFriend ? 1 : 0);
		mSetFriendRightBuilder.setSearchType(isCanBeSearched ? 1 : 0);
		mSetFriendRightBuilder.setRecommendType(isRecommend ? 1 : 0);

		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetFriendRight);
		mDataSvrProtoBuilder
				.setSetFriendRightReqInfo(mSetFriendRightBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetFriendRightBuilder = SetFriendRightReq.newBuilder();
	}
}
