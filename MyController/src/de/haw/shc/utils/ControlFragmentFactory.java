package de.haw.shc.utils;

import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;



import de.haw.shc.ControlFragment;
public class ControlFragmentFactory {

	private static Map<Context, Map<Control,Fragment>>  fragmentMap = new HashMap<Context, Map<Control,Fragment>>();

	static{
		
		for (Context c: Context.values()) {
			fragmentMap.put(c,null);
		}
	}
	public static Fragment getInstance(Context context, Control control) {
		if (fragmentMap.get(context) == null){
			fragmentMap.put(context, new HashMap<Control, Fragment>());
		}
		if (fragmentMap.get(context).get(control) == null){
			Fragment fragment = new ControlFragment();
			Bundle args = new Bundle();
			args.putSerializable(ControlFragment.CONTEXT, context);
			args.putSerializable(ControlFragment.CONTROL, control);
			fragment.setArguments(args);
			fragmentMap.get(context).put(control, fragment);
		}
		return fragmentMap.get(context).get(control);
	}

}
