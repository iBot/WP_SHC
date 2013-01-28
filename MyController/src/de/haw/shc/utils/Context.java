package de.haw.shc.utils;

import static de.haw.shc.utils.Control.*; 


public enum Context{
	
	ALL("allRooms","allRooms") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW, HEATING };
		}
	},
	LOUNGE("lounge","lounge") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, HEATING };
		}
	},
	KITCHEN("kitchen","kitchen") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW };
		}
	},
	HALL("hall","hall") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN };
		}
	},
	SLEEPING("sleeping","sleeping") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, HEATING };
		}
	},
	DINING("dining","dining") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW, HEATING };
		}
	};
	
//	Resources.ResourceMap rm = Resources.getKeyValueMap("res/values/strings.xml");
	private String name;
    private String value;
	

	private Context(String name, String value) {
//		this.name = rm.getStringValue(name);
		this.name = name;
        this.value = value;
	}
	
	public abstract Control[] getControls();

    public String getValue(){
        return value;
    }

	@Override
	public String toString() {
		return name;
	}
	
	
}
