package com.heme.smile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.widget.Toast;

public class Util {
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


}
