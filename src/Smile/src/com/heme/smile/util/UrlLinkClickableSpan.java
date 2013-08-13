package com.heme.smile.util;

import android.text.style.ClickableSpan;
import android.view.View;

public class UrlLinkClickableSpan extends ClickableSpan {
	
	
	  @Override
	public void onClick(View widget) {
		// TODO Auto-generated method stub
		
	}
//    @Override
//    public void onClick(final View widget) {
//
//		final TextView tv = (TextView) widget;
//		final int start = tv.getSelectionStart();
//		final int end = tv.getSelectionEnd();
//		final SpannableString spStr = new SpannableString(tv.getText());
//	    final Handler mHandler = new Handler()
//    	{
//			@Override
//			public void handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				super.handleMessage(msg);
//				if(msg.what == 1)
//				{				
//					spStr.setSpan(new BackgroundColorSpan(Color.parseColor("#A00099CC")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);							
//					tv.setText(spStr);
//				}
//				else if(msg.what == 2)
//				{
//					spStr.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//					tv.setText(spStr);
//					//所需要的动作
//				}
//				
//			}
//    		
//    	};
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Message msg = new Message();
//					msg.what = 1;
//					mHandler.sendMessage(msg);
//					Thread.sleep(100);
//					msg = new Message();
//					msg.what = 2;
//					mHandler.sendMessage(msg);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}).start();
//    }
}
