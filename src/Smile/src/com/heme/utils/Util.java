package com.heme.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Util {
	private static final boolean PRINT_LOG = true;
    private static Toast mToast=null;
    //private static TextView mToastTextView=null;
    
	public static void log(String tag, String content) {
		if(PRINT_LOG) {
			String strTemp = " com.xunlei.kankan.Log :  ";
			strTemp += content;
			Log.d((" [ " + getMillTime()+ " ] " + tag + " thread_id : " + Thread.currentThread().getId()), strTemp);			
		}
	}
	
	public static boolean ensureDir(String path) {
		log(" Util " , " ensureDir path : " + path);
		if(null == path) {
			return false;
		}
		
		boolean ret = false;
		
		File file = new File(path);
		log(" Util " , " ensureDir file.exists() : " + file.exists() + " , file.isDirectory() : " + file.isDirectory());
		if(!file.exists() || !file.isDirectory()) {
			try{
			    ret = file.mkdirs();
			    log(" Util " , " ensureDir file.mkdirs() ret : " + ret);
			} catch(SecurityException  se) {
				se.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public static boolean ensureFile(String path) {
		if(null == path) {
			return false;
		}
		
		boolean ret = false;
		
		File file = new File(path);
		if(!file.exists() || !file.isFile()) {
			try{
			    file.createNewFile();
			    ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public static String getSDCardDir() {
		return Environment.getExternalStorageDirectory().getPath();
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
    
    public static Bitmap creatBitmap(String path) {
    	if(null == path) {
    		return null;
    	}
    	
    	File file = new File(path);
    	if(!file.exists()) {
    		return null;
    	}
    	
    	return BitmapFactory.decodeFile(path);
    }
    
    public static void saveBitmap(Bitmap bitmap, String path, String name) {
    	if(!path.endsWith("/")) {
    		path += "/";
    	}
        File file = new File(path+name);
        if(file.exists()) {
        	file.delete();
        }
        try {
			file.createNewFile();
        
			FileOutputStream out = null;
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    public static String getDate() {
//    	Calendar cd = Calendar.getInstance();
//    	int year = cd.get(Calendar.YEAR);
//    	int month = cd.get(Calendar.MONTH);
//    	int day = cd.get(Calendar.DAY_OF_MONTH);
    	
    	SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd");
    	return format.format(new Date());
    	//return String.format("%d.%d.%d", year, month, day);
    }
    
    public static String getMillTime(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSS");
    	return format.format(new Date());
    }
    public static String getDateTime() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    	return format.format(new Date());
    }
    
    public static String mToStr(int m) {
    	m = m/1000;
    	int hour = m/3600;
    	int minute = (m-3600*hour)/60;
    	int second = m%60;
    	return String.format("%02d:%02d:%02d", hour, minute, second);

    }
    
    public static String getLocalIpAddress()    
    {    
        try    
        {    
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)    
            {    
               NetworkInterface intf = en.nextElement();    
               for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)    
               {    
                   InetAddress inetAddress = enumIpAddr.nextElement();    
                   if (!inetAddress.isLoopbackAddress())    
                   {    
                       return inetAddress.getHostAddress().toString();    
                   }    
               }    
           }    
        }    
        catch (SocketException ex)    
        {    
        	Util.log("WifiPreference IpAddress", ex.toString());    
        }    
        return null;    
    }
    
    public static void showToast(Context context,String showMsg,int duration){
    	if(mToast==null){
    		mToast=new Toast(context);
    	}
    
    		TextView mToastTextView=new TextView(context);
    		mToastTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	    mToastTextView.setGravity(Gravity.CENTER);
    	    mToastTextView.setPadding(10, 3, 10, 3);
    		ShapeDrawable drawable=new ShapeDrawable(new RoundRectShape(new float[]{8,8,8,8,8,8,8,8},null,null));
    	    drawable.getPaint().setColor(Color.BLACK);
    	    drawable.setAlpha(200);
    	    mToastTextView.setBackgroundDrawable(drawable);
    
    	
    	mToastTextView.setText(showMsg);
    	mToast.setView(mToastTextView);
    	mToast.setDuration(duration);
    	mToast.show();
    }
    public static String xunleiBase64Decoder(String s){
    	int start=s.indexOf("thunder://");
    	String code=s.substring(start+"thunder://".length());
    	return String.valueOf(Base64.decode(code, Base64.DEFAULT));
    }
    
//    public static void startRefresh(Context context,ImageButton freshButton){
//    	Animation anim = AnimationUtils.loadAnimation(context, R.anim.refresh_anim); 
//    	
//    	freshButton.startAnimation(anim);
//    }
//    public static void stopRefresh(ImageButton freshButton){
//    	freshButton.clearAnimation();
//    }
    
    public static String getUrlPF(Activity ctx)
    {
    	DisplayMetrics dm = new DisplayMetrics(); 
    	ctx.getWindowManager().getDefaultDisplay().getMetrics(dm); 
    	int width = (int) (dm.widthPixels);
    	if( width< 480)
    	{
    		return "pf=320";
    	}
    	else if(width >= 480 && width< 540)
    	{
    		return "pf=480";
    	}
    	else if(width >=540)
    	{
    		return "pf=540";
    	}
    	else
    	{
    		return "pf=540";
    	}
    }
}
