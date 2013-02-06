package de.haw.shc.utils.context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;


import android.util.Log;
import de.haw.shc.ControlFragment;
import de.haw.shc.R;
import de.haw.shc.utils.control.Control;
import de.haw.shc.utils.control.ControlFragmentFactory;

public class RoomDelegate {

	private Room mRoom;
	private Control mControl;
    private static final String LOG_TAG = "RoomDelegate";
	
	

	private Map<Room, Control> ctxCtrlMap = new HashMap<Room, Control>();

	public void onItemSelected(final Room id,
			final Activity activity) {

		final ActionBar actionBar = activity.getActionBar();
		// Specify that tabs should be displayed in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		if (mRoom != null && mControl != null) {
			ctxCtrlMap.put(mRoom, mControl);
		}
		actionBar.removeAllTabs();

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {

                mRoom = id;
                mControl = (Control) tab.getTag();

                Log.d(LOG_TAG, "selected Tab: "+mRoom+"-"+mControl);
//                activity.getFragmentManager()
                        ft
//                        .beginTransaction()
                        .replace(
                                R.id.control_container,
                                ControlFragmentFactory.getInstance(id,
                                        (Control) tab.getTag()));

			}

            @Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}

            @Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
                onTabSelectOrReselect(tab, ft);
			}

            private void onTabSelectOrReselect(Tab tab, FragmentTransaction ft){

            }
		};

		RoomTabFactory.createTabs(actionBar, id, tabListener);

		if (ctxCtrlMap.containsKey(id)) {
			int pos = Arrays.asList(id.getControls()).indexOf(
					ctxCtrlMap.get(id));
			actionBar.setSelectedNavigationItem(pos);
		} else {
			actionBar.setSelectedNavigationItem(0);
		}
	}

	public void restoreFrom(Bundle savedInstanceState,
			Activity activity) {
		if (savedInstanceState.containsKey(ControlFragment.CONTEXT)) {
			Room room = (Room) savedInstanceState
					.getSerializable(ControlFragment.CONTEXT);
			if (room != null) {
				onItemSelected(room, activity);
				if (savedInstanceState.containsKey(ControlFragment.CONTROL)) {
					Control control = (Control) savedInstanceState
							.getSerializable(ControlFragment.CONTROL);
					if (control != null) {
						activity.getActionBar()
								.setSelectedNavigationItem(
                                        Arrays.asList(room.getControls())
                                                .indexOf(control));
					}
				}
			}
		}
	}

	public void onSaveInstanceState(Bundle outState) {
		if (mRoom != null) {
			outState.putSerializable(ControlFragment.CONTEXT, mRoom);
		}
		if (mControl != null) {
			outState.putSerializable(ControlFragment.CONTROL, mControl);
		}
	}

}
