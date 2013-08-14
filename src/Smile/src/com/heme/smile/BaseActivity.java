package com.heme.smile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.heme.smile.ui.view.NotificationView;
import com.heme.smile.util.CrashHandler;

public class BaseActivity extends Activity {
	private static WindowManager mWindowMgr = null;
	private WindowManager.LayoutParams mWindowMgrParams = null;
	private static NotificationView mNotificationView = null;
	private ProgressDialog mDialog;
	private Vibrator mVibrator;
	public void gotoWebview(String title,String url){
		Intent intent = new Intent(BaseActivity.this, CommonWebviewActivity.class);
		Bundle b = new Bundle();
		b.putString(CommonWebviewActivity.TITLE, title);
		b.putString(CommonWebviewActivity.URL, url);
		intent.putExtras(b);
		startActivity(intent);
	}
	private void getWindowLayout(){
		if(mNotificationView == null){
			mWindowMgr = (WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
			mWindowMgrParams = new WindowManager.LayoutParams();
			mWindowMgrParams.type = 2003;
			mWindowMgrParams.format = 1;
			mWindowMgrParams.flags = 40;
			mWindowMgrParams.gravity = Gravity.LEFT | Gravity.TOP;
			initParams();
			
			mNotificationView = new NotificationView(this);
			mWindowMgr.addView(mNotificationView, mWindowMgrParams);
		}
	}
	public void addIconToStatusbar(int resId){
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = new Notification(
				resId, "欢迎回到微校", System.currentTimeMillis());
		n.flags |= Notification.FLAG_ONGOING_EVENT;
		n.flags |= Notification.FLAG_NO_CLEAR;
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
		n.contentIntent = pi;
		if (getSharedPreferences(getResources().getString(R.string.shardpreference_sound), MODE_PRIVATE).getBoolean("sound", true)) {
			n.defaults=Notification.DEFAULT_SOUND;
		}
		n.setLatestEventInfo(this, "微校", "", pi);
		if (getSharedPreferences(getResources().getString(R.string.shardpreference_vibrate), MODE_PRIVATE).getBoolean("vibrate", true)) {
			mVibrator.vibrate(500);
		}
		nm.notify(999, n);
	}
	private void initParams(){
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mWindowMgrParams.x = dm.widthPixels - 136;
		mWindowMgrParams.y = 300;
		mWindowMgrParams.width = 136;
		mWindowMgrParams.height = 136;
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (mVibrator==null) {
			mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);		
		}
	}
	private void deleteIconToStatusbar(){
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(999);
	}
	public void backToDesk(){
		Intent intent= new Intent(Intent.ACTION_MAIN);
		 intent.addCategory(Intent.CATEGORY_HOME);
		 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 startActivity(intent);
		 System.exit(0);
	}
	public void showWaitDialog(String content){
		mDialog = new ProgressDialog(BaseActivity.this);
		mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mDialog.setTitle("请稍候");
		mDialog.setMessage(content);
		mDialog.setIcon(R.drawable.ic_launcher);
		mDialog.setCancelable(true);
		mDialog.show();
	}
	public void dismissDialog(){
		if (mDialog!=null&&mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}
	public void showAlertDialog(String title,String message,String leftText,String rightText,final LeftButtonclickListener leftClick,final RightButtonclickListener rightClick){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.setPositiveButton(leftText, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if (leftClick!=null) {
					leftClick.onLeftBtnClick();
				}
			}
		});
    	builder.setNegativeButton(rightText, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if (rightClick!=null) {
					rightClick.onRightBtnClick();
				}
			}
		});
    	builder.create().show();
	}
	
	
	public interface LeftButtonclickListener{
		public void onLeftBtnClick();
	}
	public interface RightButtonclickListener{
		public void onRightBtnClick();
	}
}
