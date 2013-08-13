package com.heme.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.StatFs;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.heme.smile.R;


public class Util {
	//private static final String TAG = "Util";
	
	public static void log(String tag, String content) {
		log(tag, content, false);
	}
	
	public static void log(String tag, byte []content) {
//		if(Config.CONFIG_UTIL_PRINT_LOG) {
//			String temp = new String(content);
//			log(tag, temp);
//		}
	}
	
	public static void log(String tag, String content, boolean force) {
		if(force) {
			Log.d(tag, content);
		} else {
//			if(Config.CONFIG_UTIL_PRINT_LOG) {
//				Log.d(tag, content);
//			}
		}
	}
	
	public static void createDirectory(String strPath) {
		File file =new File(strPath);
		if(!file .exists())     
		{     
		    file .mkdirs();
		}
	}
	
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		if(!file.exists() || file.isDirectory()) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isDirExist(String path) {
		File file = new File(path);
		if(!file.exists() || file.isFile()) {
			return false;
		}
		
		return true;
	}
	
	public static String md5(String key) {
		try {
			char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] buf = key.getBytes();
			md.update(buf, 0, buf.length);
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder(32);
			for (byte b : bytes) {
				sb.append(hex[((b >> 4) & 0xF)]).append(hex[((b >> 0) & 0xF)]);
			}
			key = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	
	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					"com.heme.smile", 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}
	
