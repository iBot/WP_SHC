/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 **************************/
package de.ba.uielements;

import java.util.LinkedList;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * Class NewSlider is control item. Its inherits from LinearLayout to be
 * displayed on the screen and implements ControlItemIF for communicate with
 * program NewSlider contains 3 standard android views TextView, SeekBar,
 * EditText
 * */
public class NewSlider extends LinearLayout implements ControlItemIF {

	private SeekBar seekbar;
	private EditText manualvalue;
	private TextView text;
	private int current_value;
	private int min;
	private int max;
	private String name;

	private LinkedList<UpdateIF> observers = new LinkedList<UpdateIF>();

	/**
	 * Standard constructor is needed for LinearLayout
	 * */
	public NewSlider(Context context) {
		super(context);
	}

	/**
	 * @param value
	 *            String[] value[0] min; value[1] max; value[2] actual state.
	 * */
	@Override
	public void setArguments(String[] value) {
		this.min = Integer.valueOf(value[0]);
		this.max = Integer.valueOf(value[1]);
		this.current_value = Integer.valueOf(value[2]);
	}

	/**
	 * @return String[] value[0] min; value[1] max; value[2] actual state.
	 * */
	@Override
	public String[] getArguments() {
		String[] args = new String[3];
		args[0] = Integer.toString(this.min);
		args[1] = Integer.toString(this.max);
		args[2] = Integer.toString(this.current_value);
		return args;
	}

	/***/
	@Override
	public String getParameter() {
		return Integer.toString(this.current_value);
	}

	/***/
	@Override
	public void setState(String param) {
		this.current_value = Integer.valueOf(param);
		seekbar.setProgress((current_value - min) * 100 / (max - min));
	}

	/***/
	@Override
	public String getState() {
		return Integer.toString(this.current_value);
	}

	/***/
	@Override
	public void createIt(Context context) {
		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		setGravity(Gravity.CENTER_VERTICAL);
		setWeightSum(100);

		int width = LayoutParams.WRAP_CONTENT;
		// getLayoutParams().height; getLayoutParams().height;
		int height = LayoutParams.WRAP_CONTENT;
		LayoutParams lptxt = new LinearLayout.LayoutParams(width, height, 20);
		LayoutParams lpmv = new LinearLayout.LayoutParams(width, height, 10);

		seekbar = new SeekBar(context);
		manualvalue = new EditText(context);
		text = new TextView(context);
		text.setText(name);
		text.setMaxWidth(lptxt.width);
		// Wertebereich der Seekbar wird per min max argument vorgegeben
		// dies können billibige Werte sein auch negative
		// intern ist aber der Bereich 0-100
		// es wird also intern-extern bzw.extern-intern immer umgerechnet
		seekbar.setMax(100);
		seekbar.setProgress((current_value - min) * 100 / (max - min));
		seekbar.setOnSeekBarChangeListener(new SliderListener());

		manualvalue.setText(Integer.toString(current_value));
		manualvalue.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
		manualvalue.setMaxLines(1);
		manualvalue.setOnClickListener(new SliderListener());
		manualvalue.setMaxWidth(lpmv.width);

		addView(text, lptxt);
		addView(seekbar, new LinearLayout.LayoutParams(width, height, 70));
		addView(manualvalue, lpmv);
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
		return name;
	}

	/**
	 * Slider Listener class
	 * */
	private class SliderListener implements OnSeekBarChangeListener,
			OnClickListener {

		/***/
		@Override
		public void onClick(View v) {

			int tmpValue;
			try {
				tmpValue = Integer.valueOf((String) ((TextView) manualvalue)
						.getText().toString());
			} catch (NumberFormatException ex) {
				tmpValue = current_value;
			}

			current_value = tmpValue;

			// current_value=(max-min) * current_value/100 + min;
			if (current_value < min)
				current_value = min;
			if (current_value > max)
				current_value = max;
			manualvalue.setText("" + current_value);
			seekbar.setProgress((current_value - min) * 100 / (max - min));
			notifyObserver();
		}

		/***/
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			current_value = (max - min) * progress / 100 + min;
			manualvalue.setText("" + current_value);
			// notifyObserver();
		}

		/***/
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		/***/
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

			// value=seekBar.getProgress();
			// manualvalue.setText(""+value);
			notifyObserver();
		}

	}

	/***/
	@Override
	public String getTyp() {
		return "Slider";
	}

}
