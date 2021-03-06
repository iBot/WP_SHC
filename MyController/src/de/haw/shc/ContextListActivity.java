package de.haw.shc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;



import de.haw.shc.utils.context.Room;
import de.haw.shc.utils.context.RoomDelegate;

public class ContextListActivity extends Activity implements
		ContextListFragment.Callbacks {

	private boolean mTwoPane;
	private RoomDelegate clfDelegate = new RoomDelegate();
    private static Context AppContext;

    public static Context getAppContext(){
        return AppContext;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        AppContext = getApplication().getApplicationContext();
		setContentView(R.layout.activity_context_twopane);

		if (findViewById(R.id.control_container) != null) {
			mTwoPane = true;
			((ContextListFragment) getFragmentManager()
					.findFragmentById(R.id.context_list))
					.setActivateOnItemClick(true);
			if (savedInstanceState != null) {
				Log.d(getClass().getSimpleName(),"onCreate mit savedInstanceState");
				clfDelegate.restoreFrom(savedInstanceState, this);
			}
		}	
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(getClass().getSimpleName(),"onSavedInstanceState");
		super.onSaveInstanceState(outState);
		clfDelegate.onSaveInstanceState(outState);
	}
	
	@Override
	public void onItemSelected(final Room id) {
		if (mTwoPane) {
			clfDelegate.onItemSelected(id, this);
		} else {
			Intent detailIntent = new Intent(this, ControlActivity.class);
			detailIntent.putExtra(ControlFragment.CONTEXT, id);
			startActivity(detailIntent);
		}
	}
}
