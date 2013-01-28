package de.haw.shc.utils.context;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import de.haw.shc.utils.control.Control;


public class RoomTabFactory {

	
	public static void createTabs(ActionBar actionBar, Room room, TabListener tabListener) {
		for (Control control : room.getControls()) {
			actionBar.addTab(actionBar.newTab()
						.setText(control.toString())
						.setTabListener(tabListener).setTag(control));
		}
	}

}
