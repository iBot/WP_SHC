package de.haw.shc.utils.control;

public enum Control {
	LIGHT("Licht"), HEATING("Heizung"), WINDOW("Fenster"), BLINDS("Rolos"), CURTAIN(
			"Gardinen");

	private String name;

	private Control(String name) {
		//this.name = rm.getStringValue(name);
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	//Resources.ResourceMap rm = Resources.getKeyValueMap("res/values/strings.xml");

}
