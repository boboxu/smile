package com.heme.smile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

public class FileUtil {
	public static String saveBitmapToSdCard(Bitmap bitmap,String path){
		FileOutputStream b = null;
		String name = System.currentTimeMillis()+".jpg";
		String fileName = path+"/"+name;
		String savePath = null;
		try {
			b = new FileOutputStream(fileName);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				bitmap.recycle();
				bitmap = null;
				System.gc();
				b.flush();
				b.close();
				savePath = fileName;
			} catch (IOException e) {
				savePath = null;
				e.printStackTrace();
			}
		}
		return savePath;
	}
}
