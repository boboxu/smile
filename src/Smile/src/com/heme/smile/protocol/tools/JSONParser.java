package com.heme.smile.protocol.tools;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class JSONParser extends IDataLoaderParser {
	public abstract Object parseJson(JSONObject obj);
	
	@Override
	public final Object parse(byte[] b) {
		String strJson = new String(b);
		try {
			JSONObject obj = new JSONObject(strJson);
			return parseJson(obj);
			
		} catch (JSONException e) {
			mErrCode = 20005;
			return null;
		}
	}
}
