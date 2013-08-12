package com.heme.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

import com.heme.logic.module.Data.LoginReq;
import com.heme.smile.SmileApplication;

public class FileUtil {

	private static String FILEPATH = "/smile/data/";

	public static void writeToFile(String filename,
			com.google.protobuf.GeneratedMessage message, boolean append) {
		String fullPath = getFullPathWithFileName(filename);
		ensureFile(fullPath);
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(fullPath, append);
			output.write(message.toByteArray());
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public static LoginReq readLoginRspFromFile(String filename) {
		FileInputStream fs = readFsFromFilePath(filename);
		if (fs != null) {
			try {
				return LoginReq.parseFrom(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	private static FileInputStream readFsFromFilePath(String path) {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fs;
	}

	public static boolean isSDCardExist() {
		return Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment
				.getExternalStorageState());
	}

	private static boolean ensureFile(String path) {
		if (null == path) {
			return false;
		}

		boolean ret = false;

		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			try {
				file.createNewFile();
				ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

	public static boolean ensureDir(String path) {
		if(null == path) {
			return false;
		}
		
		boolean ret = false;
		
		File file = new File(path);
		if(!file.exists() || !file.isDirectory()) {
			try{
			    ret = file.mkdirs();
			} catch(SecurityException  se) {
				se.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public static String getFullAppDataPath()
	{
		StringBuffer sb = new StringBuffer();
		if (isSDCardExist()) {
			sb.append(Environment.getExternalStorageDirectory().getPath());
		} else {
			sb.append(SmileApplication.APPPATH);
		}
		sb.append(FILEPATH);
		return sb.toString();
	}
	
	private static String getFullPathWithFileName(String filename) {
		StringBuffer sb = new StringBuffer();
		sb.append(getFullAppDataPath());
		sb.append(filename);
		return sb.toString();
	}

	public static boolean deleteFile(String filename) {
		String fullpath = getFullPathWithFileName(filename);
		File file = new File(fullpath);
		if (!file.exists() || !file.isFile()) {
			//本就没有
			return true;
		}
		else
		{
			return file.delete();
		}
	}
}
