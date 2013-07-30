package com.heme.logic.managers.accountmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.httpprotocols.setfriendright.SetFriendRightRequest.VERIFYTYPE;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.LoginRsp;

public class AccountManager extends BaseBusinessLogicManager implements
		IAccountManagerInterface {
	private static final String DATAPATH = "/mnt/sdcard/smile/accountinfo";
	LoginRsp mLoginRsp = null;

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserIcon(String iconName, Handler handler) {

	}

	@Override
	public void updateSignature(String signature, Handler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFriendRight(VERIFYTYPE type, boolean isAutoAddFriend,
			boolean isCanBeSearched, boolean isRecommend, Handler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserInfo(SEXTYPE type, String birthday, String job,
			String local, String email, String interest, Handler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getCurrentAccoutSystemId() {
		if (mLoginRsp == null) 
		{
			mLoginRsp = readFromFile(DATAPATH);
		}
		return mLoginRsp.getSystemId();
	}

	@Override
	public void removeCurrentAccount() {

	}

	@Override
	public void setCurrentAccount(LoginRsp loginrsp) {
		if (loginrsp == null) {
			LoginRsp.Builder logRspbuilder = LoginRsp.newBuilder();
			logRspbuilder.setErrCode(0);
			logRspbuilder.setErrString("");
			logRspbuilder.setSystemId(123);
			logRspbuilder.setSessionId("1234");
			logRspbuilder.addFriendSystemId(321);
			logRspbuilder.addGroupId(12);
			loginrsp = logRspbuilder.build();
		}
		
		writeToFile(DATAPATH, loginrsp);
	}

	private void writeToFile(String datapath, LoginRsp logRsp) {
		// 暂时用写文件来测试
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(datapath);
			output.write(logRsp.toByteArray());
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}
	
	private LoginRsp readFromFile(String datapath)
	{
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(
					datapath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fs != null) 
		{
			try {
				return LoginRsp.parseFrom(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else {
			return null;
		}
	}

	@Override
	public String getCurrentSessionId() {
		if (mLoginRsp == null) 
		{
			mLoginRsp = readFromFile(DATAPATH);
		}
		return mLoginRsp.getSessionId();
	}
}
