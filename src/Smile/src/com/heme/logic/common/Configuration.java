package com.heme.logic.common;


public class Configuration {
	enum APPVERSION
	{
		PARENT,
		STUDENT,
		TEACHER;
		public static int value(APPVERSION version)
		{
			switch (version) {
			case PARENT:
				return 1;
			case STUDENT:
				return 2;
			case TEACHER:
				return 3;
			default:
				return 1;
			}
		}
	}
	//app应用类型 1家长版 2学生版 3老师版
	public static final int APP_VERSION = APPVERSION.value(APPVERSION.PARENT);
	public static final String PROTO_VERSION = "1";

}
