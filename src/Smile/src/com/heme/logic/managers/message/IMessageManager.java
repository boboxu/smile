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
	/***
	 * 
	 * @param targetId
	 * @param picinfo
	 * @param handler
	 */
	public void sendPicMsgToUser(long targetId,PicMsgInfo picinfo,Handler handler);
	
	/***
	 * 
	 * @param targetId
	 * @param voiceinfo
	 * @param handler
	 */
	public void sendVoiceMsgToUser(long targetId,VoiceMsgInfo voiceinfo,Handler handler);
	
	/***
	 * 
	 * @param targetId
	 * @param videoinfo
	 * @param handler
	 */
	public void sendVideoMsgToUser(long targetId,VideoMsgInfo videoinfo,Handler handler);
	
	/***
	 * 
	 * @param targetId
	 * @param textinfo
	 * @param handler
	 */
	public void sendTextMsgToUser(long targetId,String textinfo,Handler handler);
	
	//SendMsg c2g
	/***
	 * 
	 * @param groupId
	 * @param picinfo
	 * @param handler
	 */
	public void sendPicMsgToGroup(long groupId,PicMsgInfo picinfo,Handler handler);
	
	/***
	 * 
	 * @param groupId
	 * @param picinfo
	 * @param handler
	 */
	public void sendVoiceMsgToGroup(long groupId,VoiceMsgInfo voiceinfo,Handler handler);
	
	/***
	 * 
	 * @param groupId
	 * @param picinfo
	 * @param handler
	 */
	public void sendVideoMsgToGroup(long groupId,VideoMsgInfo videoinfo,Handler handler);
	
	/***
	 * 
	 * @param groupId
	 * @param picinfo
	 * @param handler
	 */
	public void sendTextMsgToGroup(long groupId,String textinfo,Handler handler);
	
	//SendMsg greennet
	/***
	 * 
	 * @param 收到警告的时候，会带来一个fromId，UI保存下来这里传入就好
	 * @param msgInfo
	 * @param handler
	 */
	public void sendGreenNetCommand(long targetId,NetGuardInfo msgInfo,Handler handler);

	//SendMsg voicetest
	public void sendVoiceTestMsgToUser(long userId,VoiceTestInfo testinfo,Handler handler);
	public void sendVoiceTestMsgToGroup(long groupId,VoiceTestInfo testinfo,Handler handler);
	
	//PollMsg 拉取未读c2c
	/***
	 * 拉取指定用户的未读消息
	 * @param fromId
	 */
	public void pollUserMsg(long fromId,Handler handler);
	
	/***
	 * 拉取全部的未读消息
	 * @param handler
	 */
	public void pollAllUserMsg(Handler handler);
	
	//PollMsg 拉取未读c2g
	/***
	 * 拉取指定群的未读消息
	 * @param fromGid
	 * @param handler
	 */
	public void pollGroupMsg(long fromGid,Handler handler);
	
	/***
	 * 拉取所有的未读消息
	 * @param handler
	 */
	public void pollAllGroupMsg(Handler handler);
	
	//PollMsg 拉取未读系统消息
	public void pollSystemInfo(Handler handler);
	
	//PollMsg 拉取未读社区群组消息
	public void pollSocialGroupInfo(Handler handler);
	
	//PollMsg 拉取未读公告
	public void pollNoticeInfo(Handler handler);
	
	//PollMsg 拉取未读课堂信息助手
	public void pollClassAssisInfo(Handler handler);
	
	//PollMsg 拉取未读语音测评助手
	public void pollVoiceTestInfo(Handler handler);
	
	//PollMsg 拉取未读绿色上网助手
	public void pollNetGuardInfo(Handler handler);
	
	//PollUnReadMsg 上线的时候
	public void PollUnReadInfo(Handler handler);
}
