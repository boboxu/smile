package com.heme.smile.util;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;

import com.heme.logic.common.Constans;
import com.heme.smile.R;

public class BitmapUtil {
	public static Bitmap createBusinessCard(Context ctx,String avatarPath,String nickname,String userid){
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setSubpixelText(true);
		paint.setDither(true);
		paint.setTextSize(25);
		Bitmap tmpBgBitmp = null;
		tmpBgBitmp = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.business_card_bg);
		Matrix bgMatrix = new Matrix();
		float bg_w_scale = (float)Constans.BUSINESS_CARD_WIDTH/tmpBgBitmp.getWidth();
		float bg_h_scale = (float)Constans.BUSINESS_CARD_HEIGHT/tmpBgBitmp.getHeight();
		bgMatrix.postScale(bg_w_scale, bg_h_scale);
		Bitmap bgBitmap = createMutableBitmap(tmpBgBitmp, 0, 0, tmpBgBitmp.getWidth(), tmpBgBitmp.getHeight(), bgMatrix, true);
		if (tmpBgBitmp!=null&&!tmpBgBitmp.isRecycled()) {
			tmpBgBitmp.recycle();
			tmpBgBitmp = null;
			System.gc();
		}
		if (bgBitmap==null||bgBitmap.isRecycled()) {
			Log.e("bitmaputil", "createBusinessCard bgBitmap null");
			return null;
		}
		Canvas canvas = new Canvas(bgBitmap);
		Bitmap tmpAvatarBitmap = null;
		if (avatarPath==null||avatarPath.trim().equals("")) {
			tmpAvatarBitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.ic_launcher);
		}else {
			tmpAvatarBitmap = BitmapFactory.decodeFile(avatarPath);
		}
		float avatar_w_scale = (float)80/tmpAvatarBitmap.getWidth();
		float avatar_h_scale = (float)80/tmpAvatarBitmap.getHeight();
		Matrix avatarMatrix = new Matrix();
		avatarMatrix.postScale(avatar_w_scale, avatar_h_scale);
		Bitmap avatarBitmap = createMutableBitmap(tmpAvatarBitmap, 0, 0, tmpAvatarBitmap.getWidth(), tmpAvatarBitmap.getHeight(), avatarMatrix, true);
		if (tmpAvatarBitmap!=null&&!tmpAvatarBitmap.isRecycled()) {
			tmpAvatarBitmap.recycle();
			tmpAvatarBitmap = null;
			System.gc();
		}
		if (avatarBitmap==null||avatarBitmap.isRecycled()) {
			Log.e("bitmaputil", "createBusinessCard avatarBitmap null");
			return null;
		}
		canvas.drawBitmap(avatarBitmap, 10, 10, paint);
		if (avatarBitmap!=null&&!avatarBitmap.isRecycled()) {
			avatarBitmap.recycle();
			avatarBitmap = null;
			System.gc();
		}
		canvas.drawText(nickname, 100,30, paint);
		canvas.drawText("傻逼号:"+userid, 100,70, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		return bgBitmap;
	}
	public static String saveBitmap(Bitmap bitmap,String filepath){
		if (bitmap==null||bitmap.isRecycled()||filepath==null||filepath.trim().equals("")) {
			return null;
		}
		FileOutputStream fos = null;
		File file = new File(filepath);
		try {
			file.createNewFile();
			fos = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
			return filepath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Bitmap createMutableBitmap(Bitmap source, int x, int y,
			int width, int height, Matrix m, boolean filter) {
		if (source == null || source.isRecycled()) {
			return null;
		}
		if (x < 0) {
			throw new IllegalArgumentException("x must be >= 0");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y must be >= 0");
		}

		if (width <= 0) {
			throw new IllegalArgumentException("width must be > 0");
		}
		if (height <= 0) {
			throw new IllegalArgumentException("height must be > 0");
		}

		if (x + width > source.getWidth()) {
			throw new IllegalArgumentException(
					"x + width must be <= bitmap.width()");
		}
		if (y + height > source.getHeight()) {
			throw new IllegalArgumentException(
					"y + height must be <= bitmap.height()");
		}

		int neww = width;
		int newh = height;
		Canvas canvas = new Canvas();
		Bitmap bitmap;
		Paint paint;

		Rect srcR = new Rect(x, y, x + width, y + height);
		RectF dstR = new RectF(0, 0, width, height);

		Config newConfig = Config.ARGB_8888;
		/*
		 * final Config config = source.getConfig(); // GIF files generate null
		 * configs, assume ARGB_8888 if (config != null) { switch (config) {
		 * case RGB_565: newConfig = Config.RGB_565; break; case ALPHA_8:
		 * newConfig = Config.ALPHA_8; break; //noinspection deprecation case
		 * ARGB_4444: case ARGB_8888: default: newConfig = Config.ARGB_8888;
		 * break; } }
		 */

		if (m == null || m.isIdentity()) {
			try {
				bitmap = Bitmap.createBitmap(neww, newh, newConfig);
			} catch (OutOfMemoryError e2) {
				return null;
			}
			paint = null; // not needed
		} else {
			final boolean transformed = !m.rectStaysRect();

			RectF deviceR = new RectF();
			m.mapRect(deviceR, dstR);

			neww = Math.round(deviceR.width());
			newh = Math.round(deviceR.height());

			/*
			 * bitmap = createBitmap(neww, newh, transformed ? Config.ARGB_8888
			 * : newConfig, transformed || source.hasAlpha());
			 */
			if (neww <= 0 || newh <= 0) {
				return null;
			}
			try {
				bitmap = Bitmap.createBitmap(neww, newh,
						transformed ? Config.ARGB_8888 : newConfig);
			} catch (OutOfMemoryError e1) {
				return null;
			} catch (Exception e) {
				return null;
			}

			canvas.translate(-deviceR.left, -deviceR.top);
			canvas.concat(m);

			paint = new Paint();
			paint.setFilterBitmap(filter);
			if (transformed) {
				paint.setAntiAlias(true);
			}
		}
		if (bitmap == null || bitmap.isRecycled()) {
			return null;
		}
		if (source == null || source.isRecycled()) {
			return null;
		}
		// The new bitmap was created from a known bitmap source so assume that
		// they use the same density
		bitmap.setDensity(source.getDensity());

		canvas.setBitmap(bitmap);
		canvas.drawBitmap(source, srcR, dstR, paint);

		if (Build.VERSION.SDK_INT >= 14) {
			// 要大于4.0，在低版本中Canvas.setBitmap，如果传一个null，会crash
			canvas.setBitmap(null);
		}

		return bitmap;
	}
}
