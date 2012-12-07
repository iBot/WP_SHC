package de.haw.shc.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import android.util.Log;

public class Resources {
//
//	class ResourceMap {
//		private Map<String, String> values;
//
//		public String getStringValue(String key) {
//			String result = this.values.get(key);
//			if (result==null){
//				result = "ValueNotFound";
//			}
//			return result;
//		}
//
//		private ResourceMap(Map<String, String> values) {
//			this.values = values;
//		}
//	}
//
//	private static Map<String, ResourceMap> resources = new TreeMap<String, Resources.ResourceMap>();
//
//	public static ResourceMap getKeyValueMap(String file) {
//		Log.d("MyDebug", file);
//		if (!resources.containsKey(file)) {
//
//			Map<String, String> result = new TreeMap<String, String>();
//			FileInputStream xmlSource;
//			InputStreamReader xmlStreamReader;
//			BufferedReader xmlBuffer;
//			try {
//				xmlSource = new FileInputStream(file);
//				xmlStreamReader = new InputStreamReader(xmlSource);
//				xmlBuffer = new BufferedReader(xmlStreamReader);
//			} catch (FileNotFoundException e) {
//				throw new Error(e);
//			}
//
//			
//			String line = "";
//			while (line != null) {
//				
//				System.out.println(line);
//				Log.d("MyDebug", line);
//				
//				try {
//					line = xmlBuffer.readLine();
//				} catch (IOException e) {
//
//					try {
//						xmlBuffer.close();
//					} catch (IOException e1) {
//						throw new Error(e);
//					}
//					throw new Error(e);
//				}
//				line = line.trim();
//				if (line.matches("<string name=(.)*</string>")) {
//					line = line.replace("<string name=\"", "");
//					line = line.replace("</string>", "");
//					String[] keyVal = line.split("\"<");
//					result.put(keyVal[0], keyVal[1]);
//				}
//			}
//
//			try {
//				xmlSource.close();
//
//			} catch (IOException e) {
//				throw new Error(e);
//			}
//		}
//
//		return resources.get(file);
//	}
}
