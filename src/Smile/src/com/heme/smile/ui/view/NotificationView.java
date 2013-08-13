package com.heme.smile.ui.view;

import com.heme.smile.MainActivity;
import com.heme.smile.SettingActivity;
import com.heme.smile.StartActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;

public class NotificationView extends View {

	private Context mContext = null;
	private WindowManager mWindowMgr = null;
	private WindowManager.LayoutParams mWindowMgrParams = null;
	private AnimationDrawable mAnimationDrawable = null;
	
	private int iPosX = 0;
	private int iPosY = 0;
	private int iLastPosX = 0;
	private int iLastPosY = 0;
	private boolean bMoved = false;
	   
	public NotificationView(Context context) {
		this(context, null, 0);
	}
	public NotificationView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public NotificationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		mContext = context;
		mWindowMgr = (WindowManager)getContext().getApplicationContext().getSystemService("window");
		mWindowMgrParams = new WindowManager.LayoutParams();
		initParams();
		
	}
	
	private void initParams(){
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mWindowMgrParams.x = dm.widthPixels - 136;
		mWindowMgrParams.y = 300;
		mWindowMgrParams.width = 136;
		mWindowMgrParams.height = 136;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			iPosX = (int)event.getX();
			iPosY = (int)event.getY();
			bMoved = false;
			break;
			
		case MotionEvent.ACTION_MOVE:
			bMoved = true;
			iLastPosX = (int)event.getX();
			iLastPosY = (int)event.getY();
			updatePostion(iLastPosX - iPosX, iLastPosY - iPosY);
			break;
			
		case MotionEvent.ACTION_UP:
			if(!bMoved){
				Intent it=new Intent(mContext, StartActivity.class);
				mContext.startActivity(it);
			}
			break;
			
		default:
			break;
		}
		return true;
	}

	private void updatePostion(int x, int y){
		mWindowMgrParams.type = 2003;
		mWindowMgrParams.format = 1;
		mWindowMgrParams.flags = 40;
		mWindowMgrParams.gravity = Gravity.LEFT | Gravity.TOP;
		mWindowMgrParams.x += x;
		mWindowMgrParams.y += y;
		mWindowMgr.updateViewLayout(this, mWindowMgrParams);
	}
}
