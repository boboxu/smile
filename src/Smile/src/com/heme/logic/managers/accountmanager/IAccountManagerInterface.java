package com.heme.logic.managers.accountmanager;

import android.os.Handler;

import com.heme.logic.httpprotocols.setfriendright.SetFriendRightRequest.VERIFYTYPE;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Data.LoginRsp;

//处理当前登陆账号的信息
public interface IAccountManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	
	//需要网络调用的接口
	public void updateUserIcon(String iconName,Handler handler);
	public void updateSignature(String signature,Handler handler);
	public void updateFriendRight(VERIFYTYPE type,boolean isAutoAddFriend,boolean isCanBeSearched,boolean isRecommend,Handler handler);
	public void updateUserInfo(SEXTYPE type,String birthday,String job,String local,String email,String interest,Handler handler);

	//本地数据库的接口
	public long getCurrentAccoutSystemId();
	public String getCurrentSessionId();
	public void removeCurrentAccount();  
	public void setCurrentAccount(LoginRsp loginrsp);
}
