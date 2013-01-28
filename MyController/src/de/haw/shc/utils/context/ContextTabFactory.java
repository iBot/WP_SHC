package de.haw.shc.utils.context;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import de.haw.shc.utils.control.Control;


public class ContextTabFactory {

	
	public static void createTabs(ActionBar actionBar, Context context, TabListener tabListener) {
		for (Control control : context.getControls()) {
			actionBar.addTab(actionBar.newTab()
						.setText(control.toString())
						.setTabListener(tabListener).setTag(control));
		}
	}

}
