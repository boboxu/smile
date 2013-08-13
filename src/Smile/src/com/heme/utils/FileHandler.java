package com.heme.utils;

import java.io.File;
import java.net.URLEncoder;
import java.util.Hashtable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;


public class FileHandler {
	
	private static final String TAG = "FileHanlder";
	
	private static FileHandler mInstance = null;
	
	private Hashtable<String, String> mMimeTable = null;
	
	private MimeTypeMap mMimeMap = null;
	
    public static final int FILE_TYPE_OTHER = 0;
    public static final int FILE_TYPE_VIDEO = FILE_TYPE_OTHER + 1;
    public static final int FILE_TYPE_MUSIC = FILE_TYPE_VIDEO + 1;
    public static final int FILE_TYPE_TEXT = FILE_TYPE_MUSIC + 1;
    public static final int FILE_TYPE_SOFTWARE = FILE_TYPE_TEXT + 1;
    public static final int FILE_TYPE_PICTURE = FILE_TYPE_SOFTWARE + 1;
    public static final int FILE_TYPE_COUNT = FILE_TYPE_PICTURE + 1;
    public static final int FILE_DOC_CAN_PREVIEW = FILE_TYPE_COUNT+1;
    public static final int FILE_RAR_CAN_PREVIEW = FILE_DOC_CAN_PREVIEW+1;
    public static final int FILE_GIF_CAN_PREVIEW = FILE_RAR_CAN_PREVIEW+1;
    
    
    public static int getFileTypeByName(String fileName){
    	int type = FILE_TYPE_OTHER;
    	
		/* ��ͨ���� */
    	String picture_class[] = {"png","jpeg","bmp","jpg","icon"};
		String video_class[] = {"3gp", "mp4", "rmvb", "rm", "avi", "wmv", "mov", "asf", "mpeg", "mpg", "kv", "flv", "swf", "3g2", "dat", "m4v", "asx", "dv","mkv","ts","xv","xlmv"};
		String music_class[] = {"mp3","MP3","Mp3","mP3","wma","ra","ram","wav","ape","flac","acc","ogg","aac"};
		String book_class[] = {"txt", "umd", "chm",   "excel", "html"};
		String sofware_class[] = {"sis", "sisx", "jar", "cab", "exe", "rpm", "jad", "mrp","nes", "mgz", "apk"};
		String preview_class[] = {"doc","docx","pdf","ppt","pptx","xls","xlsx"};
		String preview_rar[] = {"rar","7z","zip","torrent"};
		String gif_class[] = {"gif"};
		if (fileName==null) {
			return FileHandler.FILE_TYPE_OTHER;
		}else if (fileName!=null&&fileName.lastIndexOf(".")==-1) {
			return FileHandler.FILE_TYPE_OTHER;
		}
		int dotIndex = fileName.lastIndexOf(".")+1;
		String dest_name = fileName.substring(dotIndex,fileName.length()).toLowerCase();
		int index = 0;
		//���video_class
		for(index = 0; index < video_class.length; ++index) {
			if(0 == dest_name.compareTo(video_class[index])) {
				return FILE_TYPE_VIDEO;
			}
		}
		//���music_class
		for(index = 0; index < music_class.length; ++index) {
			if(0 == dest_name.compareTo(music_class[index])) {
				return FILE_TYPE_MUSIC;
			}
		}
		//���book_class
		for(index = 0; index < book_class.length; ++index) {
			if(0 == dest_name.compareTo(book_class[index])) {
				return FILE_TYPE_TEXT;
			}
		}
		//���sofware_class
		for(index = 0; index < sofware_class.length; ++index) {
			if(0 == dest_name.compareTo(sofware_class[index])) {
				return FILE_TYPE_SOFTWARE;
			}
		}
		for(index = 0; index < picture_class.length; ++index) {
			if(0 == dest_name.compareTo(picture_class[index])) {
				return FILE_TYPE_PICTURE;
			}
		}
		for(index = 0; index < preview_class.length; ++index) {
			if(0 == dest_name.compareTo(preview_class[index])) {
				return FILE_DOC_CAN_PREVIEW;
			}
		}
		for(index = 0; index < preview_rar.length; ++index) {
			if(0 == dest_name.compareTo(preview_rar[index])) {
				return FILE_RAR_CAN_PREVIEW;
			}
		}
		for(index = 0; index < gif_class.length; ++index) {
			if(0 == dest_name.compareTo(gif_class[index])) {
				return FILE_GIF_CAN_PREVIEW;
			}
		}
    	return type;
    }
	
	public static String getParentPath(String filePath) {
		if(null == filePath || "/".equals(filePath)) {
			return filePath;
		}
		if(filePath.startsWith("/")){
			filePath = filePath.substring(1);
		}
		if(filePath.endsWith("/")){
			filePath = filePath.substring(0,filePath.length()-1);
		}
		int lastIndex = filePath.lastIndexOf('/');
		if(-1 == lastIndex) {
			return null;
		}		
		filePath = filePath.substring(0, lastIndex);
		filePath = filePath.startsWith("/")? filePath: "/"+filePath;
		filePath = filePath.endsWith("/")? filePath: filePath+"/";
		return filePath;		
	}
	
	public static String getLastComponent(String path) {
		path = path.endsWith("/")? path.substring(0, path.length()-1): path;
		
		int index = path.lastIndexOf('/');
		if(-1 == index) {
			return null;
		}
		
		return path.substring(index+1);
	}
	