	public static String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					"com.heme.smile", 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verName;	

	}
	
    public static int px2dip(Context context, float pxValue){ 
    	final float scale = context.getResources().getDisplayMetrics().density; 
    	return (int)(pxValue / scale + 0.5f); 
    } 
    
    public static int dip2px(Context context, float dipValue){ 
    	final float scale = context.getResources().getDisplayMetrics().density; 
    	return (int)(dipValue * scale + 0.5f); 
	}
    
    public static String getSDCardDir() {
		return Environment.getExternalStorageDirectory().getPath();
	}
    
	public static boolean isSDCardExist() {
		return Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState());
	}
	
	public static long getFileSize(String path) {
		File file = new File(path);
		return getFileSize(file);
	}
	
	public static long getFileSize(File file) {
		if(!file.exists()) {
			return 0;
		}
		if(file.isFile()) {
			return file.length();
		}
		
		long size = 0;
		File[] list = file.listFiles();
		for(File tmp: list) {
			size += getFileSize(tmp);
		}
		
		return size;
	}
	
	public static boolean deleteDir(String path) {
		 File f = new File(path);
		 if(!f.exists() || !f.isDirectory()){
			 return false;
		 }
		 
		 File[] fileList = f.listFiles();
		 for(File file: fileList) {
			 if(file.isFile()) {
				 file.delete();
			 } else if(file.isDirectory()) {
				 deleteDir(file.getAbsolutePath());
			 }
		 }
		 
		 f.delete();
		 return true;
	}
	
    public static long getAvailableExternalMemorySize() {    
        File path = Environment.getExternalStorageDirectory();    
        StatFs stat = new StatFs(path.getPath());    
        long blockSize = stat.getBlockSize();    
        long availableBlocks = stat.getAvailableBlocks();    
        return availableBlocks * blockSize;      
    }    
        
    public static long getTotalExternalMemorySize() {      
        File path = Environment.getExternalStorageDirectory();   
        Environment.getExternalStorageState();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();   
        return totalBlocks * blockSize;  
    }
    
    public static String getDateString(long ms) {
    	return getDateString(ms, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String getDateString(long ms, String format) {
    	Date curDate   =  new Date(ms);
		return getDateString(curDate, format);
    }
    
    public static String getNowDateString() {
    	return getNowDateString("yyyy-MM-dd HH:mm:ss");
    }
    
    public static String getNowDateString(String format) {
		Date curDate   =  new Date(System.currentTimeMillis());
		return getDateString(curDate, format);
    }
    
    public static String getDateString(Date date) {
    	return getDateString(date, "yyyy-MM-dd HH:mm:ss");
    }
    public static void showToast(Context ctx,String content){
		Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
	}
	
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
    public static String getDateString(Date date, String format) {
    	SimpleDateFormat   formatter = new SimpleDateFormat(format);
    	return formatter.format(date);
    }
    
    public static String byteConvert(long byteNum)  {
    	double f = byteNum / (1024 * 1024 * 1024 * 1.0);
    	String res;
    	if(f > 1.0) {
    		//大于1G
    		res = new DecimalFormat("0.00").format(f);
    		res += "GB";
    	} else {
    		//小于1G
    		f = byteNum / (1024 * 1024 * 1.0);
    		if(f > 1.0) {
    			//大于1M
    			res = new DecimalFormat("0.00").format(f);
    			res += "MB";
    		} else {
    			//小于1M
    			f = byteNum / (1024 * 1.0); 
    			if(f > 1.0) {
    				//大于1K
    				res = new DecimalFormat("0").format(f);
    				res += "KB";
    			} else {
    				//小于1K
    				res = byteNum + "B";
    			}
    		}
    	}
    	return res;
    }
    
    public static String byteConvertEx(long byteNum)  {
    	double f = byteNum / (1024 * 1024 * 1024 * 1.0);
    	String res;
    	if(f > 1.0) {
    		//大于1G
    		res = new DecimalFormat("0.0").format(f);
    		res += "G";
    	} else {
    		//小于1G
    		f = byteNum / (1024 * 1024 * 1.0);
    		if(f > 1.0) {
    			//大于1M
    			res = new DecimalFormat("0.0").format(f);
    			res += "M";
    		} else {
    			//小于1M
    			f = byteNum / (1024 * 1.0); 
    			if(f > 1.0) {
    				//大于1K
    				res = new DecimalFormat("0").format(f);
    				res += "K";
    			} else {
    				//小于1K
    				res = byteNum + "B";
    			}
    		}
    	}
    	return res;
    }
    
    public static boolean checkEmail(String strAddress)
    {
    	String reg = "[0-9a-zA-Z][0-9a-zA-Z_.]*[^_]@[0-9a-zA-Z]+(\\.[0-9a-zA-Z]+)*\\.[0-9a-zA-Z]+$";
    	if(!strAddress.matches(reg))
    	{
    		return false;
    	}
    	return true;
    }
    
	public static void hiddenInput(Context ctx,View v){
		InputMethodManager inputMethodManager = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
	public static boolean copy(String   from,   String   to)     
	  {   
	      try   
	      {   
	          String   toPath   =   to.substring(0,to.lastIndexOf("/"));   //提取文件路径   
	          File   f   =   new   File(toPath);   //建立文件目录路   
	          if(!f.exists())   
	              f.mkdirs();   
	            
	          BufferedInputStream   bin   =   new   BufferedInputStream(new   FileInputStream(from));   
	          BufferedOutputStream   bout   =   new   BufferedOutputStream(new   FileOutputStream(to));   
	          int   c;   
	          while((c=bin.read())   !=   -1)   //复制   
	              bout.write(c);   
	          bin.close();   
	          bout.close();   
	          return   true;   
	      }   
	      catch(Exception   e)   
	      {   
	          e.printStackTrace();   
	          return   false;   
	      }   
	  }   
	
	public static void showToast(Toast toast,String str){
		try{
			toast.setText(str);
			toast.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isCharFiltered(char ch) {
		char table[] = {'/', '\\', '<', '>', ':', '：', '*', '?', '？', '"', '\n', '|'};
		for(char c : table) {
			if(c == ch)
				return true;
		}
		return false;
	}
	
	public static final class FileNameInputFilter implements InputFilter{
		
		private final String TAG = "FileNameInputFilter";
		@Override
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			Util.log(TAG, "source = " + source + " start = " + start + " end = " + end + 
					" dstart =" + dstart + " dend = " + dend);
			String str = source.toString();
			String dst = new String();
			boolean show = false;
			for(int i =0 ; i < str.length(); i++){
				char c = str.charAt(i);
				if(c !='/' && 
						c !='\\' && 	
						c !='<' && 	 
						c !='>' && 	
						c !=':' &&
						c !='*' &&
						c !='?' &&
						c !='"' &&
						c !='\n' &&
						c !='|' 
						){
					dst+=c;
				}else{
					show = true;
				}
			}
			
			return dst;
		}
		
	}
	
	public static class TextLengthWatcher implements TextWatcher
	{
		private Context mCtx;
		private EditText mEditText;
		private int mMaxLength;
		private Toast mToast = null;
		
		public TextLengthWatcher(Context ctx, EditText editText, int maxLength)
		{
			mCtx = ctx;
			mEditText = editText;
			mMaxLength = maxLength;
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after)
		{
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count)
		{
			
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			int nSelEnd = mEditText.getSelectionEnd();
			if(nSelEnd == 0)
			{
				return;
			}
			boolean isOverMaxLength = s.length() > mMaxLength ? true : false;
			if(isOverMaxLength)
			{
				if(mToast == null)
				{
					mToast = Toast.makeText(mCtx, "超过最多字符限制！", Toast.LENGTH_SHORT);
				}
				mToast.show();
				String tmp = mEditText.getText().toString().substring(0, mMaxLength);
				mEditText.setText(tmp);
				mEditText.setSelection(mEditText.getText().length());
			}
		}
		
	}
    public static void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0,
                0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        
        if (lpHeight > 0) { 
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);   
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
	
    public static String getTimePastDesc(String strtime){
    	SimpleDateFormat   formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			return getTimePastDesc(formatter.parse(strtime).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return strtime;
		}    
    }
    
    public static String getTimePastDesc(long thetime){
    	String str = "";
    	long cur = System.currentTimeMillis();
    	cur -= thetime;
    	if(cur<0)
    		cur = 0;
    	cur /= 1000;
    	if(cur < 60){
    		str = cur+" 秒前";
    		return str;
    	}
    	cur /= 60;
    	if(cur < 60){
    		str = cur+" 分钟前";
    		return str;
    	}
    	cur /= 60;
    	if(cur < 24){
    		str = cur+" 小时前";
    		return str;
    	}
    	cur /= 24;
    	str = getDateString(thetime, "yy-MM-dd");
    	
    	/*if(cur < 365){
    		str = cur+" 天前";
    		return str;
    	}
    	cur /= 365;
    	str = cur+" 年前";  */  	
    	
    	return str;
    }
    
    public static DisplayMetrics getScreenDim(Activity ctx){
		DisplayMetrics dm = new DisplayMetrics();		
		ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;	
    }
    
    public static int getDefaultFileIcon70WH(String filename){
		switch(FileHandler.getFileTypeByName(filename)){
		case FileHandler.FILE_TYPE_MUSIC:
			return R.drawable.audio_default;
		case FileHandler.FILE_TYPE_SOFTWARE:
			return R.drawable.software_default;
		case FileHandler.FILE_TYPE_TEXT:
			return R.drawable.doc_default;
		case FileHandler.FILE_TYPE_VIDEO:
			return R.drawable.video_default;
		case FileHandler.FILE_TYPE_PICTURE:
			return R.drawable.pic_default;
		case FileHandler.FILE_RAR_CAN_PREVIEW:
			return R.drawable.zip_default;
		default:
			return R.drawable.unknown_file_default;
		}
    }
    public static int getDefaultFileIcon150WH(String filename){
		switch(FileHandler.getFileTypeByName(filename)){
		case FileHandler.FILE_TYPE_MUSIC:
			return R.drawable.audio_default_150;
		case FileHandler.FILE_TYPE_SOFTWARE:
			return R.drawable.software_default_150;
		case FileHandler.FILE_TYPE_TEXT:
		case FileHandler.FILE_DOC_CAN_PREVIEW:
			return R.drawable.doc_default_150;
		case FileHandler.FILE_TYPE_VIDEO:
			return R.drawable.video_default_150;
		case FileHandler.FILE_TYPE_PICTURE:
			return R.drawable.pic_default_150;
		case FileHandler.FILE_RAR_CAN_PREVIEW:
			return R.drawable.zip_default_150;
		default:
			return R.drawable.unknown_file_default_150;
		}
    }
    
    public static int getFiletypeRes(String filename){
		int type = FileHandler.getFileTypeByName(filename);
		switch(type){
			case FileHandler.FILE_TYPE_MUSIC:
				return R.drawable.filetype_audio_gray;
			case FileHandler.FILE_TYPE_SOFTWARE:
				return R.drawable.filetype_software_gray;
			case FileHandler.FILE_TYPE_TEXT:
			case FileHandler.FILE_DOC_CAN_PREVIEW:
				return R.drawable.filetype_txt_gray;
			case FileHandler.FILE_TYPE_VIDEO:
				return R.drawable.filetype_video_gray;
			case FileHandler.FILE_TYPE_PICTURE:
				return R.drawable.filetype_pic_gray;
			case FileHandler.FILE_RAR_CAN_PREVIEW:
				return R.drawable.filetype_zip_gray;
			default:
				return R.drawable.filetype_unknown_gray;
		}	   	
    }
    
	public static String getFirstClassDomainName(String url)
	{
		String ret = url;
		if(null != ret)
		{
			if(!ret.trim().equals(""))
			{
				String temp;
				ret = ret.replace("http://", "");
				int index = ret.indexOf("/");
				if(index != -1)
				{
					ret = ret.substring(0, index);
				}
				try
				{
					temp = ret.substring(ret.lastIndexOf("."));
					ret  = ret.substring(0, ret.lastIndexOf("."));
					String temp1 = ret.substring(ret.lastIndexOf(".") + 1);
					
					ret = temp1 + temp;
				}catch(Exception e)
				{
					
				}
			}
		}
		return ret;
	}
}
