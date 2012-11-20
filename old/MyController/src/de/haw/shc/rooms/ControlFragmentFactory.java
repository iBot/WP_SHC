package de.haw.shc.rooms;

import java.util.HashMap;
import java.util.Map;

import de.haw.shc.ControlFragment;

import android.app.Fragment;
import android.os.Bundle;

public class ControlFragmentFactory {

	private static Map<RoomContext, Map<Control,Fragment>>  fragmentMap = new HashMap<RoomContext, Map<Control,Fragment>>();

	static{
		
		for (RoomContext c: RoomContext.values()) {
			fragmentMap.put(c,null);
		}
	}
	public static Fragment getInstance(RoomContext context, Control control) {
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
