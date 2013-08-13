package com.heme.smile.util;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Log;

import com.heme.smile.R;



public class BiaoqingUtil {
	/**
	 * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
	 */
    public static void dealExpression(Context context,SpannableString spannableString, Pattern patten, int start) throws Exception {
    	Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            Log.d("Key", key);
            if (matcher.start() < start) {
                continue;
            }
            Field field = R.drawable.class.getDeclaredField(key);
			int resId = Integer.parseInt(field.get(null).toString());		
            if (resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
                ImageSpan imageSpan = new ImageSpan(bitmap);				            
                int end = matcher.start() + key.length();					
                spannableString.setSpan(imageSpan, matcher.start(), end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);	
                if (end < spannableString.length()) {						
                    dealExpression(context,spannableString,  patten, end);
                }
                break;
            }
        }
    }
    public static void dealExpressionWithUrl(Context context,SpannableString spannableString, Pattern patten, int start) throws Exception {
    	Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            Log.d("Key", key);
            if (matcher.start() < start) {
                continue;
            }
            	
            if (key!=null&&!key.trim().equals("")) {
                int end = matcher.start() + key.length();					
                spannableString.setSpan(new URLSpan(key), matcher.start(), end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);	
                if (end < spannableString.length()) {						
                	dealExpressionWithUrl(context,spannableString,  patten, end);
                }
                break;
            }
        }
    }
    //根据正则表达式将文本内容换成表情图(如果有)
    public static SpannableString getExpressionString(Context context,String str,String zhengze1,String zhengze2){
    	SpannableString spannableString = new SpannableString(str);
        Pattern biaoqingPattern = Pattern.compile(zhengze1, Pattern.CASE_INSENSITIVE);		//通过传入的正则表达式来生成一个pattern
        Pattern urlPattern = Pattern.compile(zhengze2, Pattern.CASE_INSENSITIVE);		//通过传入的正则表达式来生成一个pattern
        try {
        	dealExpression(context,spannableString, biaoqingPattern, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        try {
        	dealExpressionWithUrl(context,spannableString, urlPattern, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        return spannableString;
    }
    public static SpannableString getVoiceMessageExpressionString(Context context,String str){
    	SpannableString spannableString = new SpannableString(str);
    	Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.chatting_setmode_voice_btn_focused);
        ImageSpan imageSpan = new ImageSpan(bitmap);
        spannableString.setSpan(imageSpan, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
    //根据正则表达式从文本中提取url
    public static SpannableString getExpressionStringWithUrl(Context context,String str,String zhengze){
    	SpannableString spannableString = new SpannableString(str);
        Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);		//通过传入的正则表达式来生成一个pattern
        try {
        	dealExpressionWithUrl(context,spannableString, sinaPatten, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        return spannableString;
    }
}