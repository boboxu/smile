package com.heme.logic.managers.message;

import java.util.ArrayList;

import android.os.Handler;

import com.google.protobuf.ByteString;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.base.message.BaseMessageOprRequest.MSGTYPE;
import com.heme.logic.httpprotocols.message.pollmsg.PollMessageRequest;
import com.heme.logic.httpprotocols.message.pullunreadmsg.PollUnreadMessageRequest;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest.CONTENTTYPE;
import com.heme.logic.httpprotocols.message.sendmsg.c2c.SendUserMsgRequest;
import com.heme.logic.httpprotocols.message.sendmsg.c2g.SendGroupMsgRequest;
import com.heme.logic.httpprotocols.message.sendmsg.greennet.SendCommandRequest;
import com.heme.logic.httpprotocols.message.sendmsg.voicetest.SendVoiceTestMsgRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Message.NetGuardInfo;
import com.heme.logic.module.Message.PicMsgInfo;
import com.heme.logic.module.Message.VideoMsgInfo;
import com.heme.logic.module.Message.VoiceMsgInfo;
import com.heme.logic.module.Message.VoiceTestInfo;

public class MessageManager extends BaseBusinessLogicManager implements
		IMessageManager {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
//		handleresponse(msgWhat, msgObj, handler)
	}

	@Override
	public void sendPicMsgToUser(long targetId, PicMsgInfo picinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendUserMsgRequest request = new SendUserMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setMsgContent(picinfo, CONTENTTYPE.TYPEPIC, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendVoiceMsgToUser(long targetId, VoiceMsgInfo voiceinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendUserMsgRequest request = new SendUserMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setMsgContent(voiceinfo, CONTENTTYPE.TYPEVOICE,
				ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendVideoMsgToUser(long targetId, VideoMsgInfo videoinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendUserMsgRequest request = new SendUserMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setMsgContent(videoinfo, CONTENTTYPE.TYPEVIDEO,
				ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendTextMsgToUser(long targetId, String textinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendUserMsgRequest request = new SendUserMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setMsgContent(textinfo, CONTENTTYPE.TYPETEXT, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendPicMsgToGroup(long groupId, PicMsgInfo picinfo,
			Handler handler) {
		ArrayList<Long> targetGidArrayList = new ArrayList<Long>();
		targetGidArrayList.add(Long.valueOf(groupId));
		SendGroupMsgRequest request = new SendGroupMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetGidArrayList);
		request.setMsgContent(picinfo, CONTENTTYPE.TYPEPIC, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendVoiceMsgToGroup(long groupId, VoiceMsgInfo voiceinfo,
			Handler handler) {
		ArrayList<Long> targetGidArrayList = new ArrayList<Long>();
		targetGidArrayList.add(Long.valueOf(groupId));
		SendGroupMsgRequest request = new SendGroupMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetGidArrayList);
		request.setMsgContent(voiceinfo, CONTENTTYPE.TYPEVOICE,
				ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendVideoMsgToGroup(long groupId, VideoMsgInfo videoinfo,
			Handler handler) {
		ArrayList<Long> targetGidArrayList = new ArrayList<Long>();
		targetGidArrayList.add(Long.valueOf(groupId));
		SendGroupMsgRequest request = new SendGroupMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetGidArrayList);
		request.setMsgContent(videoinfo, CONTENTTYPE.TYPEVIDEO,
				ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void sendTextMsgToGroup(long groupId, String textinfo,
			Handler handler) {
		ArrayList<Long> targetGidArrayList = new ArrayList<Long>();
		targetGidArrayList.add(Long.valueOf(groupId));
		SendGroupMsgRequest request = new SendGroupMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetGidArrayList);
		request.setMsgContent(textinfo, CONTENTTYPE.TYPETEXT, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendGreenNetCommand(long targetId, NetGuardInfo msgInfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendCommandRequest request = new SendCommandRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setNetGuardMsgInfo(msgInfo, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendVoiceTestMsgToUser(long userId, VoiceTestInfo testinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(userId));
		SendVoiceTestMsgRequest request = new SendVoiceTestMsgRequest(
				LogicManager.accountManager().getCurrentAccoutSystemId(),
				LogicManager.accountManager().getCurrentSessionId(),
				targetIdArrayList, new ArrayList<Long>());
		request.setVoiceTestInfo(testinfo, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendVoiceTestMsgToGroup(long groupId, VoiceTestInfo testinfo,
			Handler handler) {
		ArrayList<Long> targetGidArrayList = new ArrayList<Long>();
		targetGidArrayList.add(Long.valueOf(groupId));
		SendVoiceTestMsgRequest request = new SendVoiceTestMsgRequest(
				LogicManager.accountManager().getCurrentAccoutSystemId(),
				LogicManager.accountManager().getCurrentSessionId(),
				new ArrayList<Long>(), targetGidArrayList);
		request.setVoiceTestInfo(testinfo, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollUserMsg(long fromId, Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPEC2C);
		request.setPollMsgFromUid(fromId, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollAllUserMsg(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPEC2C);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void pollGroupMsg(long fromGid, Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPEGROUP);
		request.setPollMsgFromGid(fromGid, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void pollAllGroupMsg(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPEGROUP);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void pollSystemInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPESYSTEM);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollSocialGroupInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(),
				MSGTYPE.TYPESOCIALGROUP);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollNoticeInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPENOTICE);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollClassAssisInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPECLASSASSIS);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollVoiceTestInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPEVOICETEST);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollNetGuardInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MSGTYPE.TYPENETGUARD);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void PollUnReadInfo(Handler handler) {
		PollUnreadMessageRequest request = new PollUnreadMessageRequest(
				LogicManager.accountManager().getCurrentAccoutSystemId(),
				LogicManager.accountManager().getCurrentSessionId());
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

}
