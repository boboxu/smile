package com.heme.smile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.heme.smile.ui.view.ChatMsgViewAdapter;
import com.heme.smile.util.AudioRecorder;
import com.heme.smile.util.ChatMsgEntity;

public class SingleChatActivity extends Activity implements OnClickListener {
	
	public static final String SINGLE_CHAT_NICKNAME = "single_chat_nickname";
	public static final int PLAYER_VOICE_MESSAGE = 0;
	private Dialog dialog;
	private Button mBtnSend;// 发送btn
	private ImageView mBtnBack;// 返回btn
	private EditText mEditTextContent;
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
	private String mSingleChater = "";
	
	
	private AudioRecorder mr;
	private MediaPlayer mediaPlayer;
	Button player;
	private Thread recordThread;
	
	private static int MAX_TIME = 300;    //最长录制时间，单位秒，0为无时间限制
	private static int MIX_TIME = 1;     //最短录制时间，单位秒，0为无时间限制，建议设为1
	
	private static int RECORD_NO = 0;  //不在录音
	private static int RECORD_ING = 1;   //正在录音
	private static int RECODE_ED = 2;   //完成录音
	
	private static int RECODE_STATE = 0;      //录音的状态
	
	private static float recodeTime=0.0f;    //录音的时间
	private static double voiceValue=0.0;    //麦克风获取的音量值
	private String[] msgArray = new String[] { "你好呀","我很好","今天我吃了五碗饭","我吃了十碗","你牛逼" };
	private String[] dataArray = new String[] { "2013-07-27 00:09:02",
			"2013-07-27 00:09:22", "2013-07-27 00:09:33",
			"2013-07-27 00:09:44", "2013-07-27 00:09:55"};
	private final static int COUNT = 5;// 初始化数组总数
	private ImageView mDialogImage;
	private static boolean playState = false;  //播放状态
	private ImageButton mVoiceBtn;
	private LinearLayout mLuyinLayout;
	private LinearLayout mFasongLayout;
	private ImageButton mKeyboardBtn;
	private Button mRecordBtn;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PLAYER_VOICE_MESSAGE:
				playVoiceMsg(msg.arg1);
				break;

			default:
				break;
			}
		};
	};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSingleChater = getIntent().getStringExtra(SINGLE_CHAT_NICKNAME);
		setContentView(R.layout.singlechat);
		initUI();
		initData();// 初始化数据
		mListView.setSelection(mAdapter.getCount() - 1);
	}
	void showVoiceDialog(){
		dialog = new Dialog(SingleChatActivity.this,R.style.RecordingDialogStyle);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.recording_dialog);
		mDialogImage=(ImageView)dialog.findViewById(R.id.dialog_img);
		dialog.show();
	}
	//启动录音线程
	private void startRecordThread(){
		recordThread = new Thread(RecordThread);
		recordThread.start();
	}
	// 录音线程
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
							Util.showToast(SingleChatActivity.this, "录音时间太短，失败");
							RECODE_STATE = RECORD_NO;
						} else {
							Util.showToast(SingleChatActivity.this,
									"录音完成,执行发送语音逻辑");
							sendVoiceMsg(mr.getPath(), recodeTime);
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
	public void initUI() {
		mRecordBtn = (Button)findViewById(R.id.btn_yuyin);
		mRecordBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
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

					break;
				case MotionEvent.ACTION_UP:
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
							Util.showToast(SingleChatActivity.this,
									"录音时间太短，失败");
							RECODE_STATE = RECORD_NO;
						} else {
							sendVoiceMsg(mr.getPath(), recodeTime);
						}
					}

					break;
				}
				return false;
			}
		});
		mFasongLayout = (LinearLayout) findViewById(R.id.ll_fasong);
		mLuyinLayout = (LinearLayout) findViewById(R.id.ll_yuyin);
		mKeyboardBtn = (ImageButton) findViewById(R.id.chatting_keyboard_btn);
		mKeyboardBtn.setOnClickListener(this);
		mVoiceBtn = (ImageButton) findViewById(R.id.chatting_voice_btn);
		mVoiceBtn.setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText(mSingleChater);
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
		});
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (ImageView) findViewById(R.id.backImg);
		mBtnBack.setOnClickListener(this);
		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
	}
	private void playVoiceMsg(int position){
		final ChatMsgEntity entity = mDataArrays.get(position);
		if (entity.getType()==1) {
			//播放语音消息
			if (!playState) {
				mediaPlayer = new MediaPlayer();
				String url = entity.getMessage();
				try
				{
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
						}
					});
				}
				catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IllegalStateException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}else {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
					playState=false;
				}else {
					playState=false;
				}
			}
		}
	}
	public void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setType(0);
			entity.setDate(dataArray[i]);
			if (i % 2 == 0) {
				entity.setName(mSingleChater);
				entity.setMsgType(true);// 收到的消息
			} else {
				entity.setName("任盈盈");
				entity.setMsgType(false);// 自己发送的消息
			}
			entity.setMessage(msgArray[i]);
			mDataArrays.add(entity);
		}

		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mAdapter.setHandler(mHandler);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chatting_keyboard_btn:
			mVoiceBtn.setVisibility(mVoiceBtn.VISIBLE);
			mKeyboardBtn.setVisibility(mKeyboardBtn.GONE);
			mFasongLayout.setVisibility(mFasongLayout.VISIBLE);
			mLuyinLayout.setVisibility(mLuyinLayout.GONE);
			break;
		case R.id.chatting_voice_btn:
			mVoiceBtn.setVisibility(mVoiceBtn.GONE);
			mKeyboardBtn.setVisibility(View.VISIBLE);
			mFasongLayout.setVisibility(View.GONE);
			mLuyinLayout.setVisibility(mLuyinLayout.VISIBLE);
			break;
		case R.id.btn_send:// 发送按钮点击事件
			send();
			break;
		case R.id.backImg:
			finish();
			break;
		}
	}
	private void sendVoiceMsg(String voiceFilePath,float voicetime){
		String contString =voiceFilePath;
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setName("任盈盈");
			entity.setType(1);
			entity.setVoiceTime(voicetime);
			entity.setDate(getDate());
			entity.setMessage(contString);
			entity.setMsgType(false);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变

			mEditTextContent.setText("");// 清空编辑框数据

			mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
		}
	}
	private void send() {
		String contString = mEditTextContent.getText().toString();
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setName("任盈盈");
			entity.setType(0);
			entity.setDate(getDate());
			entity.setMessage(contString);
			entity.setMsgType(false);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变

			mEditTextContent.setText("");// 清空编辑框数据

			mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
		}
	}

	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date());
	}
}