package com.heme.smile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heme.utils.FileHandler;
import com.heme.utils.Util;



public class FileExplorerActivity extends Activity {

	private static final String TAG = "FileExplorerActivity";
	public static final String DATA_LOCALPATH = "Path";
	
	public static final int TYPE_DIR = 0;
	public static final int TYPE_FILE = 1;
	
	private List<Map<String, Object>> mData;
	private String mDir = Environment.getExternalStorageDirectory().getAbsolutePath();
	private ListView mListView;
	private MyAdapter mAdapter;
	private Button mBtnBack;
	private Button mBtnOk;
	
	private ImageView mCurCheckedIcon;
	
	private int mCurSelected = -1;
	
	private boolean mIsRootDir = true;
	private File mCurFile;		//当前目录文件
	
	private HashMap<String, ImageView> mHashMap = new HashMap<String, ImageView>();
	private ArrayList<String> selectedList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fileexplorerwrapper);
		
		mBtnBack = (Button)findViewById(R.id.fileexplorer_btn_back);
		mBtnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//	finish();
				backDir();
			}
		});
		
		mBtnOk = (Button)findViewById(R.id.fileexplorer_btn_ok);
		mBtnOk.setVisibility(View.GONE);
		mBtnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mCurSelected == -1)
				{
					Toast.makeText(FileExplorerActivity.this, "请选择上传文件！", Toast.LENGTH_SHORT).show();
				}
				else
				{
//					finishWithResult();
				}
			}
		});
		mData = getData();
		mListView = (ListView)findViewById(R.id.file_explorer_list);
				
		mAdapter = new MyAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				onListItemClick((ListView)parent, view, position, id);
			}
		});
		mListView.setFadingEdgeLength(0);
		mListView.setScrollingCacheEnabled(false);
		mListView.setDivider(null);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private List<Map<String, Object>>getData()
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		File f = new File(mDir);
		mCurFile = f;
		File[] files = f.listFiles();
		
		if(!mDir.equals("/sdcard"))
		{		
//			map = new HashMap<String, Object>();
//			map.put("title", "返回到上一级目录");
//			map.put("info", f.getParent());
//			map.put("fileName", "");
//			map.put("fileSize", "");
//			map.put("type",TYPE_DIR);
//			list.add(map);
		}
		else 
		{
		//	mBtnBack.setVisibility(View.VISIBLE);
		}
	
		if(files != null)
		{
			for(int i = 0; i < files.length; i++)
			{
				if(files[i].isDirectory())
				{
					map = new HashMap<String, Object>();
					
					File[] tempFiles = files[i].listFiles();
					if(tempFiles == null)
					{
					}
					else
					{
						if(tempFiles.length == 0)
						{
							continue;
						}
						map.put("fileSize", tempFiles.length + "个文件");
					}
					map.put("title", files[i].getName());
					map.put("info", files[i].getPath());
					map.put("fileName", files[i].getName());
					map.put("type",TYPE_DIR);
					list.add(map);
				}
			}
			
			for(int i = 0; i < files.length; i++)
			{
				if(!files[i].isDirectory())
				{
					map = new HashMap<String, Object>();
					map.put("title", files[i].getName());
					map.put("info", files[i].getPath());
					map.put("fileName", files[i].getName());
					map.put("fileSize", Util.byteConvert(files[i].length()));					
					map.put("type",TYPE_FILE);
					list.add(map);
				}
			}
		}
		return list;
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		mDir = (String)mData.get(position).get("info");
		
		if((Integer)mData.get(position).get("type") == TYPE_DIR)
		{
			mIsRootDir = false;
			mCurSelected = -1;
			mDir = (String)mData.get(position).get("info");
			mData = getData();
			mAdapter.notifyDataSetChanged();
		}
		else
		{
			String pathString = (String)mData.get(position).get("info");
			if (FileHandler.getFileTypeByName(pathString)!=FileHandler.FILE_TYPE_VIDEO) {
				Util.showToast(this, "只支持选择视频文件");
				return;
			}else {
				finishWithResult(pathString);
			}
			
//			mCurCheckedIcon = (ImageView)v.findViewById(R.id.fileexplore_checked_icon);
//			if(mCurCheckedIcon.getVisibility() == View.VISIBLE)
//			{
//				String pathString = (String)mData.get(position).get("info");
//				if (selectedList!=null&&selectedList.contains(pathString)) {
//					int index = getIndex(pathString);
//					selectedList.remove(index);
//				}
//				mCurCheckedIcon.setVisibility(View.GONE);
//				mCurSelected = -1;
//				return;
//			}
//			
//			if(mCurSelected == -1)
//			{
//				mCurSelected = position;
//			}
//			else
//			{
//				ImageView checkedIcon = mHashMap.get(mData.get(mCurSelected).get("fileName"));
////				if(checkedIcon != null)
////				{
////					checkedIcon.setVisibility(View.GONE);
////				}
//				mCurSelected = position;	
//			}
//			String pathString = (String)mData.get(position).get("info");
//			if (!selectedList.contains(pathString)) {
//				selectedList.add(pathString);
//			}
//			mCurCheckedIcon.setVisibility(View.VISIBLE);

		}
	}
	
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
			if (KeyEvent.ACTION_DOWN == event.getAction()) {
				backDir();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
	
	private void backDir()
	{
		if(mIsRootDir)
		{
			finish();
		}
		else
		{
			mCurSelected = -1;
			mDir = mCurFile.getParent();
			if(mDir.equals("/sdcard"))
			{
				mIsRootDir = true;
			}
			mData = getData();
			mAdapter.notifyDataSetChanged();
		}
	}
	public final class ViewHolder
	{
		public ImageView mCheckedIcon;
		public ImageView mFileIcon;
		public TextView mFileName;
		public TextView mFileSize;
		public ImageView mFileTypeIcon;
	}
	
	public class MyAdapter extends BaseAdapter
	{
		private LayoutInflater mInflater;
		
		public MyAdapter(Context context)
		{
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			if (mData==null||mData.size()==0) {
				return null;
			}
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null)
			{
				holder = new ViewHolder();

				convertView = mInflater.inflate(R.layout.fileexplorer, null);
				holder.mCheckedIcon = (ImageView)convertView.findViewById(R.id.fileexplore_checked_icon);
				holder.mFileIcon = (ImageView)convertView.findViewById(R.id.fileexplore_fileicon);
				holder.mFileName = (TextView)convertView.findViewById(R.id.fileexplore_filename);
				holder.mFileSize = (TextView)convertView.findViewById(R.id.fileexplore_filesize);
				holder.mFileTypeIcon = (ImageView)convertView.findViewById(R.id.fileexplore_filetype_icon);						
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder)convertView.getTag();
				if(holder == null)
				{
					holder = new ViewHolder();
					convertView = mInflater.inflate(R.layout.fileexplorer, null);
					holder.mCheckedIcon = (ImageView)convertView.findViewById(R.id.fileexplore_checked_icon);
					holder.mFileIcon = (ImageView)convertView.findViewById(R.id.fileexplore_fileicon);
					holder.mFileName = (TextView)convertView.findViewById(R.id.fileexplore_filename);
					holder.mFileSize = (TextView)convertView.findViewById(R.id.fileexplore_filesize);
					holder.mFileTypeIcon = (ImageView)convertView.findViewById(R.id.fileexplore_filetype_icon);						
					convertView.setTag(holder);
				}
			}
			Map<String, Object> map = mData.get(position);
			if (TYPE_DIR!=(Integer)map.get("type")) {
				String pathString = (String)map.get("info");
				if (selectedList.contains(pathString)) {
					holder.mCheckedIcon.setVisibility(View.VISIBLE);
				}else {
					holder.mCheckedIcon.setVisibility(View.GONE);
				}
			}else {
				holder.mCheckedIcon.setVisibility(View.GONE);
			}
			holder.mFileName.setText((String) mData.get(position).get("fileName"));
			holder.mFileSize.setText((String) mData.get(position).get("fileSize"));
			if(TYPE_DIR == (Integer) mData.get(position).get("type"))
			{
				holder.mFileIcon.setImageResource(R.drawable.fb_folder);
				holder.mFileTypeIcon.setImageResource(R.drawable.filetype_folder_gray);
			}
			else
			{
				int type = FileHandler.getFileTypeByName((String) mData.get(position).get("title"));
				switch(type)
				{
				case FileHandler.FILE_TYPE_MUSIC:
					holder.mFileIcon.setImageResource(R.drawable.filetype_audio_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_audio_gray);
					break;
				case FileHandler.FILE_TYPE_OTHER:
					holder.mFileIcon.setImageResource(R.drawable.filetype_unknown_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_unknown_gray);
					break;
				case FileHandler.FILE_TYPE_SOFTWARE:
					holder.mFileIcon.setImageResource(R.drawable.filetype_software_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_software_gray);
					break;
				case FileHandler.FILE_TYPE_TEXT:
					holder.mFileIcon.setImageResource(R.drawable.filetype_txt_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_txt_gray);
					break;
				case FileHandler.FILE_TYPE_VIDEO:
					holder.mFileIcon.setImageResource(R.drawable.filetype_video_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_video_gray);
					break;
				case FileHandler.FILE_TYPE_PICTURE:
					holder.mFileIcon.setImageResource(R.drawable.filetype_pic_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_pic_gray);
					break;
				default:
					holder.mFileIcon.setImageResource(R.drawable.filetype_unknown_big);
					holder.mFileTypeIcon.setImageResource(R.drawable.filetype_unknown_gray);
				}		
			}
			mHashMap.put((String) mData.get(position).get("fileName"), holder.mCheckedIcon);
			return convertView;
		}
		
		
	}


	private void finishWithResult(String pathString)
	{
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString(SingleChatActivity.SELECT_VIDEO_PATH, pathString);
		intent.putExtras(bundle);
		setResult(SingleChatActivity.SELECT_VIDEO, intent);
		finish();
	}
	public int getIndex(String path){
		int result = -1;
		for (int i = 0; i < selectedList.size(); i++) {
			String p = selectedList.get(i);
			if (p.equals(path)) {
				result = i;
				break;
			}
		}
		return result;
	}
}
