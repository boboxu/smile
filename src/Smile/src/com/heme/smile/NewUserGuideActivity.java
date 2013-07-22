package com.heme.smile;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.heme.smile.ui.listener.OnViewChangeListener;
import com.heme.smile.ui.view.MyScrollLayout;
import com.heme.smile.R;

public class NewUserGuideActivity extends Activity implements OnViewChangeListener, OnClickListener{
    /** Called when the activity is first created. */
	

	private MyScrollLayout mScrollLayout;
	
	private ImageView[] mImageViews;
	
	private int mViewCount;
	
	private int mCurSel;
	private Button mStartBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuserguide);
        
        init();
    }
    
    private void gotoLoginActivity(){
    	Intent intent = new Intent(NewUserGuideActivity.this,LoginActivity.class);
    	SharedPreferences sPreferences = this.getSharedPreferences(getResources().getString(R.string.shardpreference_newuserguide), MODE_PRIVATE);
    	Editor editor = sPreferences.edit();
    	editor.putBoolean("guided", true);
    	editor.commit();
    	startActivity(intent);
    	finish();
    }

    private void init()
    {
    	mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);
    	mStartBtn = (Button)findViewById(R.id.startBtn);
    	mStartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoLoginActivity();
			}
		});
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout);
    	
    	mViewCount = mScrollLayout.getChildCount();
    	mImageViews = new ImageView[mViewCount];
    	
    	for(int i = 0; i < mViewCount; i++)
    	{
    		mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
    		mImageViews[i].setEnabled(true);
    		mImageViews[i].setOnClickListener(this);
    		mImageViews[i].setTag(i);
    	}
    	
    	mCurSel = 0;
    	mImageViews[mCurSel].setEnabled(false);
    	
    	mScrollLayout.SetOnViewChangeListener(this);
    }


    private void setCurPoint(int index)
    {
    	if (index < 0 || index > mViewCount - 1 || mCurSel == index)
    	{
    		return ;
    	}
    	
    	mImageViews[mCurSel].setEnabled(true);
    	mImageViews[index].setEnabled(false);
    	
    	mCurSel = index;
    	if (mCurSel==mViewCount-1) {
    		mStartBtn.setVisibility(View.VISIBLE);
		}else {
			mStartBtn.setVisibility(View.GONE);
		}
    }

    @Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = (Integer)(v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}
}