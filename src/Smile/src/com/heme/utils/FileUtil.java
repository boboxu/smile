package com.heme.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

import com.heme.logic.module.Data.LoginRsp;

public class FileUtil {
	
	private static String FILEPATH = "smile/data/";
	
	public static void writeToFile(String filename,
			com.google.protobuf.GeneratedMessage message) {
		StringBuffer sb = new StringBuffer();
		// 暂时用写文件来测试
		FileOutputStream output = null;
		if (isSDCardExist()) 
		{
			sb.append(Environment.getExternalStorageDirectory().getPath());
		}
		else
		{
//			sb.append(get().getFilesDir().getAbsolutePath());
		}
		sb.append(FILEPATH);
		sb.append(filename);
		try {
			output = new FileOutputStream(filename);
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

	public static LoginRsp readLoginRspFromFile(String filename) {
		FileInputStream fs = readFsFromFilePath(filename);
		if ( fs != null) {
			try {
				return LoginRsp.parseFrom(fs);
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
		return Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState());
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
}
