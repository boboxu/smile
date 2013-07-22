package com.heme.smile.protocol.runner;


public abstract class Future implements IFuture {
	
	protected boolean mCancel;
	
	public abstract void run();
	
	public void cancel() {
		mCancel = true;
	}
	
	public boolean isCanceled() {
		return mCancel;
	}
}
