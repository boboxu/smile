package com.heme.logic.managers.pushmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.LogicManager;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest.COMMANDTYPE;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest.MSGTYPE;
import com.heme.logic.httpprotocols.push.PushMsgRequest;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Message.CommonMsg;
seBusinessLogicManager;

public class PushManager extends BaseBusinessLogicManager {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	//收到MessageOpr，根据uint32_command命令号来判断交给哪个request来解析数据。
	//命令号 1.发送请求 2.发送确认 3.推送请求 4.推送确认 5.拉取请求 6.拉取结果 7.拉取未读列表请求 8.拉取未读列表结果
//	BaseMessageOprRequest.COMMANDTYPE
	
	public void onRecivedPushMsg(PushMsgRequest msgRequest)
	{
		//确认是推送消息，并且是推到当前用户的
		if (msgRequest.getCmdType() == COMMANDTYPE.TYPEPUSHREQ && 
				msgRequest.getToId() == LogicManager.accountManager().getCurrentAccoutSystemId()) {
			List<CommonMsg> msgList = msgRequest.getMsgList();
			//存数据库咯
			for (int i = 0; i < msgList.size(); i++) {
				CommonMsg msgItem = msgList.get(i);
				MSGTYPE msgtype = BaseMessageOprRequest.MSGTYPE.values()[msgItem.getUint32MsgType()];
				switch (msgtype) {
				case TYPEALL://这个在推送的消息里面肯定不会有
					break;
				case TYPEC2C://个人消息
					break;
				case TYPEGROUP://群消息
					break;
				case TYPESYSTEM://社区消息
					break;
				case TYPESOCIALGROUP://
					break;
				case TYPENOTICE:
					break;
				case TYPECLASSASSIS:
					break;
				case TYPEVOICETEST:
					break;
				case TYPENETGUARD:
					break;
				default:
					break;
				}
			}
			//汇报UI
		}
	}
}
