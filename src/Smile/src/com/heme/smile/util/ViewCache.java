package com.heme.smile.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.smile.R;

public class ViewCache {

	    private View baseView;
	    private TextView textView;
	    private ImageView imageView;
	    private TextView timeView;
	    public ViewCache(View baseView) {
	        this.baseView = baseView;
	    }

	    public TextView getTextView() {
	        if (textView == null) {
	            textView = (TextView) baseView.findViewById(R.id.notification_text);
	        }
	        return textView;
	    }

	    public ImageView getImageView() {
	        if (imageView == null) {
	            imageView = (ImageView) baseView.findViewById(R.id.notification_img);
	        }
	        return imageView;
	    }
	    public TextView getTimeView() {
	        if (timeView == null) {
	        	timeView = (TextView) baseView.findViewById(R.id.time_catalog);
	        }
	        return timeView;
	    }

}
