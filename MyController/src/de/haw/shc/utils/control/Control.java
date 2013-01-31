package de.haw.shc.utils.control;

import de.haw.shc.ContextListActivity;
import de.haw.shc.R;

public enum Control {
	LIGHT(ContextListActivity.getAppContext().getString(R.string.light)),
    HEATING(ContextListActivity.getAppContext().getString(R.string.heating)),
    WINDOW(ContextListActivity.getAppContext().getString(R.string.window)),
    BLINDS(ContextListActivity.getAppContext().getString(R.string.blinds)),
    CURTAIN(ContextListActivity.getAppContext().getString(R.string.curtain));

	private String name;

	private Control(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	//Resources.ResourceMap rm = Resources.getKeyValueMap("res/values/strings.xml");

}
