package com.heme.logic.managers.message;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Message.NetGuardInfo;
import com.heme.logic.module.Message.PicMsgInfo;
import com.heme.logic.module.Message.VideoMsgInfo;
import com.heme.logic.module.Message.VoiceMsgInfo;
import com.heme.logic.module.Message.VoiceTestInfo;

public interface IMessageManager extends IBaseBusinessLogicManagerInterface {
	//SendMsg c2c
	public void sendPicMsgToUser(long targetId,PicMsgInfo picinfo,Handler handler);
	public void sendVoiceMsgToUser(long targetId,VoiceMsgInfo voiceinfo,Handler handler);
	public void sendVideoMsgToUser(long targetId,VideoMsgInfo videoinfo,Handler handler);
	public void sendTextMsgToUser(long targetId,String textinfo,Handler handler);
	
	//SendMsg c2g
	public void sendPicMsgToGroup(long groupId,PicMsgInfo picinfo,Handler handler);
	public void sendVoiceMsgToGroup(long groupId,VoiceMsgInfo voiceinfo,Handler handler);
	public void sendVideoMsgToGroup(long groupId,VideoMsgInfo videoinfo,Handler handler);
	public void sendTextMsgToGroup(long groupId,String textinfo,Handler handler);
	
	//greennet
	/***
	 * 
	 * @param 收到警告的时候，会带来一个fromId，UI保存下来这里传入就好
	 * @param msgInfo
	 * @param handler
	 */
	public void sendGreenNetCommand(long targetId,NetGuardInfo msgInfo,Handler handler);
	//socialgroup
	public void sendPicMsgToSocialGroup(long groupId,PicMsgInfo picinfo,Handler handler);
	public void sendVoiceMsgToSocialGroup(long groupId,VoiceMsgInfo voiceinfo,Handler handler);
	public void sendVideoMsgToSocialGroup(long groupId,VideoMsgInfo videoinfo,Handler handler);
	public void sendTextMsgToSocialGroup(long groupId,String textinfo,Handler handler);
	
	//voicetest
	public void sendVoiceTestMsgToUser(long userId,VoiceTestInfo testinfo,Handler handler);
	public void sendVoiceTestMsgToGroup(long groupId,VoiceTestInfo testinfo,Handler handler);
	
	
}
