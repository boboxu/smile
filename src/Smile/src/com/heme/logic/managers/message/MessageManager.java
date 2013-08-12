package com.heme.logic.managers.message;

import java.util.ArrayList;

import android.os.Handler;

import com.google.protobuf.ByteString;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.message.pollmsg.PollMessageRequest;
import com.heme.logic.httpprotocols.message.pollmsg.PollMessageResponse;
import com.heme.logic.httpprotocols.message.pullunreadmsg.PollUnreadMessageRequest;
import com.heme.logic.httpprotocols.message.resptosvr.RespToSvrRequest;
import com.heme.logic.httpprotocols.message.sendmsg.c2c.SendUserMsgRequest;
import com.heme.logic.httpprotocols.message.sendmsg.c2c.SendUserMsgResponse;
import com.heme.logic.httpprotocols.message.sendmsg.c2g.SendGroupMsgRequest;
import com.heme.logic.httpprotocols.message.sendmsg.greennet.SendCommandRequest;
import com.heme.logic.httpprotocols.message.sendmsg.voicetest.SendVoiceTestMsgRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Message.ContentType;
import com.heme.logic.module.Message.MessageType;
import com.heme.logic.module.Message.NetGuardInfo;
import com.heme.logic.module.Message.PicMsgInfo;
import com.heme.logic.module.Message.VideoMsgInfo;
import com.heme.logic.module.Message.VoiceMsgInfo;
import com.heme.logic.module.Message.VoiceTestInfo;

public class MessageManager extends BaseBusinessLogicManager implements
		IMessageManager {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof SendUserMsgResponse) 
		{
			SendUserMsgResponse sumResponse = (SendUserMsgResponse)response;
			SendUserMsgRequest sumRequest = (SendUserMsgRequest)sumResponse.getmRequest();
			switch (sumRequest.getContentType()) {
			case CT_Text:
				handleresponse(sumResponse.getSendMsgRes().getUint32Result() == 0?Constans.SEND_TEXT_C2C_SUCCESS:Constans.SEND_TEXT_C2C_FAILED, sumResponse.getSendMsgRes(), handler);
				break;
			case CT_Picture:
				handleresponse(sumResponse.getSendMsgRes().getUint32Result() == 0?Constans.SEND_PIC_C2C_SUCCESS:Constans.SEND_PIC_C2C_FAILED, sumResponse.getSendMsgRes(), handler);
				break;
			case CT_Video:
				handleresponse(sumResponse.getSendMsgRes().getUint32Result() == 0?Constans.SEND_VIDEO_C2C_SUCCESS:Constans.SEND_VIDEO_C2C_FAILED, sumResponse.getSendMsgRes(), handler);
				break;
			case CT_Voice:
				handleresponse(sumResponse.getSendMsgRes().getUint32Result() == 0?Constans.SEND_VOICE_C2C_SUCCESS:Constans.SEND_VOICE_C2C_FAILED, sumResponse.getSendMsgRes(), handler);
				break;
			case CT_IDCard:
				handleresponse(sumResponse.getSendMsgRes().getUint32Result() == 0?Constans.SEND_IDCARD_C2C_SUCCESS:Constans.SEND_IDCARD_C2C_FAILED, sumResponse.getSendMsgRes(), handler);
			default:
				break;
			}
		}
		else if (response instanceof PollMessageResponse) {
			PollMessageResponse pmResponse = (PollMessageResponse)response;
			handleresponse(Constans.POLL_C2C_SUCCESS, pmResponse.getPollMsgRes(), handler);
			sendRespToSvr(pmResponse.getmMessageOpr().getBytesContext());
		}
	}

	@Override
	public void sendPicMsgToUser(long targetId, PicMsgInfo picinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendUserMsgRequest request = new SendUserMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setMsgContent(picinfo, ContentType.CT_Picture, ByteString.EMPTY);
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
		request.setMsgContent(voiceinfo, ContentType.CT_Voice,
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
		request.setMsgContent(videoinfo, ContentType.CT_Video,
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
		request.setMsgContent(textinfo, ContentType.CT_Text, ByteString.EMPTY);
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
		request.setMsgContent(picinfo, ContentType.CT_Picture, ByteString.EMPTY);
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
		request.setMsgContent(voiceinfo,ContentType.CT_Voice,
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
		request.setMsgContent(videoinfo,ContentType.CT_Video,
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
		request.setMsgContent(textinfo,ContentType.CT_Text, ByteString.EMPTY);
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
				.accountManager().getCurrentSessionId(),  MessageType.MT_C2C);
		request.setPollMsgFromUid(fromId, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollAllUserMsg(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MessageType.MT_C2C);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void pollGroupMsg(long fromGid, Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(),MessageType.MT_Group);
		request.setPollMsgFromGid(fromGid, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void pollAllGroupMsg(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MessageType.MT_Group);
		sendRequest(request, handler, getClass().getName(), _FUNC_());

	}

	@Override
	public void pollSystemInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(),MessageType.MT_System);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollSocialGroupInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(),
				MessageType.MT_Group);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollNoticeInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MessageType.MT_Broadcast);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollClassAssisInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MessageType.MT_ClassInfo);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollVoiceTestInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MessageType.MT_VoiceTest);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void pollNetGuardInfo(Handler handler) {
		PollMessageRequest request = new PollMessageRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), MessageType.MT_NetGuard);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void PollUnReadInfo(Handler handler) {
		PollUnreadMessageRequest request = new PollUnreadMessageRequest(
				LogicManager.accountManager().getCurrentAccoutSystemId(),
				LogicManager.accountManager().getCurrentSessionId());
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendIdCardToUser(long targetId, String stridinfo,
			Handler handler) {
		ArrayList<Long> targetIdArrayList = new ArrayList<Long>();
		targetIdArrayList.add(Long.valueOf(targetId));
		SendUserMsgRequest request = new SendUserMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetIdArrayList);
		request.setMsgContent(stridinfo, ContentType.CT_IDCard, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void sendIdCardToGroup(long groupId, String stridinfo,
			Handler handler) {
		ArrayList<Long> targetGidArrayList = new ArrayList<Long>();
		targetGidArrayList.add(Long.valueOf(groupId));
		SendGroupMsgRequest request = new SendGroupMsgRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), targetGidArrayList);
		request.setMsgContent(stridinfo, ContentType.CT_IDCard, ByteString.EMPTY);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	private void sendRespToSvr(ByteString context)
	{
		RespToSvrRequest request = new RespToSvrRequest(LogicManager
				.accountManager().getCurrentAccoutSystemId(), LogicManager
				.accountManager().getCurrentSessionId(), context);
		sendRequest(request, null, getClass().getName(), _FUNC_());
	}
	
}
