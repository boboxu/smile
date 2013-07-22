package com.heme.smile;

import com.heme.smile.common.Constans;
import com.heme.smile.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "SettingActivity";
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
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.setting);
		findViewById(R.id.backImg).setVisibility(View.GONE);
		((TextView)findViewById(R.id.titleTextView)).setText("设置");
		findViewById(R.id.clear_chat_history).setOnClickListener(this);
		findViewById(R.id.update_pwd).setOnClickListener(this);
		findViewById(R.id.about).setOnClickListener(this);
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
