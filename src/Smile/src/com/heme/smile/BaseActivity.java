package com.heme.smile;

import com.heme.smile.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class BaseActivity extends Activity {
	private ProgressDialog mDialog;
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
