package de.haw.shc;

import de.haw.shc.rooms.Control;
import de.haw.shc.rooms.RoomContext;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ControlFragment extends Fragment {

	public static final String CONTEXT = "context";
	public static final String CONTROL = "control";

	RoomContext mContext;
	Control mControl;

	public ControlFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			if (getArguments().containsKey(CONTEXT)) {
				mContext = (RoomContext) (getArguments().getSerializable(CONTEXT));
			}
			if (getArguments().containsKey(CONTROL)) {
				mControl = (Control) (getArguments().getSerializable(CONTROL));
			}
		} else {
			mContext = (RoomContext) savedInstanceState.getSerializable(CONTEXT);
			mControl = (Control) savedInstanceState.getSerializable(CONTROL);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(CONTEXT, mContext);
		outState.putSerializable(CONTROL, mControl);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_control, container,
				false);
		if (mContext != null && mControl != null) {
			TextView tw = (TextView) rootView.findViewById(R.id.control);
			tw.setText(mContext + " " + mControl);
		}

		return rootView;
	}

}
