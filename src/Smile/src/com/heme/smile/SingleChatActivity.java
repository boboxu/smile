package com.heme.smile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.ImageSpan;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.heme.smile.R.layout;
import com.heme.smile.ui.view.ChatMsgViewAdapter;
import com.heme.smile.ui.view.TextGridAdapter;
import com.heme.smile.util.AudioRecorder;
import com.heme.smile.util.Biaoqing;
import com.heme.smile.util.BitmapUtil;
import com.heme.smile.util.ChatMsgEntity;
import com.heme.smile.util.FileUtil;
import com.heme.utils.Util;

public class SingleChatActivity extends Activity implements OnClickListener {
	
	public static final String SINGLE_CHAT_NICKNAME = "single_chat_nickname";
	public static final int PLAYER_VOICE_MESSAGE = 0;
	public static final int SELECT_LOCAL = PLAYER_VOICE_MESSAGE + 1; 
	public static final int SHOW_BIG_PICTURE = SELECT_LOCAL + 1;
	public static final int REQUEST_CARD = SHOW_BIG_PICTURE + 1;
	public static final int SHOW_CARD = REQUEST_CARD + 1;
	public static final int SELECT_VIDEO = SHOW_CARD + 1;
	public static final int OPEN_VIDEO_FILE = SELECT_VIDEO +1;
	public static final int CAPTURE = OPEN_VIDEO_FILE + 1;
	public static final String CARD_NICKNAME = "card_nickname";
	public static final String CARD_USERID = "card_userid";
	public static final String SELECT_VIDEO_PATH = "select_video_path";
	private Dialog dialog;
	private Button mBtnSend;// 发送btn
	private ImageView mBtnBack;// 返回btn
	private EditText mEditTextContent;
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
	private String mSingleChater = "";
	private int[] expressionImages;
	private String[] expressionImageNames;
	private int[] expressionImages1;
	private String[] expressionImageNames1;
	private int[] expressionImages2;
	private String[] expressionImageNames2;
	
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
	private RelativeLayout mFasongLayout;
	private ImageButton mKeyboardBtn;
	private Button mRecordBtn;
	private RelativeLayout mOperateLayout;
	private ImageButton mShowOperateBtn;
	private ImageView mBiaoqingImgView;
	private ImageView mTupianImgView;
	private ViewPager viewPager;
	private LinearLayout page_select;
	private ArrayList<GridView> grids;
	private GridView gView1;
	private GridView gView2;
	private GridView gView3;
	private ImageView page0;
	private ImageView page1;
	private ImageView page2;
	private Button mFriendInfoImg;
	private Button mGroupChatBtn;
	private ImageView mBusinessCardImg;
	private ImageView mVideoImg;
	private GridView mGridView;
	private TextGridAdapter mTextGridAdapter;
	private LinkedList<String> mTextKeysList = new LinkedList<String>();
	private LinkedHashMap<String, String> mTextOperationList = new LinkedHashMap<String, String>();
	private PopupWindow mAddCustomChatTextPopupWindow;
	private View mAddCustomChatTextView;
	private Button mAddCustomChatConfirmBtn,mAddCustomChatCancelBtn;
	private EditText mAddCustomChatKeyEditText,mAddCustomChatValueEditText;
	private PopupWindow mPopupWindow;
	private View mAvatarClickView;
	private Button mClickViewCapture,mClickViewSelectLocal,mClickViewCancel;
	private Handler mHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PLAYER_VOICE_MESSAGE:
				playVoiceMsg(msg.arg1);
				break;
			case SHOW_BIG_PICTURE:
				showBigPicture(msg.arg1);
				break;
			case SHOW_CARD:
				Intent cardIntent = new Intent(SingleChatActivity.this, UserDetailsActivity.class);
				cardIntent.putExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME, (String)msg.obj);
				startActivity(cardIntent);
				break;
			case OPEN_VIDEO_FILE:
				Intent intent = new Intent();
				intent.setAction(android.content.Intent.ACTION_VIEW);
				File file = new File((String)msg.obj);
				intent.setDataAndType(Uri.fromFile(file), "video/*");
				startActivity(intent);
				break;
			default:
				break;
			}
		};
	};
	

	private boolean checkVideoPlaySupport(Intent intent) {
	        List<ResolveInfo> list = SingleChatActivity.this.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
	        return list.size() > 0;
	    }
	private void showBigPicture(int position){
		ChatMsgEntity entity = mDataArrays.get(position);
		Intent intent = new Intent(this, MyImageViewActivity.class);
		intent.putExtra(MyImageViewActivity.FILEPATH, entity.getExternalContent());
		startActivity(intent);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTextOperationData();
		mSingleChater = getIntent().getStringExtra(SINGLE_CHAT_NICKNAME);
		setContentView(R.layout.singlechat);
		initUI();
		initViewPager();
		initData();// 初始化数据
		mListView.setSelection(mAdapter.getCount() - 1);
	}
	void showVoiceDialog(){
		dialog = new Dialog(SingleChatActivity.this,R.style.RecordingDialogStyle);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.chat_recording_dialog);
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
	private void initViewPager() {
		LayoutInflater inflater = LayoutInflater.from(this);
		grids = new ArrayList<GridView>();
		gView1 = (GridView) inflater.inflate(R.layout.grid1, null);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		// 生成24个表情
		for (int i = 0; i < 24; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", expressionImages[i]);
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(SingleChatActivity.this, listItems,
				R.layout.singleexpression, new String[] { "image" },
				new int[] { R.id.image });
		gView1.setAdapter(simpleAdapter);
		gView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bitmap bitmap = null;
				bitmap = BitmapFactory.decodeResource(getResources(),
						expressionImages[arg2 % expressionImages.length]);
				ImageSpan imageSpan = new ImageSpan(SingleChatActivity.this, bitmap);
				SpannableString spannableString = new SpannableString(
						expressionImageNames[arg2].substring(1,
								expressionImageNames[arg2].length() - 1));
				spannableString.setSpan(imageSpan, 0,
						expressionImageNames[arg2].length() - 2,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 编辑框设置数据
				mEditTextContent.append(spannableString);
				System.out.println("edit的内容 = " + spannableString);
			}
		});
		grids.add(gView1);

		gView2 = (GridView) inflater.inflate(R.layout.grid2, null);
		grids.add(gView2);

		gView3 = (GridView) inflater.inflate(R.layout.grid3, null);
		grids.add(gView3);

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return grids.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(grids.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(grids.get(position));
				return grids.get(position);
			}
		};

		viewPager.setAdapter(mPagerAdapter);
		// viewPager.setAdapter();

		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}
	//模拟表情操作栏中文字替换的数据
	private void initTextOperationData(){
		mTextOperationList.put("奖励", "小伙子不错，我很看好你.给你个奖励f001");
		mTextOperationList.put("批评", "你小子太不靠谱了，回去面壁思过f010");
		mTextOperationList.put("迟到", "你今天迟到了，下次不要再犯了f014");
		mTextOperationList.put("早退", "课都没上完，人就跑了，早退f008");
		mTextOperationList.put("添加", "添加");
		for (String key : mTextOperationList.keySet()) {
			mTextKeysList.add(key);
		}
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int position = ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;
		if (position==mTextKeysList.size()-1) {
			return false;
		}
		String delete_key = mTextKeysList.get(position);
		if (delete_key==null||delete_key.trim().equals("")) {
			return false;
		}
		if (mTextOperationList.containsKey(delete_key)) {
			mTextOperationList.remove(delete_key);
		}
		mTextKeysList.remove(delete_key);
		Util.showToast(this, "删除成功!");
		mTextGridAdapter.notifyDataSetChanged();
		return true;
	}
	public void initUI() {
		mAvatarClickView = LayoutInflater.from(this).inflate(R.layout.avartclickview, null);
		mClickViewCapture = (Button)mAvatarClickView.findViewById(R.id.capture);
		mClickViewCapture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPopupWindow.dismiss();
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				 startActivityForResult(camera, CAPTURE);
			}
		});
		mClickViewSelectLocal = (Button)mAvatarClickView.findViewById(R.id.selectlocal);
		mClickViewSelectLocal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindow.dismiss();
				Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(picture, SELECT_LOCAL);

			}
		});
		mClickViewCancel = (Button)mAvatarClickView.findViewById(R.id.cancel);
		mClickViewCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mPopupWindow!=null&&mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				}
			}
		});
		mAddCustomChatTextView = LayoutInflater.from(this).inflate(R.layout.custom_chat_text_add, null);
		mAddCustomChatConfirmBtn = (Button)mAddCustomChatTextView.findViewById(R.id.ok);
		mAddCustomChatConfirmBtn.setOnClickListener(this);
		mPopupWindow = new PopupWindow(mAvatarClickView,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,true);
		mAddCustomChatCancelBtn = (Button)mAddCustomChatTextView.findViewById(R.id.cancel);
		mAddCustomChatCancelBtn.setOnClickListener(this);
		mAddCustomChatKeyEditText = (EditText)mAddCustomChatTextView.findViewById(R.id.key_edittext);
		mAddCustomChatValueEditText = (EditText)mAddCustomChatTextView.findViewById(R.id.value_edittext);
		mAddCustomChatTextPopupWindow = new PopupWindow(mAddCustomChatTextView,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,true);
		page0 = (ImageView)findViewById(R.id.page0_select);
		page1 = (ImageView)findViewById(R.id.page1_select);
		page2 = (ImageView)findViewById(R.id.page2_select);
		mGridView = (GridView)findViewById(R.id.custom_textion_gridview);
		mGridView.setNumColumns(4);
		mGridView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
				if (info.position==mTextKeysList.size()-1) {
					return;
				}
				menu.add(Menu.NONE,0,0,"删除");
				
			}
		
		});
		mTextGridAdapter = new TextGridAdapter(this, mTextOperationList, mTextKeysList);
		mGridView.setAdapter(mTextGridAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2==mTextKeysList.size()-1) {
					//添加
					mAddCustomChatTextPopupWindow.setOutsideTouchable(true);
					mAddCustomChatTextPopupWindow.showAtLocation(findViewById(R.id.singlechat_layout), Gravity.CENTER, 0, 0);
					mAddCustomChatTextPopupWindow.update();
				}else {
					String key = mTextKeysList.get(arg2);
					send(mTextOperationList.get(key));
				}
				
			}
		});
		mVideoImg = (ImageView)findViewById(R.id.video_imgview);
		mVideoImg.setOnClickListener(this);
		mBusinessCardImg = (ImageView)findViewById(R.id.businesscard_imgview);
		mBusinessCardImg.setOnClickListener(this);
		mGroupChatBtn = (Button)findViewById(R.id.rightBtn1);
		mGroupChatBtn.setVisibility(View.VISIBLE);
		mGroupChatBtn.setOnClickListener(this);
		mGroupChatBtn.setText("群聊");
		mFriendInfoImg = (Button)findViewById(R.id.rightBtn);
		mFriendInfoImg.setText("好友信息");
		mFriendInfoImg.setVisibility(View.VISIBLE);
		mFriendInfoImg.setOnClickListener(this);
		page_select = (LinearLayout) findViewById(R.id.page_select);
		expressionImages = Biaoqing.expressionImgs;
		expressionImageNames = Biaoqing.expressionImgNames;
		expressionImages1 = Biaoqing.expressionImgs1;
		expressionImageNames1 = Biaoqing.expressionImgNames1;
		expressionImages2 = Biaoqing.expressionImgs2;
		expressionImageNames2 = Biaoqing.expressionImgNames2;
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		mTupianImgView = (ImageView)findViewById(R.id.tupian_imgview);
		mTupianImgView.setOnClickListener(this);
		mBiaoqingImgView = (ImageView)findViewById(R.id.biaoqing_imgview);
		mBiaoqingImgView.setOnClickListener(this);
		mOperateLayout = (RelativeLayout)findViewById(R.id.operate_layout);
		mShowOperateBtn = (ImageButton)findViewById(R.id.chatting_biaoqing_btn);
		mShowOperateBtn.setOnClickListener(this);
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
		mFasongLayout = (RelativeLayout) findViewById(R.id.ll_fasong);
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
		case R.id.ok:
			String key = mAddCustomChatKeyEditText.getText().toString();
			String value = mAddCustomChatValueEditText.getText().toString();
			if (key.trim().equals("")||value.trim().equals("")) {
				Util.showToast(this, "内容不能为空");
				return;
			}
			if (mTextOperationList.containsKey(key.trim())) {
				Util.showToast(this, "您已经添加过短语:"+key);
				return;
			}
			mTextKeysList.remove("添加");
			mTextOperationList.remove("添加");
			mTextKeysList.add(key.trim());
			mTextOperationList.put(key.trim(), value.trim());
			mTextKeysList.add("添加");
			mTextOperationList.put("添加", "添加");
			mAddCustomChatKeyEditText.setText("");
			mAddCustomChatValueEditText.setText("");
			mTextGridAdapter.notifyDataSetChanged();
			mAddCustomChatTextPopupWindow.dismiss();
			
			break;
		case R.id.cancel:
			mAddCustomChatTextPopupWindow.dismiss();
			break;
		case R.id.video_imgview:
			Intent fileIntent = new Intent(this, FileExplorerActivity.class);
			startActivityForResult(fileIntent,SELECT_VIDEO);
			break;
		case R.id.businesscard_imgview:
			Intent cardIntent = new Intent(SingleChatActivity.this, SelectUserListActivity.class);
			cardIntent.putExtra(SelectUserListActivity.SHOWCHECKBOX, false);
			cardIntent.putExtra(SelectUserListActivity.SELECTCARD, true);
			startActivityForResult(cardIntent, REQUEST_CARD);
			break;
		case R.id.rightBtn1:
			Intent address_intent = new Intent(this, SelectUserListActivity.class);
			address_intent.putExtra(SelectUserListActivity.SHOWCHECKBOX, true);
			startActivity(address_intent);
			break;
		case R.id.rightBtn:
			Intent intent = new Intent(this,ChattingPeopleListActivity.class);
			intent.putExtra(SINGLE_CHAT_NICKNAME,mSingleChater );
			startActivity(intent);
			break;
		case R.id.biaoqing_imgview:
			viewPager.setVisibility(viewPager.VISIBLE);
			page_select.setVisibility(page_select.VISIBLE);
			break;
		case R.id.tupian_imgview:
