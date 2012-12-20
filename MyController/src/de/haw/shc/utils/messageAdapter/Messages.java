package de.haw.shc.utils.messageAdapter;

import java.util.HashMap;
import java.util.Map;

//public static final Message 

public final class Messages {
	private Messages(){};
	
	public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_OFF = new LightMessage("kitchen_main_light_off", getNoLightMap());
	public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_ON = new LightMessage("kitchen_main_light_on", getIntensityMap());
	public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_RED = new LightMessage("kitchen_main_light_color", getRedLightMap());
	public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_GREEN = new LightMessage("kitchen_main_light_color", getGreenLightMap());
	public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_BLUE = new LightMessage("kitchen_main_light_color", getBlueLightMap());
	
	public static final Message MSG_LIGHT_DINING_LIGHT_OFF = new LightMessage("dining_light_off", getNoLightMap());
	public static final Message MSG_LIGHT_DINING_LIGHT_ON = new LightMessage("dining_light_on", getIntensityMap());
	public static final Message MSG_LIGHT_DINING_LIGHT_COLOR_RED = new LightMessage("dining_light_color", getRedLightMap());
	public static final Message MSG_LIGHT_DINING_LIGHT_COLOR_GREEN = new LightMessage("dining_light_color", getGreenLightMap());
	public static final Message MSG_LIGHT_DINING_LIGHT_COLOR_BLUE = new LightMessage("dining_light_color", getBlueLightMap());
	
	public static final Message MSG_LIGHT_CORRIDOR_LIGHT_OFF = new LightMessage("corridor_light_off", getNoLightMap());
	public static final Message MSG_LIGHT_CORRIDOR_LIGHT_ON = new LightMessage("corridor_light_on", getIntensityMap());
	
	public static final Message MSG_LIGHT_SLEEPING_LIGHT_OFF = new LightMessage("sleeping_light_off", getFadeMap());
	public static final Message MSG_LIGHT_SLEEPING_LIGHT_ON = new LightMessage("sleeping_light_on", getIntensityFadeMap());
	public static final Message MSG_LIGHT_SLEEPING_COLOR_RED = new LightMessage("sleeping_light_color", getRedLightMap());
	public static final Message MSG_LIGHT_SLEEPING_COLOR_GREEN = new LightMessage("sleeping_light_color", getGreenLightMap());
	public static final Message MSG_LIGHT_SLEEPING_LIGHT_COLOR_BLUE = new LightMessage("sleeping_light_color", getBlueLightMap());
	
	public static final Message MSG_LIGHT_BATHROOM_LIGHT_OFF = new LightMessage("sleeping_light_off", getFadeMap());
	public static final Message MSG_LIGHT_BATHROOM_LIGHT_ON = new LightMessage("sleeping_light_on", getIntensityFadeMap());
	public static final Message MSG_LIGHT_BATHROOM_COLOR_RED = new LightMessage("sleeping_light_color", getRedLightMap());
	public static final Message MSG_LIGHT_BATHROOM_COLOR_GREEN = new LightMessage("sleeping_light_color", getGreenLightMap());
	public static final Message MSG_LIGHT_BATHROOM_LIGHT_COLOR_BLUE = new LightMessage("sleeping_light_color", getBlueLightMap());
	
	public static Message createLightMessage(String action, String topic, Map<String, Object> valueMap){
		return new LightMessage(action, valueMap);
	}
	
	public static Message createHeatingMessage(){
		throw new Error("Not implemented yet!");
	}
	
	public static Message createWindowMessage(){
		throw new Error("Not implemented yet!");
	}
	
	public static Message createCutrainMessage(){
		throw new Error("Not implemented yet!");
	}
	
	private static final Map<String, Object> getNoLightMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("red", 0);
		valueMap.put("green", 0);
		valueMap.put("blue", 0);
		valueMap.put("fadeTime", 0);
		return valueMap;
	}
	private static final Map<String, Object> getRedLightMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("red", 255);
		valueMap.put("green", 0);
		valueMap.put("blue", 0);
		valueMap.put("fadeTime", 0);
		return valueMap;
	}
	private static final Map<String, Object> getGreenLightMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("red", 0);
		valueMap.put("green", 255);
		valueMap.put("blue", 0);
		valueMap.put("fadeTime", 0);
		return valueMap;
	}
	private static final Map<String, Object> getBlueLightMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("red", 0);
		valueMap.put("green", 0);
		valueMap.put("blue", 255);
		valueMap.put("fadeTime", 0);
		return valueMap;
	}
	private static final Map<String, Object> getAllLightMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("red", 255);
		valueMap.put("green", 255);
		valueMap.put("blue", 255);
		valueMap.put("fadeTime", 0);
		return valueMap;
	}
	private static final Map<String, Object> getIntensityMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("intensity", 255);
		return valueMap;
	}
	private static final Map<String, Object> getIntensityFadeMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("fadeTime", 10);
		valueMap.put("intensity", 255);
		return valueMap;
	}
	private static final Map<String, Object> getFadeMap(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("fadeTime", 10);
		valueMap.put("intensity", 255);
		return valueMap;
	}
	

}
