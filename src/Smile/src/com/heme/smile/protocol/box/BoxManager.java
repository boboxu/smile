package com.heme.smile.protocol.box;

import com.heme.smile.protocol.runner.RunnerManager;


public class BoxManager extends RunnerManager {
	private static BoxManager mInstance = null;
	private BoxManager() { 
		
	}
	public static BoxManager getInstance() {
		if(null == mInstance) {
			mInstance = new BoxManager();
		}
		return mInstance;
	}
}
