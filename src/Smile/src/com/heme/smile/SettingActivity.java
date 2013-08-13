package com.heme.smile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.heme.logic.common.Constans;
import com.heme.utils.Util;

public class SettingActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "SettingActivity";
	private long mExitTime = 0;
	private ToggleButton mSoundButton,mVibrateButton;
	private SharedPreferences mSoundSP,mVibrateSP;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
			if((System.currentTimeMillis()-mExitTime) > 2000){
				Toast.makeText(getApplicationContext(), "再按一次返回键回到桌面", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis(); 
			}else {
				backToDesk();
			}
			return true;
		}	
		return true;
	}
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.CLEAR_CHAT_HISTORY_SUCCESS:
				dismissDialog();
				Util.showToast(SettingActivity.this, "恭喜,清空聊天记录成功");
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mSoundSP = this.getSharedPreferences(getResources().getString(R.string.shardpreference_sound), MODE_PRIVATE);
		mVibrateSP = this.getSharedPreferences(getResources().getString(R.string.shardpreference_vibrate), MODE_PRIVATE);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.setting);
		findViewById(R.id.backImg).setVisibility(View.GONE);
		((TextView)findViewById(R.id.titleTextView)).setText("设置");
		findViewById(R.id.clear_chat_history).setOnClickListener(this);
		findViewById(R.id.update_pwd).setOnClickListener(this);
		findViewById(R.id.about).setOnClickListener(this);
		mSoundButton = (ToggleButton)findViewById(R.id.sound_btn);
		mSoundButton.setOnClickListener(this);
		mVibrateButton = (ToggleButton)findViewById(R.id.vibrate_btn);
		mVibrateButton.setOnClickListener(this);
		mSoundButton.setChecked(mSoundSP.getBoolean("sound", true));
		mVibrateButton.setChecked(mVibrateSP.getBoolean("vibrate", true));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.clear_chat_history:
			showAlertDialog("提示", "确定清空所有聊天记录吗?", "确定", "取消", new ClearChatHistoryListener(), null);
			break;
		case R.id.update_pwd:
			startActivity(new Intent(this, UpdatePwdActivity.class));
			break;
		case R.id.about:
			startActivity(new Intent(this, AboutActivity.class));
			break;
		case R.id.sound_btn:
			Editor editor = mSoundSP.edit();
			editor.putBoolean("sound", mSoundButton.isChecked());
			editor.commit();
			break;
		case R.id.vibrate_btn:
			Editor v_editor = mVibrateSP.edit();
			v_editor.putBoolean("vibrate", mVibrateButton.isChecked());
			v_editor.commit();
			break;
		default:
			break;
		}
	}
	class ClearChatHistoryListener implements LeftButtonclickListener{
		@Override
		public void onLeftBtnClick() {
			// TODO Auto-generated method stub
			//执行清空聊天记录的逻辑
			showWaitDialog("正在清空聊天记录,请稍候");
			
			mHandler.sendEmptyMessageDelayed(Constans.CLEAR_CHAT_HISTORY_SUCCESS, 3000);
		}
	}
}
