package com.heme.logic.common;

public class Constans {
	/***
	 * accountmanager
	 */

	/***
	 * loginmanager
	 */
	// 登陆成功
	public static final int LOGIN_SUCCESS = 0;
	// 登陆失败
	public static final int LOGIN_FAILED = LOGIN_SUCCESS + 1;
	// 服务器验证用户输入的验证码正确
	public static final int VERIFY_CHECKCODE_SUCCESS = LOGIN_FAILED + 1;
	// 验证码验证错误
	public static final int VERIFY_CHECKCODE_FAILED = VERIFY_CHECKCODE_SUCCESS + 1;

	/***
	 * registmanager
	 */
	// 家长注册成功
	public static final int ADULT_REG_SUCCESS = VERIFY_CHECKCODE_FAILED + 1;
	// 家长注册失败
	public static final int ADULT_REG_FAILED = ADULT_REG_SUCCESS + 1;
	// 学生注册成功
	public static final int STUDENT_REG_SUCCESS = ADULT_REG_FAILED + 1;
	// 学生注册失败
	public static final int STUDENT_REG_FAILED = STUDENT_REG_SUCCESS + 1;

	// 用户注册时填写手机号，服务器给该手机号发送验证码成功的回调
	public static final int SEND_REG_CHECK_CODE_SUCCESS = STUDENT_REG_FAILED + 1;
	// 发送验证码失败的回调
	public static final int SEND_REG_CHECK_CODE_FAILED = SEND_REG_CHECK_CODE_SUCCESS + 1;
	// 输入验证码界面倒计时
	public static final int INPUT_CHECKCODE_COUNT_DOWN = SEND_REG_CHECK_CODE_FAILED + 1;
	// 清空聊天记录成功
	public static final int CLEAR_CHAT_HISTORY_SUCCESS = INPUT_CHECKCODE_COUNT_DOWN + 1;
	// 清空聊天记录失败
	public static final int CLEAR_CHAT_HISTORY_FAILED = CLEAR_CHAT_HISTORY_SUCCESS + 1;
	// 修改密码成功
	public static final int UPDATE_PWD_SUCCESS = CLEAR_CHAT_HISTORY_FAILED + 1;
	// 修改密码失败
	public static final int UPDATE_PWD_FAILED = UPDATE_PWD_SUCCESS + 1;
	/***
	 * feedbackmanager
	 */
	// 反馈意见提交成功
	public static final int SUBMIT_FEEDBACK_SUCCESS = UPDATE_PWD_FAILED + 1;
	// 反馈意见提交失败
	public static final int SUBMIT_FEEDBACK_FAILED = SUBMIT_FEEDBACK_SUCCESS + 1;

