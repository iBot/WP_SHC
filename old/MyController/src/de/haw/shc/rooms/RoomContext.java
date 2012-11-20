package de.haw.shc.rooms;

import static de.haw.shc.rooms.Control.BLINDS;
import static de.haw.shc.rooms.Control.CURTAIN;
import static de.haw.shc.rooms.Control.HEATING;
import static de.haw.shc.rooms.Control.LIGHT;
import static de.haw.shc.rooms.Control.WINDOW;



public enum RoomContext {
	ALL("Alle Räume") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, WINDOW, HEATING };
		}
	},
	LOUNGE("Lounge") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, HEATING };
		}
	},
	KITCHEN("Küche") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW };
		}
	},
	HALL("Flur") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN };
		}
	},
	BEDROOM("Schlafbereich") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, CURTAIN, BLINDS, HEATING };
		}
	},
	DINING("Essbereich") {
		@Override
		public Control[] getControls() {
			return new Control[] { LIGHT, BLINDS, WINDOW, HEATING };
		}
	};

	private String name;

	private RoomContext(String name) {
		this.name = name;
	}

	public abstract Control[] getControls();

	@Override
	public String toString() {
		return name;
	}
}