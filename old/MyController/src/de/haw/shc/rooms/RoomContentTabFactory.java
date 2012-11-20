package de.haw.shc.rooms;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;


public class RoomContentTabFactory {


		
	public static void createTabs(ActionBar actionBar, RoomContext context, TabListener tabListener) {
		for (Control control : context.getControls()) {
			actionBar.addTab(actionBar.newTab()
						.setText(control.toString())
						.setTabListener(tabListener).setTag(control));
		}
	}

}
