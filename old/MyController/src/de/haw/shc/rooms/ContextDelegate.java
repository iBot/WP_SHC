package de.haw.shc.rooms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



import android.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


public class ContextDelegate {

	private RoomContext mContext;
	private Control mControl;

	private Map<RoomContext, Control> ctxCtrlMap = new HashMap<RoomContext, Control>();

	public void onItemSelected(final RoomContext id,
			final FragmentActivity activity) {

		final ActionBar actionBar = activity.getActionBar();
		// Specify that tabs should be displayed in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		if (mContext != null && mControl != null) {
			ctxCtrlMap.put(mContext, mControl);
		}
		actionBar.removeAllTabs();

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mContext = id;
				mControl = (Control) tab.getTag();

				activity.getSupportFragmentManager()
						.beginTransaction()
						.replace(
								R.id.control_container,
								ControlFragmentFactory.getInstance(id,
										(Control) tab.getTag())).commit();
			}
			

			public void onTabReselected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onTabSelected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onTabUnselected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

		};

		ContextTabFactory.createTabs(actionBar, id, tabListener);

		if (ctxCtrlMap.containsKey(id)) {
			int pos = Arrays.asList(id.getControls()).indexOf(
					ctxCtrlMap.get(id));
			actionBar.setSelectedNavigationItem(pos);
		} else {
			actionBar.setSelectedNavigationItem(0);
		}
	}

	public void restoreFrom(Bundle savedInstanceState,
			SherlockFragmentActivity activity) {
		if (savedInstanceState.containsKey(ControlFragment.CONTEXT)) {
			Context context = (Context) savedInstanceState
					.getSerializable(ControlFragment.CONTEXT);
			if (context != null) {
				onItemSelected(context, activity);
				if (savedInstanceState.containsKey(ControlFragment.CONTROL)) {
					Control control = (Control) savedInstanceState
							.getSerializable(ControlFragment.CONTROL);
					if (control != null) {
						activity.getSupportActionBar()
								.setSelectedNavigationItem(
										Arrays.asList(context.getControls())
												.indexOf(control));
					}
				}
			}
		}
	}

	public void onSaveInstanceState(Bundle outState) {
		if (mContext != null) {
			outState.putSerializable(ControlFragment.CONTEXT, mContext);
		}
		if (mControl != null) {
			outState.putSerializable(ControlFragment.CONTROL, mControl);
		}
	}

}
