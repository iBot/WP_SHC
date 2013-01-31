package de.haw.shc.utils.context;

import de.haw.shc.ContextListActivity;
import de.haw.shc.R;
import de.haw.shc.utils.control.Control;

import static de.haw.shc.utils.control.Control.*;


public enum Room {
	
	ALL(ContextListActivity.getAppContext().getString(R.string.allRooms),"all") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW/**, HEATING*/ };
		}
	},
	LOUNGE(ContextListActivity.getAppContext().getString(R.string.lounge),"lounge") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW/**, HEATING*/ };
		}
	},
	KITCHEN(ContextListActivity.getAppContext().getString(R.string.kitchen),"kitchen") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW };
		}
	},
	CORRIDOR(ContextListActivity.getAppContext().getString(R.string.hall),"corridor") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN };
		}
	},
	SLEEPING(ContextListActivity.getAppContext().getString(R.string.bedroom),"sleeping") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS/**, HEATING*/ };
		}
	},
	DINING(ContextListActivity.getAppContext().getString(R.string.dining),"dining") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW/**, HEATING*/ };
		}
	};
	
	private String name;
    private String value;
	

	private Room(String name, String value) {
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