	/***
	 * messagemanager c2c
	 */
	// 向PC发送绿色上网的处理指令成功
	public static final int SEND_PC_COMMOND_SUCCESS = SUBMIT_FEEDBACK_FAILED + 1;
	// 发送指令失败
	public static final int SEND_PC_COMMOND_FAILED = SEND_PC_COMMOND_SUCCESS + 1;
	// 发送图片消息成功c2c
	public static final int SEND_PIC_C2C_SUCCESS = SEND_PC_COMMOND_FAILED + 1;
	// 发送图片消息失败c2c
	public static final int SEND_PIC_C2C_FAILED = SEND_PIC_C2C_SUCCESS + 1;
	// 发送语音消息成功c2c
	public static final int SEND_VOICE_C2C_SUCCESS = SEND_PIC_C2C_FAILED + 1;
	// 发送语音消息失败c2c
	public static final int SEND_VOICE_C2C_FAILED = SEND_VOICE_C2C_SUCCESS + 1;
	// 发送视频消息成功c2c
	public static final int SEND_VIDEO_C2C_SUCCESS = SEND_VOICE_C2C_FAILED + 1;
	// 发送视频消息失败c2c
	public static final int SEND_VIDEO_C2C_FAILED = SEND_VIDEO_C2C_SUCCESS + 1;
	// 发送文本消息成功c2c
	public static final int SEND_TEXT_C2C_SUCCESS = SEND_VIDEO_C2C_FAILED + 1;
	// 发送文本消息失败c2c
	public static final int SEND_TEXT_C2C_FAILED = SEND_TEXT_C2C_SUCCESS + 1;
	// 发送名片消息成功 c2c
	public static final int SEND_IDCARD_C2C_SUCCESS = SEND_TEXT_C2C_FAILED + 1;
	// 发送名片消息失败 c2c
	public static final int SEND_IDCARD_C2C_FAILED = SEND_IDCARD_C2C_SUCCESS + 1;
	/***
	 * messagemanager c2g
	 */
	// 发送图片消息成功c2g
	public static final int SEND_PIC_C2G_SUCCESS = SEND_IDCARD_C2C_FAILED + 1;
	// 发送图片消息失败c2g
	public static final int SEND_PIC_C2G_FAILED = SEND_PIC_C2G_SUCCESS + 1;
	// 发送语音消息成功c2g
	public static final int SEND_VOICE_C2G_SUCCESS = SEND_PIC_C2G_FAILED + 1;
	// 发送语音消息失败c2g
	public static final int SEND_VOICE_C2G_FAILED = SEND_VOICE_C2G_SUCCESS + 1;
	// 发送视频消息成功c2g
	public static final int SEND_VIDEO_C2G_SUCCESS = SEND_VOICE_C2G_FAILED + 1;
	// 发送视频消息失败c2g
	public static final int SEND_VIDEO_C2G_FAILED = SEND_VIDEO_C2G_SUCCESS + 1;
	// 发送文本消息成功c2g
	public static final int SEND_TEXT_C2G_SUCCESS = SEND_VIDEO_C2G_FAILED + 1;
	// 发送文本消息失败c2g
	public static final int SEND_TEXT_C2G_FAILED = SEND_TEXT_C2G_SUCCESS + 1;
	// 发送名片消息成功c2g
	public static final int SEND_IDCARD_C2G_SUCCESS = SEND_TEXT_C2G_FAILED + 1;
	// 发送名片消息失败c2g
	public static final int SEND_IDCARD_C2G_FAILED = SEND_IDCARD_C2G_SUCCESS + 1;
	/***
	 * messagemanager voicetest
	 */
	// 发送语音测试消息成功c2c
	public static final int SEND_VOICETEST_C2C_SUCCESS = SEND_IDCARD_C2G_FAILED + 1;
	// 发送语音测试消息失败c2c
	public static final int SEND_VOICETEST_C2C_FAILED = SEND_VOICETEST_C2C_SUCCESS + 1;
	// 发送语音测试消息成功c2g
	public static final int SEND_VOICETEST_C2G_SUCCESS = SEND_VOICETEST_C2C_FAILED + 1;
	// 发送语音测试消息失败c2g
	public static final int SEND_VOICETEST_C2G_FAILED = SEND_VOICETEST_C2G_SUCCESS + 1;

