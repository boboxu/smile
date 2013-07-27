package com.heme.smile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MyMicroSchoolActivity extends BaseActivity implements OnClickListener{
	private static final String TAG= "MyMicroSchoolActivity";
	public static final int CAPTURE = 0;
	public static final int SELECT_LOCAL = 1;
	public static final int CLIP_PHOTO = 2;
	private long mExitTime = 0;
	private RelativeLayout mEditSinagureLayout;
	private EditText mSinagureEditText;
	private Button mSinagureCancelBtn,mSinagureOkBtn;
	private TextView mSinagureTextView;
	private int mCurUserLoginState = 0;   //用户在线的状态 0在线 1离线 
	private TextView mUserState;
	private View mAvatarClickView;
	private PopupWindow mPopupWindow;
	private Button mClickViewCapture,mClickViewSelectLocal,mClickViewCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.mymicoschool);
		File f = new File(Environment.getExternalStorageDirectory()+"/heme");
		if (!f.exists()) {
			f.mkdirs();
		}
		mAvatarClickView = LayoutInflater.from(this).inflate(R.layout.avartclickview, null);
		mClickViewCapture = (Button)mAvatarClickView.findViewById(R.id.capture);
		mClickViewCapture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPopupWindow.dismiss();
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				 startActivityForResult(camera, CAPTURE);
			}
		});
		mClickViewSelectLocal = (Button)mAvatarClickView.findViewById(R.id.selectlocal);
		mClickViewSelectLocal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindow.dismiss();
				Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(picture, SELECT_LOCAL);

			}
		});
		mClickViewCancel = (Button)mAvatarClickView.findViewById(R.id.cancel);
		mClickViewCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mPopupWindow!=null&&mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				}
			}
		});
		mPopupWindow = new PopupWindow(mAvatarClickView,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,true);
		findViewById(R.id.editBtn).setOnClickListener(this);
		mEditSinagureLayout = (RelativeLayout)findViewById(R.id.edit_sinagure_rl);
		mSinagureCancelBtn = (Button)findViewById(R.id.cancel);
		mSinagureOkBtn = (Button)findViewById(R.id.ok);
		mSinagureCancelBtn.setOnClickListener(this);
		mSinagureOkBtn.setOnClickListener(this);
		mSinagureEditText = (EditText)findViewById(R.id.sinagure_content);
		mSinagureTextView = (TextView)findViewById(R.id.sinagure);
		findViewById(R.id.user_state_rl).setOnClickListener(this);
		mUserState = (TextView)findViewById(R.id.user_state);
		findViewById(R.id.homework_rl).setOnClickListener(this);
		findViewById(R.id.net_helper_rl).setOnClickListener(this);
		findViewById(R.id.result_rl).setOnClickListener(this);
		findViewById(R.id.lecture_rl).setOnClickListener(this);
		findViewById(R.id.net_helper_rl).setOnClickListener(this);
		findViewById(R.id.user_avatar).setOnClickListener(this);
	}
	private void updateUserState(){
		if (mCurUserLoginState==0) {
			mUserState.setText("在线");
			Util.showToast(MyMicroSchoolActivity.this, "当前状态: 在线");
		}else if (mCurUserLoginState==1) {
			mUserState.setText("离线");
			Util.showToast(MyMicroSchoolActivity.this, "当前状态: 离线");
		}
	}
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
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.editBtn:
			mEditSinagureLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.cancel:
			mEditSinagureLayout.setVisibility(View.GONE);
			break;
		case R.id.ok:
			mEditSinagureLayout.setVisibility(View.GONE);
			Util.showToast(this, "个性签名修改成功");
			mSinagureTextView.setText(mSinagureEditText.getText().toString());
			break;
		case R.id.user_state_rl:
			if (mCurUserLoginState==0) {
				mCurUserLoginState = 1;
			}else {
				mCurUserLoginState = 0;
			}
			updateUserState();
			break;
		case R.id.homework_rl:
			gotoWebview("家庭作业助手", "http://m.163.com");
			break;
		case R.id.net_helper_rl:
			startActivity(new Intent(MyMicroSchoolActivity.this,NetHelperActivity.class));
			break;
		case R.id.result_rl:
			gotoWebview("成绩查询助手", "http://sina.cn");
			break;
		case R.id.lecture_rl:
			gotoWebview("名师讲堂", "http://3g.cn");
			break;
		case R.id.user_avatar:
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.showAtLocation(findViewById(R.id.myschool_rl), Gravity.BOTTOM, 0, 0);
			mPopupWindow.update();
			break;
		default:
			break;
		}
	}
	private void gotoWebview(String title,String url){
		Intent intent = new Intent(MyMicroSchoolActivity.this, CommonWebviewActivity.class);
		Bundle b = new Bundle();
		b.putString(CommonWebviewActivity.TITLE, title);
		b.putString(CommonWebviewActivity.URL, url);
		intent.putExtras(b);
		startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==Activity.RESULT_OK) {
			
		if (requestCode==CAPTURE) {
			String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";	
			Toast.makeText(this, name, Toast.LENGTH_LONG).show();
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
		
			FileOutputStream b = null;
			String fileName = Environment.getExternalStorageDirectory()+"/"+name;

			try {
				b = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					b.flush();
					b.close();
					startPhotoClip(Uri.fromFile(new File(fileName)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}else if (requestCode==SELECT_LOCAL) {
			startPhotoClip(data.getData());
		}else if (requestCode==CLIP_PHOTO) {
			((ImageView)findViewById(R.id.user_avatar)).setImageBitmap((Bitmap)data.getExtras().get("data"));
		}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void startPhotoClip(Uri uri){
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 128);
		intent.putExtra("outputY", 128);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, CLIP_PHOTO);
	}
}
