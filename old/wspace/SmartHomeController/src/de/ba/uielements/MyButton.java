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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Class MyButton is control item. Its inherits from LinearLayout to be
 * displayed on the screen and implements ControlItemIF for communicate with
 * program. MySwitch is a button that will sent a message to ActiveMQ if this
 * triggered
 * */
public class MyButton extends LinearLayout implements ControlItemIF {

	private Button button;
	private String[] args;
	private String name;
	private LinkedList<UpdateIF> observers = new LinkedList<UpdateIF>();

	/**
	 * @param context
	 *            AppContext
	 * */
	public MyButton(Context context) {
		super(context);
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
	public void setArguments(String[] value) {
		this.args = value;
	}

	/***/
	@Override
	public String[] getArguments() {
		return this.args;
	}

	/***/
	@Override
	public String getParameter() {
		return null;
	}

	/***/
	@Override
	public void setState(String param) {

	}

	/***/
	@Override
	public String getState() {
		return null;
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
				item.update();
			}
		}

	}

	/***/
	@Override
	public void createIt(Context context) {
		setOrientation(LinearLayout.VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		setGravity(Gravity.CENTER_HORIZONTAL);

		button = new Button(context);
		button.setText(name);
		button.setOnClickListener(new OnClickListener() {
			/***/
			@Override
			public void onClick(View view) {
				notifyObserver();
			}
		});

		int width = LayoutParams.FILL_PARENT;
		int height = LayoutParams.WRAP_CONTENT;

		addView(button, new LinearLayout.LayoutParams(width, height));
	}

	/***/
	@Override
	public String getTyp() {
		return "Button";
	}

}
