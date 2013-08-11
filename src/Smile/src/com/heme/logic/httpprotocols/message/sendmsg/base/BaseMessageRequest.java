package com.heme.logic.httpprotocols.message.sendmsg.base;

import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.module.Message.ClassInfo;
import com.heme.logic.module.Message.ContentType;
import com.heme.logic.module.Message.FileInfo;
import com.heme.logic.module.Message.MessageType;
import com.heme.logic.module.Message.NetGuardInfo;
import com.heme.logic.module.Message.PicMsgInfo;
import com.heme.logic.module.Message.VideoMsgInfo;
import com.heme.logic.module.Message.VoiceMsgInfo;
import com.heme.logic.module.Message.VoiceTestInfo;

public abstract class BaseMessageRequest extends BaseSendMsgRequest {

	protected BaseMessageRequest(long srcId,String sessionId,List<Long> mTargetId,List<Long> mTargetGid,MessageType msgType) {
		super(srcId,sessionId);
		mCommonMsgBuilder.setUint64FromUid(srcId);
		mCommonMsgBuilder.addAllUint64ToUid(mTargetId);
		mCommonMsgBuilder.addAllUint64ToGid(mTargetGid);
		mCommonMsgBuilder.setUint32MsgType(msgType);
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
	
	public void setMsgContent(Object content,ContentType type,ByteString context) {
		mCommonMsgBuilder.setUint32ContentType(type);
		switch (type) {
		case CT_Text:
			setTextMsgInfo((String)content, context);
			break;
		case CT_Picture:
			setPicMsgInfo((PicMsgInfo)content, context);
			break;
		case CT_Video:
			setVideoMsgInfo((VideoMsgInfo)content, context);
			break;
		case CT_Voice:
			setVoiceMsgInfo((VoiceMsgInfo)content, context);
			break;
		case CT_File:
			setFileMsgInfo((FileInfo)content,context);
			break;
		case CT_IDCard:
			setIdCardInfo((String)content, context);
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
	
	private void setFileMsgInfo(FileInfo msgInfo,ByteString context)
	{
		mCommonMsgBuilder.setMsgFileInfo(msgInfo);
		super.setCommonMsg(context);
	}
	
	private void setIdCardInfo(String msgInfo,ByteString context)
	{
		mCommonMsgBuilder.setStrTextMsg(msgInfo);
		super.setCommonMsg(context);
	}
	
	public ContentType getContentType()
	{
		return mCommonMsgBuilder.getUint32ContentType();
	}
}
