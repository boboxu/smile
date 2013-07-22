package com.heme.smile.protocol.runner;

public interface IRunner {
	public interface IRunnerStatusChangedListener {
		public void onChange(int curStatus, int regionStatus, IRunner runner);
	}
	
	public void start();
	public void stop();
	public void pause();
	public void resume();
	public int getStatus();
	public void setOnStatusChangedListener(IRunnerStatusChangedListener listener);
}
