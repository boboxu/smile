package com.heme.smile.protocol.tools;

import com.heme.smile.protocol.runner.DataLoader;

public class DataLoaderFactory {
	
	public static DataLoader createDataLoader(String url, IDataLoaderParser parser) {
		return new DataLoader(url, parser);
	}
	
	public static DataLoader createJsonLoader(String url, String method, JSONParser parser) {
		return new DataLoader(url, parser);
	}
	
	public static DataLoader createXMLLoader(String url, String method, XMLParser parser) {
		return new DataLoader(url, parser);
	}

	private DataLoaderFactory() {
	}
}
