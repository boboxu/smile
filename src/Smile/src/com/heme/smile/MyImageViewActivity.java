package com.heme.smile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.smile.util.MulitPointTouchListener;
import com.heme.utils.Util;

public class MyImageViewActivity extends Activity {
	public static final String FILEPATH = "filepath";
		private ImageView view;
		Matrix matrix = new Matrix();
		Rect rect;
		private ImageButton zoom_in,zoom_out;
		private PointF mid;
		private String filePath;
		private Bitmap bitmap;
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	    	filePath = getIntent().getStringExtra(FILEPATH);
	    	try {
	    		bitmap = BitmapFactory.decodeFile(filePath);
			} catch (OutOfMemoryError error) {
				System.gc();
				try {
					BitmapFactory.Options opts = new BitmapFactory.Options();
					opts.inSampleSize = 2;
					bitmap = BitmapFactory.decodeFile(filePath, opts);
				} catch (OutOfMemoryError error2) {
					Util.showToast(this, "图片太大，加载失败");
					finish();
					return;
				}
			}
	    	
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.multiimageview);  
	        mid = new PointF();
	        findAll();
	        setListener();
	    }  
	    
	    private void findAll(){
	    	findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	    	((TextView)findViewById(R.id.titleTextView)).setText("查看大图");
	    	view = (ImageView) findViewById(R.id.image_View);  
	    	view.setImageBitmap(bitmap);
	    	zoom_in = (ImageButton) findViewById(R.id.ibtn_zoom_in);
	    	zoom_out = (ImageButton) findViewById(R.id.ibtn_zoom_out);
	    }
	    @Override
	    protected void onDestroy() {
	    	if (bitmap!=null&&!bitmap.isRecycled()) {
	    		bitmap.recycle();
		    	bitmap = null;
			}
	    	System.gc();
	    	super.onDestroy();
	    }
	    private void setListener(){
	    	 view.setOnTouchListener(new MulitPointTouchListener());
	    	 //放大
	    	 zoom_in.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					matrix.set(view.getImageMatrix());
					setMid();//设置放大的中心
					matrix.postScale(1.3f, 1.3f, mid.x,mid.y);
					view.setImageMatrix(matrix);
					view.invalidate();
				}
			});
	    	 //缩小
	    	 zoom_out.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					matrix.set(view.getImageMatrix());
					setMid();//设置放大的中心
					matrix.postScale(0.8f, 0.8f, mid.x,mid.y);
					view.setImageMatrix(matrix);
					view.invalidate();
				}
			});
	    }
	    private void setMid(){
	    	rect = view.getDrawable().getBounds();
			mid.x = view.getDrawable().getBounds().centerX();
			mid.y = view.getDrawable().getBounds().centerY();
	    }
}