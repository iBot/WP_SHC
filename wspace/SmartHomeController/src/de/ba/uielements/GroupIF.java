/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 **************************/
package de.ba.uielements;

import java.util.LinkedList;

import android.content.Context;
import de.ba.datenstructure.Message;

/**
 * GroupIF is a interface to use a Group. Group is several control
 * items that have just one message together. The Group triggers several
 * parameters in just one message. An example for a Group is RGB-Slider.
 * RGB-Slider is a group with three elements of NewSlider (Red, Green, Blue)
 * */
public interface GroupIF {
	/**
	 * Adds element to the group
	 * 
	 * @param item
	 *            ControlItemIF
	 * @see ControlItemIF
	 * */
	public void addElement(ControlItemIF item);

	/**
	 * @param index
	 *            int
	 * @return ControlItemIF at position index
	 * */
	public ControlItemIF getElement(int index);

	/**
	 * @return List of all ControlItemIF contains in a group
	 * */
	public LinkedList<ControlItemIF> getAllControls();

	/**
	 * @param name
	 *            String group name
	 * */
	public void setGroupName(String name);

	/**
	 * @return String group name
	 * */
	public String getGroupName();

	/**
	 * @param String
	 *            object Id of controlled object in Living Place
	 * */
	public void setLPlabel(String label);

	/**
	 * @return String object Id of controlled object in Living Place
	 * */
	public String getLPlabel();

	/**
	 * creates and displays all control items of a group
	 * 
	 * @param context
	 *            AppContext
	 * */
	public void createIt(Context context);

	/**
	 * @param m
	 *            Message that contains Message for ActiveMQ, topic name and
	 *            kind of Message (topic or queue)
	 * */
	public void setMessage(Message m);

	/**
	 * @return Message that contains Message for ActiveMQ, topic name and kind
	 *         of Message (topic or queue)
	 * */
	public Message getMessage();
}
