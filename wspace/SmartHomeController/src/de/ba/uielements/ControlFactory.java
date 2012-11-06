/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.uielements;

import android.content.Context;

/**
 * This Factory creates control elements that are all of type ControlItemIF
 * right now are Button, Switch, Slider possible
 * */
public class ControlFactory {

	/**
	 * This method creates control items right now just button, switch, slider.
	 * For more you can extend this method with control elements that implements
	 * ControlItemIF
	 * 
	 * @param type
	 *            String now Button, Switch or Slider
	 * @param cont
	 *            AppContext
	 * @return ControlItemIF
	 * */
	public static ControlItemIF getControlItem(String type, Context cont) {
		if (type.equals("Button")) {
			return new MyButton(cont);
		} else if (type.equals("Switch")) {
			return new MySwitch(cont);
		} else if (type.equals("Slider")) {
			return new NewSlider(cont);
		} else {
			return null;
		}
	}

}
