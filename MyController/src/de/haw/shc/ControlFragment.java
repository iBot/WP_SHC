package de.haw.shc;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.haw.shc.utils.ButtonListenerFactory;
import de.haw.shc.utils.Context;
import de.haw.shc.utils.Control;
import de.haw.shc.utils.ViewTransportTyp;

import java.util.ArrayList;
import java.util.List;

public class ControlFragment extends Fragment {

    private static final String LOG_TAG = "ControlFragment";

	public static final String CONTEXT = "context";
	public static final String CONTROL = "control";
    private  static final  String BUTTONFACTORY = "BUTTONFACTORY";

	private Context mContext;
	private Control mControl;
    private ButtonListenerFactory buttonListenerFactory;

	public ControlFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
            //TODO warum getArguments?
            buttonListenerFactory = new ButtonListenerFactory();
			if (getArguments().containsKey(CONTEXT)) {
				mContext = (Context) (getArguments().getSerializable(CONTEXT));
			}
			if (getArguments().containsKey(CONTROL)) {
				mControl = (Control) (getArguments().getSerializable(CONTROL));
			}
		} else {
            buttonListenerFactory =  (ButtonListenerFactory) savedInstanceState.getSerializable(BUTTONFACTORY);
			mContext = (Context) savedInstanceState.getSerializable(CONTEXT);
			mControl = (Control) savedInstanceState.getSerializable(CONTROL);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

        outState.putSerializable(BUTTONFACTORY,buttonListenerFactory);
		outState.putSerializable(CONTEXT, mContext);
		outState.putSerializable(CONTROL, mControl);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
//		if (mContext != null && mControl != null) {
//			TextView tw = (TextView) rootView.findViewById(R.id.control);
//			tw.setText(mContext + " " + mControl);
//		}

		View view = null;
        Button button;
        List<Button> buttonList = new ArrayList<Button>();


        Log.d(LOG_TAG, "Ausgabe");
        Log.d(LOG_TAG, mContext.name());

		if(mControl == Control.LIGHT){
			
			view = inflater.inflate(R.layout.light_layout, container,false);

		}
		else if(mControl == Control.CURTAIN){
			
			view = inflater.inflate(R.layout.curtains_layout, container,false);
		}
		else if(mControl == Control.BLINDS){
			
			view = inflater.inflate(R.layout.blinds_layout, container,false);
		}
		else if(mControl == Control.WINDOW){
			
			view = inflater.inflate(R.layout.window_layout, container,false);
		}
		else if(mControl == Control.HEATING){
			
			view = inflater.inflate(R.layout.heating_layout, container,false);
		}
        buttonListenerFactory.checkControlls(new ViewTransportTyp(mContext,mContext.getControls(),view));
		return view;
	}

}
