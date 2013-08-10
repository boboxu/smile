package com.heme.logic.httpprotocols.message.sendmsg.base;

import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.module.Message.ClassInfo;
import com.heme.logic.module.Message.NetGuardInfo;
import com.heme.logic.module.Message.PicMsgInfo;
import com.heme.logic.module.Message.VideoMsgInfo;
import com.heme.logic.module.Message.VoiceMsgInfo;
import com.heme.logic.module.Message.VoiceTestInfo;

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
	
	protected BaseMessageRequest(long srcId,String sessionId,List<Long> mTargetId,List<Long> mTargetGid,MSGTYPE msgType) {
		super(srcId,sessionId);
		mCommonMsgBuilder.setUint64FromUid(srcId);
		mCommonMsgBuilder.addAllUint64ToUid(mTargetId);
		mCommonMsgBuilder.addAllUint64ToGid(mTargetGid);
		mCommonMsgBuilder.setUint32MsgType(MSGTYPE.value(msgType));
		mCommonMsgBuilder.setUint64Time(System.currentTimeMillis());
	}
	
	public void setNetGuardMsgInfo(NetGuardInfo msgInfo,ByteString context) 
	{
		mCommonMsgBuilder.setMsgMoitorMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
	public void setVoiceTestInfo(VoiceTestInfo msgInfo,ByteString context) {
		mCommonMsgBuilder.setMsgTestMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
	protected void setClassInfo(ClassInfo msgInfo,ByteString context) {
		mCommonMsgBuilder.setMsgClassInfo(msgInfo);
		super.setCommonMsg(context);
	}
	
	public void setMsgContent(Object content,CONTENTTYPE type,ByteString context) {
		mCommonMsgBuilder.setUint32ContentType(CONTENTTYPE.value(type));
		switch (type) {
		case TYPETEXT:
			setTextMsgInfo((String)content, context);
			break;
		case TYPEPIC:
			setPicMsgInfo((PicMsgInfo)content, context);
			break;
		case TYPEVIDEO:
			setVideoMsgInfo((VideoMsgInfo)content, context);
			break;
		case TYPEVOICE:
			setVoiceMsgInfo((VoiceMsgInfo)content, context);
			break;
		default:
			break;
		}
	}
	
	private void setPicMsgInfo(PicMsgInfo msgInfo,ByteString context) {
		mCommonMsgBuilder.setMsgPicMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
	private void setTextMsgInfo(String text,ByteString context) {
		mCommonMsgBuilder.setStrTextMsg(text);
		super.setCommonMsg(context);
	}
	
	private void setVoiceMsgInfo(VoiceMsgInfo msgInfo,ByteString context) {
		mCommonMsgBuilder.setMsgVoiceMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
	private void setVideoMsgInfo(VideoMsgInfo msgInfo,ByteString context) {
		mCommonMsgBuilder.setMsgVideoMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
	public CONTENTTYPE getContentType()
	{
		return CONTENTTYPE.values()[mCommonMsgBuilder.getUint32ContentType()];
	}
}
