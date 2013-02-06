package de.haw.shc.utils.control;

import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;



import de.haw.shc.ControlFragment;
import de.haw.shc.utils.context.Room;

public class ControlFragmentFactory {

	private static Map<Room, Map<Control,Fragment>>  fragmentMap = new HashMap<Room, Map<Control,Fragment>>();

	static{
		
		for (Room c: Room.values()) {
			fragmentMap.put(c,null);
		}
	}
	public static Fragment getInstance(Room context, Control control) {
//		if (fragmentMap.get(context) == null){
			fragmentMap.put(context, new HashMap<Control, Fragment>());
//		}
//		if (fragmentMap.get(context).get(control) == null){
			Fragment fragment = new ControlFragment();
			Bundle args = new Bundle();
			args.putSerializable(ControlFragment.CONTEXT, context);
			args.putSerializable(ControlFragment.CONTROL, control);
			fragment.setArguments(args);
			fragmentMap.get(context).put(control, fragment);
//		}
		return fragmentMap.get(context).get(control);
	}

}
