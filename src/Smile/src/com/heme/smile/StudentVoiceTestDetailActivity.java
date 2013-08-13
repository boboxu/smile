package com.heme.smile;

import java.io.IOException;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heme.logic.common.Constans;
import com.heme.smile.util.AudioRecorder;
import com.heme.smile.util.VoiceData;
import com.heme.utils.Util;

//语音评测详细_学生版
public class StudentVoiceTestDetailActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "StudentVoiceTestDetailActivity";
	public static final String VOICE_DATA = "voice_data";
	private VoiceData mData;
	private TextView mSendTime,mSenderName,mContent;
	private ImageView mAvatarImg;
	private Button mRecBtn;
	private Thread recordThread;
	
	private static int MAX_TIME = 0;    //最长录制时间，单位秒，0为无时间限制
	private static int MIX_TIME = 1;     //最短录制时间，单位秒，0为无时间限制，建议设为1
	
	private static int RECORD_NO = 0;  //不在录音
	private static int RECORD_ING = 1;   //正在录音
	private static int RECODE_ED = 2;   //完成录音
	
	private static int RECODE_STATE = 0;      //录音的状态
	private AudioRecorder mr;
	private MediaPlayer mediaPlayer;
	private Dialog dialog;
	private ImageView mDialogImage;
	private static float recodeTime=0.0f;    //录音的时间
	private static double voiceValue=0.0;    //麦克风获取的音量值
	private RelativeLayout mOperateLayout;
	private Button mPlayBtn,mRerecordBtn,mSendBtn;
	private static boolean playState = false;  //播放状态
	private String mRecordPath = "";
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constans.PREPARE_VOICE_TEST_FILE_SUCCESS:
				//正式代码中开启service 后台上传该评测文件
				Util.showToast(StudentVoiceTestDetailActivity.this, "初始化成功，开始后台上传语音评测录音文件");
				finish();
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mData = (VoiceData)getIntent().getExtras().getSerializable(VOICE_DATA);
		
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.studentvoicetestdetail);
		mPlayBtn = (Button)findViewById(R.id.play_btn);
		mRerecordBtn = (Button)findViewById(R.id.rerecord_btn);
		mSendBtn = (Button)findViewById(R.id.send);
		mPlayBtn.setOnClickListener(this);
		mRerecordBtn.setOnClickListener(this);
		mSendBtn.setOnClickListener(this);
		mOperateLayout = (RelativeLayout)findViewById(R.id.record_completed_operate_layout);
		mRecBtn = (Button)findViewById(R.id.rec_btn);
		mRecBtn.setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("语音评测");
		findViewById(R.id.backImg).setOnClickListener(this);
		mSendTime = (TextView)findViewById(R.id.tv_sendtime);
		mSenderName = (TextView)findViewById(R.id.tv_username);
		mContent = (TextView)findViewById(R.id.tv_chatcontent);
		mSendTime.setText("2013年8月8日 15:19:33");
		mSenderName.setText(mData.nickname);
		mContent.setText(mData.content);
	}
	void showVoiceDialog(){
		dialog = new Dialog(StudentVoiceTestDetailActivity.this,R.style.RecordingDialogStyle);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setCancelable(true);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.recording_dialog);
		mDialogImage=(ImageView)dialog.findViewById(R.id.dialog_img);
		dialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				stopRecord();
			}
		});
		dialog.show();
	}
	private void startRecordThread(){
		recordThread = new Thread(RecordThread);
		recordThread.start();
	}
	private Runnable RecordThread = new Runnable() {
		@Override
		public void run() {
			recodeTime = 0.0f;
			while (RECODE_STATE == RECORD_ING) {
				if (recodeTime >= MAX_TIME && MAX_TIME != 0) {
					recorderHandler.sendEmptyMessage(0);
				} else {
					try {
						Thread.sleep(200);
						recodeTime += 0.2;
						if (RECODE_STATE == RECORD_ING) {
							voiceValue = mr.getAmplitude();
							recorderHandler.sendEmptyMessage(1);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		Handler recorderHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {

				switch (msg.what) {
				case 0:
					// 录音超过15秒自动停止
					if (RECODE_STATE == RECORD_ING) {
						RECODE_STATE = RECODE_ED;
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						try {
							mr.stop();
							voiceValue = 0.0;
						} catch (IOException e) {
							e.printStackTrace();
						}

						if (recodeTime < 1.0) {
							Util.showToast(StudentVoiceTestDetailActivity.this, "录音时间太短，失败");
							RECODE_STATE = RECORD_NO;
							mRecBtn.setVisibility(View.VISIBLE);
							mOperateLayout.setVisibility(View.GONE);
							((TextView)findViewById(R.id.record_time)).setText("录音时长:");
							mRecordPath = "";
						} else {
							Util.showToast(StudentVoiceTestDetailActivity.this,
									"录音完成,执行发送语音逻辑");
							mRecBtn.setVisibility(View.GONE);
							mOperateLayout.setVisibility(View.VISIBLE);
							((TextView)findViewById(R.id.record_time)).setText("录音时长:"+recodeTime+"秒");
							mRecordPath = mr.getPath();
						}
					}
					break;
				case 1:
					setDialogImage();
					break;
				default:
					break;
				}
			}
		};
	};
	private	void setDialogImage(){
		if (voiceValue < 200.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_01);
		}else if (voiceValue > 200.0 && voiceValue < 400) {
			mDialogImage.setImageResource(R.drawable.record_animate_02);
		}else if (voiceValue > 400.0 && voiceValue < 800) {
			mDialogImage.setImageResource(R.drawable.record_animate_03);
		}else if (voiceValue > 800.0 && voiceValue < 1600) {
			mDialogImage.setImageResource(R.drawable.record_animate_04);
		}else if (voiceValue > 1600.0 && voiceValue < 3200) {
			mDialogImage.setImageResource(R.drawable.record_animate_05);
		}else if (voiceValue > 3200.0 && voiceValue < 5000) {
			mDialogImage.setImageResource(R.drawable.record_animate_06);
		}else if (voiceValue > 5000.0 && voiceValue < 7000) {
			mDialogImage.setImageResource(R.drawable.record_animate_07);
		}else if (voiceValue > 7000.0 && voiceValue < 10000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_08);
		}else if (voiceValue > 10000.0 && voiceValue < 14000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_09);
		}else if (voiceValue > 14000.0 && voiceValue < 17000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_10);
		}else if (voiceValue > 17000.0 && voiceValue < 20000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_11);
		}else if (voiceValue > 20000.0 && voiceValue < 24000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_12);
		}else if (voiceValue > 24000.0 && voiceValue < 28000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_13);
		}else if (voiceValue > 28000.0) {
			mDialogImage.setImageResource(R.drawable.record_animate_14);
		}
	}
	private void startRecord(){
		if (RECODE_STATE != RECORD_ING) {
			mr = new AudioRecorder("_"+System.currentTimeMillis()+"");
			RECODE_STATE = RECORD_ING;
			showVoiceDialog();
			try {
				mr.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			startRecordThread();
		}
	}
	private void stopRecord(){
		if (RECODE_STATE == RECORD_ING) {
			RECODE_STATE = RECODE_ED;
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			try {
				mr.stop();
				voiceValue = 0.0;
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (recodeTime < MIX_TIME) {
				Util.showToast(StudentVoiceTestDetailActivity.this,
						"录音时间太短，失败");
				RECODE_STATE = RECORD_NO;
				mRecBtn.setVisibility(View.VISIBLE);
				mOperateLayout.setVisibility(View.GONE);
				((TextView)findViewById(R.id.record_time)).setText("录音时长:");
				mRecordPath = "";
			} else {
				Util.showToast(StudentVoiceTestDetailActivity.this,
						"录音完成");
				mRecBtn.setVisibility(View.GONE);
				mOperateLayout.setVisibility(View.VISIBLE);
				((TextView)findViewById(R.id.record_time)).setText("录音时长:"+recodeTime+"秒");
				mRecordPath = mr.getPath();
			}
		}
	}
	private void playVoice(){
		//播放语音消息
		if (!playState) {
			if (mRecordPath==null||mRecordPath.trim().equals("")) {
				Util.showToast(StudentVoiceTestDetailActivity.this, "非法语音文件路径,播放失败");
				return;
			}
			mediaPlayer = new MediaPlayer();
			String url = mRecordPath;
			try
			{
				mPlayBtn.setText("停止");
				mSendBtn.setClickable(false);
				mRerecordBtn.setClickable(false);
				//模拟器里播放传url，真机播放传getAmrPath()
				mediaPlayer.setDataSource(url);
				//mediaPlayer.setDataSource(getAmrPath());
				mediaPlayer.prepare();
				mediaPlayer.start();
				playState=true;
				//设置播放结束时监听
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mp) {
						if (playState) {
							playState=false;
						}
						mPlayBtn.setText("播放");
						mRerecordBtn.setClickable(true);
						mSendBtn.setClickable(true);
					}
				});
			}
			catch (IllegalArgumentException e)
			{
				mPlayBtn.setText("播放");
				mRerecordBtn.setClickable(true);
				mSendBtn.setClickable(true);
				e.printStackTrace();
			}
			catch (IllegalStateException e)
			{
				mPlayBtn.setText("播放");
				mRerecordBtn.setClickable(true);
				mSendBtn.setClickable(true);
				e.printStackTrace();
			}
			catch (IOException e)
			{
				mPlayBtn.setText("播放");
				mRerecordBtn.setClickable(true);
				mSendBtn.setClickable(true);
				e.printStackTrace();
			}
		
		}else {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				playState=false;
			}else {
				playState=false;
			}
			mPlayBtn.setText("播放");
			mRerecordBtn.setClickable(true);
			mSendBtn.setClickable(true);
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;
		case R.id.rec_btn:
			startRecord();
			mRecBtn.setVisibility(View.GONE);
			break;
		case R.id.play_btn:
			playVoice();
			
			break;
		case R.id.rerecord_btn:
			startRecord();
			mOperateLayout.setVisibility(View.GONE);
			break;
		case R.id.send:
			showWaitDialog("正在进行上传初始化工作，请稍候..");
			//模拟一些上传的准备工作
			mHandler.sendEmptyMessageDelayed(Constans.PREPARE_VOICE_TEST_FILE_SUCCESS, 1500);
			break;
		default:
			break;
		}
	}
	
}
