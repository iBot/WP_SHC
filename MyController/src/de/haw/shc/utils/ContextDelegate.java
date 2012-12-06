package de.haw.shc.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentTransaction;



import de.haw.shc.ControlFragment;
import de.haw.shc.R;

public class ContextDelegate {

	private Context mContext;
	private Control mControl;
	
	

	private Map<Context, Control> ctxCtrlMap = new HashMap<Context, Control>();

	public void onItemSelected(final Context id,
			final Activity activity) {

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

				activity.getFragmentManager()
						.beginTransaction()
						.replace(
								R.id.control_container,
								ControlFragmentFactory.getInstance(id,
										(Control) tab.getTag())).commit();
			}

			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}

			public void onTabReselected(Tab tab, FragmentTransaction ft) {
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
			Activity activity) {
		if (savedInstanceState.containsKey(ControlFragment.CONTEXT)) {
			Context context = (Context) savedInstanceState
					.getSerializable(ControlFragment.CONTEXT);
			if (context != null) {
				onItemSelected(context, activity);
				if (savedInstanceState.containsKey(ControlFragment.CONTROL)) {
					Control control = (Control) savedInstanceState
							.getSerializable(ControlFragment.CONTROL);
					if (control != null) {
						activity.getActionBar()
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
