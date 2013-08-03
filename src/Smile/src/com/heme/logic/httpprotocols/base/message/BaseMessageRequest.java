package com.heme.logic.httpprotocols.base.message;

import java.util.List;

import com.heme.logic.module.Message.CommonMsg;
import com.heme.logic.module.Message.NetGuardInfo;

public abstract class BaseMessageRequest extends BaseSendMsgRequest {

	public enum CONTENTTYPE {
		TYPETEXT, TYPEPIC, TYPEVOICE, TYPEVIDEO;
		public static int value(CONTENTTYPE type) {
			switch (type) {
			case TYPETEXT:
				return 0;
			case TYPEPIC:
				return 1;
			case TYPEVOICE:
				return 2;
			case TYPEVIDEO:
				return 3;
			default:
				return 1;
			}
		}
	}

	private CommonMsg.Builder mCommonMsgBuilder;
//	private long mSrcID;
//	private List<Long> mTargetId;
//	private List<Long> mTargetGid;
//	private int mMsgType;
//	private int mContentType;
	
	protected BaseMessageRequest(long srcId,int sessionId,List<Long> mTargetId,List<Long> mTargetGid,MSGTYPE msgType,CONTENTTYPE contentType) {
		super(srcId,sessionId);
		mCommonMsgBuilder = CommonMsg.newBuilder();
		mCommonMsgBuilder.setUint64FromUid(srcId);
		mCommonMsgBuilder.addAllUint64ToUid(mTargetId);
		mCommonMsgBuilder.addAllUint64ToGid(mTargetGid);
		mCommonMsgBuilder.setUint32MsgType(MSGTYPE.value(msgType));
		mCommonMsgBuilder.setUint32ContentType(CONTENTTYPE.value(contentType));
	}
	
	protected void buildNetGuardMsgInfo(NetGuardInfo msgInfo) 
	{
		
	}
}
