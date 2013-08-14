package com.heme.logic.httpprotocols.message.sendmsg.classassis;

import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.ClassInfo;
import com.heme.logic.module.Message.MessageType;

//这个请求手机端应该不会有
public class SendClassMsgRequest extends BaseMessageRequest{
	
	//目前需求，不支持客户端发送
	protected SendClassMsgRequest(long srcId, String sessionId,
			List<Long> mTargetGid) {
		super(srcId, sessionId, null, mTargetGid, MessageType.MT_ClassInfo);
	}
	
	protected void setClassInfo(ClassInfo msgInfo,ByteString context) {
		mCommonMsgBuilder.setMsgClassInfo(msgInfo);
		super.setCommonMsg(context);
	}
	
}
