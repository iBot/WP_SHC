package de.haw.shc.utils.context;

import de.haw.shc.utils.control.Control;

import static de.haw.shc.utils.control.Control.*;


public enum Room {
	
	ALL("allRooms","all") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW/**, HEATING*/ };
		}
	},
	LOUNGE("lounge","lounge") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW/**, HEATING*/ };
		}
	},
	KITCHEN("kitchen","kitchen") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW };
		}
	},
	CORRIDOR("corridor","corridor") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN };
		}
	},
	SLEEPING("sleeping","sleeping") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS/**, HEATING*/ };
		}
	},
	DINING("dining","dining") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW/**, HEATING*/ };
		}
	};
	
//	Resources.ResourceMap rm = Resources.getKeyValueMap("res/values/strings.xml");
	private String name;
    private String value;
	

	private Room(String name, String value) {
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
