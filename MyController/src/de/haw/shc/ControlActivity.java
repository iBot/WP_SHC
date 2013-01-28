package de.haw.shc;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;


import de.haw.shc.utils.context.Room;
import de.haw.shc.utils.context.RoomDelegate;


public class ControlActivity extends Activity {

	private RoomDelegate clfDelegate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		clfDelegate = new RoomDelegate();
		if (savedInstanceState == null) {		
			Bundle b = getIntent().getExtras();
			Room context = (Room)b.getSerializable(ControlFragment.CONTEXT);
			setTitle(context.toString());
			clfDelegate.onItemSelected(context, this);
		} else{
			Room context = (Room)savedInstanceState.getSerializable(ControlFragment.CONTEXT);
			setTitle(context.toString());
			clfDelegate.restoreFrom(savedInstanceState,this);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		clfDelegate.onSaveInstanceState(outState);
	}
	
	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			
			
//			NavUtils.navigateUpTo(this, new Intent(this,
//					ContextListActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
