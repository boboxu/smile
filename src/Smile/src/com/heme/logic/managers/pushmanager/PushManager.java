package com.heme.logic.managers.pushmanager;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.dao.DbManager;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.commonlogic.servermanager.IServerManagerPushListener;
import com.heme.commonlogic.servermanager.ServerManager;
import com.heme.logic.LogicManager;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Message.CommonMsg;
import com.heme.logic.module.Message.MessageOpr;
import com.heme.logic.module.Message.MessageType;

public class PushManager extends BaseBusinessLogicManager implements IServerManagerPushListener{

	protected static PushManager gManager = null;
	
	List<CommonMsg> unreadMsgList = new ArrayList<CommonMsg>();
	
	public static PushManager shareManager()
	{
		if (gManager == null)
		{
			gManager = new PushManager();
			ServerManager.shareInstance().setmPushListener(gManager);
		}
		return gManager;
	}
	
	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	//收到MessageOpr，根据uint32_command命令号来判断交给哪个request来解析数据。
	//命令号 1.发送请求 2.发送确认 3.推送请求 4.推送确认 5.拉取请求 6.拉取结果 7.拉取未读列表请求 8.拉取未读列表结果
//	BaseMessageOprRequest.COMMANDTYPE
	@Override
	public void onRecivedPushMsg(MessageOpr msgOpr)
	{
		//确认是推到当前用户的
		if (msgOpr.getUint64Uid() == LogicManager.accountManager().getCurrentAccoutSystemId()) {
			List<CommonMsg> msgList = msgOpr.getMsgPushReq().getRptMsgPushmsgList();
			if (msgList == null || msgList.size() == 0)
			{
				return;
			}
			
			//放未读列表
			unreadMsgList.addAll(msgList);
			
			//存数据库咯
			DbManager.getCommonMsgDao().insertOrReplaceInTx(msgList);

			for (int i = 0; i < msgList.size(); i++) {
				CommonMsg msgItem = msgList.get(i);
				MessageType msgtype = msgItem.getUint32MsgType();
				switch (msgtype) {
				case MT_All://这个在推送的消息里面肯定不会有
					break;
				case MT_C2C://个人消息
					break;
				case MT_Group://群消息
					break;
				case MT_System://社区消息
					break;
				case MT_Community://
					break;
				case MT_Broadcast:
					break;
				case MT_ClassInfo:
					break;
				case MT_VoiceTest:
					break;
				case MT_NetGuard:
					break;
				default:
					break;
				}
			}
			//汇报UI
		}
	}
}