	public static String getSubPath(String parentPath, String subPath) {
		parentPath = parentPath.startsWith("/")? parentPath: "/"+parentPath;
		
		boolean parent = parentPath.endsWith("/");
		boolean sub = subPath.startsWith("/");
		if(parent ^ sub) {
			parentPath = parentPath + subPath;
		} else {
			parentPath = parentPath + subPath.substring(1);
		}
		
		return parentPath;
	}
	
	public static FileHandler getInstance() {
		if(null == mInstance) {
			mInstance = new FileHandler();
		}
		
		return mInstance;
	}
	/**
	 * ͨ������·���õ��ļ����ļ���
	 * @param dpath  ����·�� 
	 * @return
	 */
	public static String getFilenameByDPath(String dpath){
		if (dpath.lastIndexOf("/")!=-1) {
			String fname = dpath.substring(dpath.lastIndexOf("/")+1);
			if (fname.lastIndexOf(".")!=-1) {
				int index = dpath.lastIndexOf("/")+1;
				return dpath.substring(index,dpath.lastIndexOf("."));
			}else {
				return fname;
			}
		}else {
			return dpath;
		}
		
	}
	
	
	private FileHandler() {
		mMimeMap = MimeTypeMap.getSingleton();
		
		mMimeTable = new Hashtable<String, String>();

		mMimeTable.put(".3gp", "video/3gpp");
		mMimeTable.put(".amr", "audio/amr"); 
		mMimeTable.put(".apk", "application/vnd.android.package-archive");
		mMimeTable.put(".asf", "video/x-ms-asf");
		mMimeTable.put(".avi", "video/x-msvideo");
		mMimeTable.put(".bin", "application/octet-stream");
		mMimeTable.put(".bmp", "image/bmp");
		mMimeTable.put(".c", "text/plain");
		mMimeTable.put(".class", "application/octet-stream");
		mMimeTable.put(".conf", "text/plain");
		mMimeTable.put(".cpp", "text/plain");
		mMimeTable.put(".doc", "application/msword");
		mMimeTable.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		mMimeTable.put(".exe", "application/octet-stream");
		mMimeTable.put(".gif", "image/gif");
		mMimeTable.put(".gtar", "application/x-gtar");
		mMimeTable.put(".gz", "application/x-gzip");
		mMimeTable.put(".h", "text/plain");
		mMimeTable.put(".htm", "text/html");
		mMimeTable.put(".html", "text/html");
		mMimeTable.put(".jar", "application/java-archive");
		mMimeTable.put(".java", "text/plain");
		mMimeTable.put(".jpeg", "image/jpeg");
		mMimeTable.put(".jpg", "image/jpeg");
		mMimeTable.put(".js", "application/x-javascript");
		mMimeTable.put(".log", "text/plain");
		mMimeTable.put(".m3u", "audio/x-mpegurl");
		mMimeTable.put(".m4a", "audio/mp4a-latm");
		mMimeTable.put(".m4b", "audio/mp4a-latm");
		mMimeTable.put(".m4p", "audio/mp4a-latm");
		mMimeTable.put(".m4u", "video/vnd.mpegurl");
		mMimeTable.put(".m4v", "video/x-m4v");
		mMimeTable.put(".mid", "audio/mid");
		mMimeTable.put(".mov", "video/quicktime");
		mMimeTable.put(".mp2", "audio/x-mpeg");
		mMimeTable.put(".mp3", "audio/x-mpeg");
		mMimeTable.put(".mp4", "video/mp4");
		mMimeTable.put(".mpc", "application/vnd.mpohun.certificate");
		mMimeTable.put(".mpe", "video/mpeg");
		mMimeTable.put(".mpeg", "video/mpeg");
		mMimeTable.put(".mpg", "video/mpeg");
		mMimeTable.put(".mpg4", "video/mp4");
		mMimeTable.put(".mpga", "audio/mpeg");
		mMimeTable.put(".msg", "application/vnd.ms-outlook");
		mMimeTable.put(".ogg", "audio/ogg");
		mMimeTable.put(".pdf", "application/pdf");
		mMimeTable.put(".png", "image/png");
		mMimeTable.put(".pps", "application/vnd.ms-powerpoint");
		mMimeTable.put(".ppt", "application/vnd.ms-powerpoint");
		mMimeTable.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		mMimeTable.put(".prop", "text/plain");
		mMimeTable.put(".rar", "application/x-rar-compressed");
		mMimeTable.put(".rc", "text/plain");
		mMimeTable.put(".rmvb", "audio/x-pn-realaudio");
		mMimeTable.put(".rtf", "application/rtf");
		mMimeTable.put(".sh", "text/plain");
		mMimeTable.put(".tar", "application/x-tar");
		mMimeTable.put(".tgz", "application/x-compressed");
		mMimeTable.put(".txt", "text/plain");
		mMimeTable.put(".wav", "audio/x-wav");
		mMimeTable.put(".wma", "audio/x-ms-wma");	
		mMimeTable.put(".wmv", "audio/x-ms-wmv");		
		mMimeTable.put(".wps", "application/vnd.ms-works");
//		mMimeTable.put(".xml", "text/xml");
		mMimeTable.put(".xml", "text/plain");
		mMimeTable.put(".xls", "application/vnd.ms-excel");
		mMimeTable.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		mMimeTable.put(".z", "application/x-compress");
		mMimeTable.put(".zip", "application/zip");
		mMimeTable.put(".flv", "flv-application/octet-stream");
		mMimeTable.put("", "*/*");
	}
}
