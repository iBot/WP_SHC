package de.haw.shc.utils;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;


public class ContextTabFactory {

	
	public static void createTabs(ActionBar actionBar, Context context, TabListener tabListener) {
		for (Control control : context.getControls()) {
			actionBar.addTab(actionBar.newTab()
						.setText(control.toString())
						.setTabListener(tabListener).setTag(control));
		}
	}

}
