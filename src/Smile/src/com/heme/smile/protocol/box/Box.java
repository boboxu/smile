package com.heme.smile.protocol.box;

import com.heme.smile.protocol.Token;
import com.heme.smile.protocol.runner.Runner;

import android.os.Handler;
 
public class Box extends Runner {
	protected Handler mListener;
	protected Token mToken;

	public Box(Handler listener, Object userData) {
		mListener = listener;
		mToken = new Token();
		mToken.mUserData = userData;
	}
	
	public static int runBox(Box box){
		return BoxManager.getInstance().startRunner(box);
	}
	
	public static void cancel(int runnerId){
		BoxManager.getInstance().stopRunner(runnerId);
	}
	
	public static void cancelAll() {
		BoxManager.getInstance().stopAll();
	}
	@Override
	public void stop() {
		mToken = null;
		mListener = null;
		super.stop();
	}
}
