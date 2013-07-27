package com.heme.smile;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.heme.logic.common.Constans;

//针对绿色上网，发送命令给PC端
public class SendPcCommondActivity extends BaseActivity {
	private static final String TAG = "SendPcCommondActivity";
	
	public static final String NOTIFY_ID = "notify_id"; 
	private Spinner mSpinner;
	private String[] mCommondList = new String[5];
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.SEND_PC_COMMOND_SUCCESS:
				dismissDialog();
				Util.showToast(SendPcCommondActivity.this, "发送指令成功");
				finish();
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
		initCommonds();
		initUI();
	}
	private void initCommonds(){
		mCommondList[0] = "请选择处理方式";
		mCommondList[1] = "重启机器";
		mCommondList[2] = "远程关机";
		mCommondList[3] = "关闭进程";
		mCommondList[4] = "信息拦截";
	}
	private void initUI(){
		setContentView(R.layout.sendpccommond);
		mSpinner = (Spinner)findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(SendPcCommondActivity.this, android.R.layout.simple_spinner_dropdown_item,mCommondList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(adapter);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2!=0) {
					showAlertDialog("请注意", "确定向绑定电脑发送此命令吗?", "确定", "取消", new LeftButtonclickListener() {
						
						@Override
						public void onLeftBtnClick() {
							// TODO Auto-generated method stub
							showWaitDialog("正在发送命令中,请稍候...");
							mHandler.sendEmptyMessageDelayed(Constans.SEND_PC_COMMOND_SUCCESS, 1500);
						}
					}, new RightButtonclickListener() {
						
						@Override
						public void onRightBtnClick() {
							// TODO Auto-generated method stub
							
						}
					});
				}else {
					Util.showToast(SendPcCommondActivity.this, "请选择处理方式");
				}
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}

