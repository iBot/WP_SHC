package de.ba.mockup;

import java.util.HashMap;

public class Mockup {
	
	HashMap<String, String[]> container;
	
	public Mockup(){
		container=new HashMap<String, String[]>();
		
		container.put("lounge_light", new String[]{"50"});
		container.put("lounge_light_color", new String[]{"25","35","45"});
		container.put("kitchen_cooking_light", new String[]{"0"});
		container.put("kitchen_cooking_color", new String[]{"0","0","0"});
		container.put("kitchen_main_light", new String[]{"0"});
		container.put("kitchen_main_light_color", new String[]{"0","0","0"});
		container.put("dining_light", new String[]{"0"});
		container.put("dining_light_color", new String[]{"0","0","0"});
		container.put("corridor_light", new String[]{"0"});
		container.put("corridor_light_color", new String[]{"0","0","0"});
		container.put("sleeping_light", new String[]{"0"});
		container.put("sleeping_light_color", new String[]{"0","0","0"});
		container.put("bathroom_light", new String[]{"0"});
		container.put("bathroom_light_color", new String[]{"0","0","0"});
		
		container.put("lounge_curtain", new String[]{"0"});
		container.put("sleeping_hall_curtain", new String[]{"0"});
		container.put("sleeping_window_curtain", new String[]{"0"});
		container.put("blinds", new String[]{"0"});
		
		container.put("winAll", new String[]{"0"});
		container.put("winDining", new String[]{"0"});
		container.put("winDining0", new String[]{"0"});
		container.put("winDining1", new String[]{"0"});
		container.put("winKitchen", new String[]{"0"});
		container.put("winKitchen0", new String[]{"0"});
		container.put("winKitchen1", new String[]{"0"});
		container.put("winLounge", new String[]{"0"});
		container.put("winLounge0", new String[]{"0"});
		container.put("winLounge1", new String[]{"0"});
		container.put("winLounge2", new String[]{"0"});
		container.put("winLounge3", new String[]{"0"});
		container.put("winLounge4", new String[]{"0"});
		container.put("winBathroom", new String[]{"0"});
		
		container.put("heat_sleep", new String[]{"0"});
		container.put("heat_dinning", new String[]{"0"});
		container.put("heat_bathroom", new String[]{"0"});
		container.put("heat_lounge", new String[]{"0"});
		container.put("heat_lounge0", new String[]{"0"});
		container.put("heat_lounge1", new String[]{"0"});
	}

	public String[] getState(String object){
		return container.get(object);
	}
}
