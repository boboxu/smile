package com.heme.smile.protocol.runner;

public class RunnerInfo {
	public static final int STATUS_UNSPCIFIED = -1;
	public static final int STATUS_WAITING = 0;
	public static final int STATUS_RUNNING = STATUS_WAITING + 1;
	public static final int STATUS_STOPED = STATUS_RUNNING + 1;
	public static final int STATUS_SUCCEED = STATUS_STOPED + 1;
	public static final int STATUS_FAILED = STATUS_SUCCEED + 1;
	
	public int mStatus;
	public int mId;
}
