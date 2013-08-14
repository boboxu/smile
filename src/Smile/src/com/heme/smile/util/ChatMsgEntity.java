package com.heme.smile.util;

public class ChatMsgEntity {
	private String name;//消息来自
	private String date;//消息日期
	private String message;//消息内容
	private boolean isComMeg = true;// 是否为收到的消息
	private int type =0;  //0 代表文字信息，1为语音,2为图片，3为视频文件 4为名片
	private int voiceTime = 0;  //录音时长
	private String externalContent = "";   //额外的内容，
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getMsgType() {
		return isComMeg;
	}

	public void setMsgType(boolean isComMsg) {
		isComMeg = isComMsg;
	}

	public ChatMsgEntity() {
	}

	public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
		super();
		this.name = name;
		this.date = date;
		this.message = text;
		this.isComMeg = isComMsg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVoiceTime() {
		return voiceTime;
	}

	public void setVoiceTime(float voiceTime) {
		this.voiceTime = Math.round(voiceTime);
	}

	public String getExternalContent() {
		return externalContent;
	}

	public void setExternalContent(String externalContent) {
		this.externalContent = externalContent;
	}

}
