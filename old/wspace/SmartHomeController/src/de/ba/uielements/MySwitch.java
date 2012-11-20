/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 **************************/
package de.ba.uielements;

import java.util.LinkedList;

import android.content.Context;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import de.ba.ui.SmartHomeControllerActivity;

/**
 * Class MySwitch is control item. Its inherits from LinearLayout to be
 * displayed on the screen and implements ControlItemIF for communicate with
 * program. MySwitch is a kind of toggle button. It can be used if controlling
 * object need two different values to toggle it state, but this wouldn't work
 * with two different messages.
 * */
public class MySwitch extends LinearLayout implements ControlItemIF {
	private Switch sw;

	private String off_state_value;
	private String on_state_value;
	private boolean cstate;

	private String name;
	private LinkedList<UpdateIF> observers = new LinkedList<UpdateIF>();

	/**
	 * @param context
	 *            AppContext
	 * */
	public MySwitch(Context context) {
		super(context);
	}

	/***/
	// public Switch getControlItem() {
	// return sw;
	// }

	/***/
	@Override
	/**
	 * @param value: value[0] - value for on state; value[1] - value for off state; value[2] - current state
	 * */
	public void setArguments(String[] value) {
		this.on_state_value = value[0];
		this.off_state_value = value[1];
		this.cstate = (Integer.valueOf(value[2]) == 1) ? true : false;

	}

	/**
	 * @returns String[]: value[0] - value for on state; value[1] - value for
	 *          off state; value[2] - current state
	 * */
	@Override
	public String[] getArguments() {
		String[] args = new String[3];
		args[0] = this.on_state_value;
		args[1] = this.off_state_value;
		args[2] = cstate ? Integer.toString(1) : Integer.toString(0);
		return args;
	}

	/***/
	@Override
	public String getParameter() {
		return cstate ? this.on_state_value : this.off_state_value;
	}

	/***/
	@Override
	public void setState(String param) {
		this.cstate = (Integer.valueOf(param) == 1) ? true : false;
		sw.setChecked(cstate);
	}

	/***/
	@Override
	public String getState() {
		return this.cstate ? Integer.toString(1) : Integer.toString(0);
	}

	/***/
	@Override
	public void createIt(Context context) {
		setOrientation(LinearLayout.VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		setGravity(Gravity.CENTER_HORIZONTAL);

		sw = new Switch(context);
		sw.setText(name);
		sw.setChecked(cstate);

		sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			/***/
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					Toast.makeText(SmartHomeControllerActivity.appContext,
							"on", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SmartHomeControllerActivity.appContext,
							"off", Toast.LENGTH_SHORT).show();
				}
				cstate = isChecked;
				notifyObserver();

			}
		});

		int width = LayoutParams.FILL_PARENT;
		int height = LayoutParams.WRAP_CONTENT;

		addView(sw, new LinearLayout.LayoutParams(width, height));
	}

	/***/
	@Override
	public void addObserver(UpdateIF observer) {
		this.observers.add(observer);
	}

	/***/
	@Override
	public void notifyObserver() {
		if (!observers.isEmpty()) {

			for (UpdateIF item : observers) {
				item.update(); // this.value, name, id
			}
		}
	}

	/***/
	@Override
	public void setName(String s) {
		this.name = s;

	}

	/***/
	@Override
	public String getName() {
		return this.name;
	}

	/***/
	@Override
	public String getTyp() {
		return "Switch";
	}

}
