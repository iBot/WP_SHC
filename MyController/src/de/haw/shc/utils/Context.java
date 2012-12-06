package de.haw.shc.utils;

import static de.haw.shc.utils.Control.*; 

<<<<<<< HEAD
public enum Context {
		
	ALL("allRooms") {
=======
public enum Context{
	
	ALL("Alle Räume") {
>>>>>>> 4081ca1248c5a6358398d60441df71b6ae7d8340
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW, HEATING };
		}
	},
	LOUNGE("lounge") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, HEATING };
		}
	},
	KITCHEN("kitchen") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW };
		}
	},
	HALL("hall") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN };
		}
	},
	BEDROOM("bedroom") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, HEATING };
		}
	},
	DINING("dining") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW, HEATING };
		}
	};
	
	Resources.ResourceMap rm = Resources.getKeyValueMap("/res/values/strings.xml");
	private String name;
	

	private Context(String name) {
		this.name = rm.getStringValue(name);
	}
	
	public abstract Control[] getControls();

	@Override
	public String toString() {
		return name;
	}
	
	
}