//			Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//			startActivityForResult(picture, SELECT_LOCAL);
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.showAtLocation(findViewById(R.id.singlechat_layout), Gravity.BOTTOM, 0, 0);
			mPopupWindow.update();
			break;
		case R.id.chatting_biaoqing_btn:
			if (mOperateLayout.getVisibility()==View.GONE&&page_select.getVisibility()==View.GONE) {
				mOperateLayout.setVisibility(View.VISIBLE);
			}else{
				viewPager.setVisibility(View.GONE);
				page_select.setVisibility(View.GONE);
				mOperateLayout.setVisibility(View.GONE);
			}
			break;
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
			send(mEditTextContent.getText().toString());
			break;
		case R.id.backImg:
			finish();
			break;
		}
	}

	// 发送名片类型的消息
	private void sendBusinessCardMsg() {
		
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
	private void sendPicMsg(String thumbPicPath,String picpath) {
		String contString = thumbPicPath;
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setName("任盈盈");
			entity.setType(2);
			entity.setDate(getDate());
			entity.setMessage(contString);
			entity.setExternalContent(picpath);
			entity.setMsgType(false);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
			mEditTextContent.setText("");// 清空编辑框数据
			mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
		}
	}
	private void sendVideoMsg(String videopath){
			String contString = videopath;
			if (contString.length() > 0) {
				ChatMsgEntity entity = new ChatMsgEntity();
				entity.setName("任盈盈");
				entity.setType(3);
				entity.setDate(getDate());
				entity.setMessage(contString);
				entity.setExternalContent("");
				entity.setMsgType(false);
				mDataArrays.add(entity);
				mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
				mEditTextContent.setText("");// 清空编辑框数据
				mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
		}
	}
	private void sendBusinessCard(String thumbPicPath,String userid) {
		String contString = userid;
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setName("任盈盈");
			entity.setType(4);
			entity.setDate(getDate());
			entity.setMessage(thumbPicPath);
			entity.setExternalContent(userid);
			entity.setMsgType(false);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
			mEditTextContent.setText("");// 清空编辑框数据
			mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
		}
	}
	private void send(String content) {
		String contString = content;
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
	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			System.out.println("页面滚动" + arg0);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			System.out.println("换页了" + arg0);
		}

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				break;
			case 1:
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
				// 生成24个表情
				for (int i = 0; i < 24; i++) {
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("image", expressionImages1[i]);
					listItems.add(listItem);
				}

				SimpleAdapter simpleAdapter = new SimpleAdapter(SingleChatActivity.this,
						listItems, R.layout.singleexpression,
						new String[] { "image" }, new int[] { R.id.image });
				gView2.setAdapter(simpleAdapter);
				gView2.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Bitmap bitmap = null;
						bitmap = BitmapFactory.decodeResource(getResources(),
								expressionImages1[arg2
										% expressionImages1.length]);
						ImageSpan imageSpan = new ImageSpan(SingleChatActivity.this, bitmap);
						SpannableString spannableString = new SpannableString(
								expressionImageNames1[arg2]
										.substring(1,
												expressionImageNames1[arg2]
														.length() - 1));
						spannableString.setSpan(imageSpan, 0,
								expressionImageNames1[arg2].length() - 2,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						// 编辑框设置数据
						mEditTextContent.append(spannableString);
						System.out.println("edit的内容 = " + spannableString);
					}
				});
				break;
			case 2:
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));
				List<Map<String, Object>> listItems1 = new ArrayList<Map<String, Object>>();
				// 生成24个表情
				for (int i = 0; i < 24; i++) {
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("image", expressionImages2[i]);
					listItems1.add(listItem);
				}

				SimpleAdapter simpleAdapter1 = new SimpleAdapter(SingleChatActivity.this,
						listItems1, R.layout.singleexpression,
						new String[] { "image" }, new int[] { R.id.image });
				gView3.setAdapter(simpleAdapter1);
				gView3.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Bitmap bitmap = null;
						bitmap = BitmapFactory.decodeResource(getResources(),
								expressionImages2[arg2
										% expressionImages2.length]);
						ImageSpan imageSpan = new ImageSpan(SingleChatActivity.this, bitmap);
						SpannableString spannableString = new SpannableString(
								expressionImageNames2[arg2]
										.substring(1,
												expressionImageNames2[arg2]
														.length() - 1));
						spannableString.setSpan(imageSpan, 0,
								expressionImageNames2[arg2].length() - 2,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						// 编辑框设置数据
						mEditTextContent.append(spannableString);
						System.out.println("edit的内容 = " + spannableString);
					}
				});
				break;

			}
		}
	}
	
	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date());
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==Activity.RESULT_OK) {
			if (requestCode==SELECT_LOCAL) {
				String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
				Bitmap b = null;
				String path = null;
				if (data.getData()!=null) {
					Uri uri = data.getData();
					String[] proj = {MediaStore.Images.Media.DATA};
					Cursor cursor = managedQuery(uri, proj, null, null, null);
					int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					if (cursor!=null&&cursor.moveToFirst()) {
						path = cursor.getString(columnIndex);
						cursor.close();
						b = BitmapUtil.loadBitmap(path);
					}else {
						Object object = (Bitmap) (data.getExtras() == null ? null : data.getExtras().get("data")); 
						b = object==null?null:(Bitmap)object; 
						if (b!=null&&!b.isRecycled()) {
							path = FileUtil.saveBitmapToSdCard(b, Environment.getExternalStorageDirectory().getAbsolutePath());
						}
					}
				}else {
					Object object = (Bitmap) (data.getExtras() == null ? null : data.getExtras().get("data")); 
					b = object==null?null:(Bitmap)object; 
					if (b!=null&&!b.isRecycled()) {
						path = FileUtil.saveBitmapToSdCard(b, Environment.getExternalStorageDirectory().getAbsolutePath());
					}
				}
				if (b==null||b.isRecycled()) {
					Util.showToast(this, "获取图片失败");
					return;
				}
				Bitmap thumbnail = ThumbnailUtils.extractThumbnail(b, 500, 500);
				if (b!=null&&!b.isRecycled()) {
					b.recycle();
					b = null;
				}
				System.gc();
				String thumb_path = FileUtil.saveBitmapToSdCard(thumbnail, Environment.getExternalStorageDirectory().getAbsolutePath());
				thumbnail.recycle();
				thumbnail = null;
				System.gc();
				if (path==null||path.trim().equals("")||thumb_path==null||thumb_path.trim().equals("")) {
					Util.showToast(this, "获取图片失败");
					return;
				}
				sendPicMsg(thumb_path, path);
			}else if (requestCode==CAPTURE) {
				if (data==null) {
					Util.showToast(this, "获取图片失败");
					return;
				}
				String path = null;
				Object object = null;
				if (data.getData() != null) { 
                    //根据返回的URI获取对应的SQLite信息 
                    Cursor cursor = this.getContentResolver().query(data.getData(), null, 
                                    null, null, null); 
                    if (cursor!=null&&cursor.moveToFirst()) { 
                    	path = cursor.getString(cursor.getColumnIndex("_data"));// 获取绝对路径 
                    	cursor.close(); 
                    }else {
                    	object = (Bitmap) (data.getExtras() == null ? null : data.getExtras().get("data")); 
					} 
				}else{//三星  小米(小米手机不会自动存储DCIM...  这点让哥又爱又恨...) 
					object = (Bitmap) (data.getExtras() == null ? null : data.getExtras().get("data")); 
				} 
				
				String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";	
				Bitmap b = object==null?null:(Bitmap)object;
				if (b==null||b.isRecycled()) {
					Util.showToast(this, "获取图片失败");
					return;
				}
				Bitmap thumbnail = ThumbnailUtils.extractThumbnail(b, 500, 500);
				path = FileUtil.saveBitmapToSdCard(b, Environment.getExternalStorageDirectory().getAbsolutePath());
				if (b!=null&&!b.isRecycled()) {
					b.recycle();
					b = null;
				}
				System.gc();
				String thumb_path = FileUtil.saveBitmapToSdCard(thumbnail, Environment.getExternalStorageDirectory().getAbsolutePath());
				thumbnail.recycle();
				thumbnail = null;
				System.gc();
				if (path==null||path.trim().equals("")||thumb_path==null||thumb_path.trim().equals("")) {
					Util.showToast(this, "获取图片失败");
					return;
				}
				sendPicMsg(thumb_path, path);
			}
		}else if (resultCode==REQUEST_CARD) {
			if (data!=null) {
				String card_nickname = data.getStringExtra(CARD_NICKNAME);
				String card_userid = data.getStringExtra(CARD_USERID);
				Bitmap cardBitmap = BitmapUtil.createBusinessCard(this, null, card_nickname, card_userid);
				String filepath = BitmapUtil.saveBitmap(cardBitmap, "/mnt/sdcard/cardbusiness.png");
				if (filepath!=null&&!filepath.trim().equals("")) {
					sendBusinessCard(filepath, card_nickname);
				}else {
					Util.showToast(this, "发送名片失败，请重试");
				}
			}
		}else if (resultCode==SELECT_VIDEO) {
			if (data!=null&&data.getExtras()!=null) {
				sendVideoMsg(data.getExtras().getString(SELECT_VIDEO_PATH));
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}