	/***
	 * messagemanager pollmsg
	 */
	// 拉取未读成功 c2c
	public static final int POLL_C2C_SUCCESS = SEND_VOICETEST_C2G_FAILED + 1;
	// 拉取未读失败 c2c
	public static final int POLL_C2C_FAILED = POLL_C2C_SUCCESS + 1;
	// 拉取未读成功 c2g
	public static final int POLL_C2G_SUCCESS = POLL_C2C_FAILED + 1;
	// 拉取未读失败 c2g
	public static final int POLL_C2G_FAILED = POLL_C2G_SUCCESS + 1;
	// 拉取未读成功 系统消息
	public static final int POLL_SYSTEM_SUCCESS = POLL_C2G_FAILED + 1;
	// 拉取未读失败 系统消息
	public static final int POLL_SYSTEM_FAILED = POLL_SYSTEM_SUCCESS + 1;
	// 拉取未读成功 社区群消息
	public static final int POLL_SOCIAL_SUCCESS = POLL_SYSTEM_FAILED + 1;
	// 拉取未读失败 社区群消息
	public static final int POLL_SOCIAL_FAILED = POLL_SOCIAL_SUCCESS + 1;
	// 拉取未读成功 公告
	public static final int POLL_NOTICE_SUCCESS = POLL_SOCIAL_FAILED + 1;
	// 拉取未读失败 公告
	public static final int POLL_NOTICE_FAILED = POLL_NOTICE_SUCCESS + 1;
	// 拉取未读成功 课堂信息助手
	public static final int POLL_CLASSASSIS_SUCCESS = POLL_NOTICE_FAILED + 1;
	// 拉取未读失败 课堂信息助手
	public static final int POLL_CLASSASSIS_FAILED = POLL_CLASSASSIS_SUCCESS + 1;
	// 拉取未读成功 语音测评
	public static final int POLL_VOICETEST_SUCCESS = POLL_CLASSASSIS_FAILED + 1;
	// 拉取未读失败 语音测评
	public static final int POLL_VOICETEST_FAILED = POLL_VOICETEST_SUCCESS + 1;
	// 拉取未读成功 绿色上网助手
	public static final int POLL_GREENNET_SUCCESS = POLL_VOICETEST_FAILED + 1;
	// 拉取未读失败 绿色上网助手
	public static final int POLL_GREENNET_FAILED = POLL_GREENNET_SUCCESS + 1;
	/***
	 * messagemanager poll unreadinfo
	 */
	// 上线拉取未读信息 成功
	public static final int POLL_UNREADINFO_SUCCESS = POLL_GREENNET_FAILED;
	// 上线拉取未读信息 失败
	public static final int POLL_UNREADINFO_FAILED = POLL_UNREADINFO_SUCCESS;
	/***
	 * schoolinfomanager
	 */
	// 拉取地区信息成功
	public static final int GET_AREAINFO_SUCCESS = POLL_UNREADINFO_FAILED + 1;
	// 拉取地区信息失败
	public static final int GET_AREAINFO_FAILED = GET_AREAINFO_SUCCESS + 1;
	// 拉取学校信息成功
	public static final int GET_SCHOOLINFO_SUCCESS = GET_AREAINFO_FAILED + 1;
	// 拉取学校信息失败
	public static final int GET_SCHOOLINFO_FAILED = GET_SCHOOLINFO_SUCCESS + 1;
	// 拉取班级信息成功
	public static final int GET_CLASSINFO_SUCCESS = GET_SCHOOLINFO_FAILED + 1;
	// 拉取班级信息失败
	public static final int GET_CLASSINFO_FAILED = GET_CLASSINFO_SUCCESS + 1;

	/***
	 * groupmanager
	 */
	// 拉取群信息成功
	public static final int GET_GROUPINFO_SUCCESS = GET_CLASSINFO_FAILED + 1;
	// 拉取群信息失败
	public static final int GET_GROUPINFO_FAILED = GET_GROUPINFO_SUCCESS + 1;

	/***
	 * friendmanager
	 */
	// 拉取个人信息成功
	public static final int GET_USERINFO_SUCCESS = GET_GROUPINFO_FAILED + 1;
	// 拉取个人信息失败
	public static final int GET_USERINFO_FAILED = GET_USERINFO_SUCCESS + 1;
	// 拉取个人详细信息成功
	public static final int GET_VERBOSEUSERINFO_SUCCESS = GET_USERINFO_FAILED + 1;
	// 拉取个人详细信息失败
	public static final int GET_VERBOSEUSERINFO_FAILED = GET_VERBOSEUSERINFO_SUCCESS + 1;

	/***
	 * passwordmanager
	 */
	public static final int RESET_PASSWORD_SUCCESS = GET_VERBOSEUSERINFO_FAILED + 1;
	public static final int RESET_PASSWORD_FAILED = RESET_PASSWORD_SUCCESS + 1;
	public static final int FIND_PASSWORD_SUCCESS = RESET_PASSWORD_FAILED + 1;
	public static final int FIND_PASSWORD_FAILED = FIND_PASSWORD_SUCCESS + 1;
	/***
	 * pushmanager
	 */
	public static final int PUSH_MANAGER_READ_SUCCESS = FIND_PASSWORD_FAILED + 1;
	public static final int PUSH_MANAGER_READ_FAILED = PUSH_MANAGER_READ_SUCCESS + 1;
	/***
	 * statusreportmanager
	 */
	public static final int GET_STATUS_SUCCESS = PUSH_MANAGER_READ_FAILED + 1;
	public static final int GET_STATUS_FAILED = GET_STATUS_SUCCESS + 1;
	public static final int SET_STATUS_SUCCESS = GET_STATUS_FAILED + 1;
	public static final int SET_STATUS_FAILED = SET_STATUS_SUCCESS + 1;
	/***
	 * updatemanager
	 */
}
