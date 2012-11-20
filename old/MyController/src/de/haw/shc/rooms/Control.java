package de.haw.shc.rooms;

public enum Control {
	LIGHT("Licht"), HEATING("Heizung"), WINDOW("Fenster"), BLINDS("Rolos"), CURTAIN(
			"Gardinen");

	private String name;

	private Control(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
