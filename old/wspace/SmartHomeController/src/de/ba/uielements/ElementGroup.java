/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 **************************/
package de.ba.uielements;

import java.util.LinkedList;

//import android.R;
import de.ba.ui.R;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.ba.datenstructure.Message;
import de.ba.datenstructure.Node;
import de.ba.datenstructure.NodeProperties;
import de.ba.ui.FragmentComunicationIF;
import de.ba.ui.SmartHomeControllerActivity;

/**
 * ElementGroup is implementation of GroupIF. Its inherits from LinearLayout to
 * be displayed on the screen and implements UpdateIF to communicate with
 * control items.
 * */
public class ElementGroup extends LinearLayout implements GroupIF, UpdateIF {

	private LinkedList<ControlItemIF> groupelements = new LinkedList<ControlItemIF>();
	private Message msg;
	private String groupname;
	private String lplabel;
	private FragmentComunicationIF fragmentupdater;
	private TextView namefield;

	/**
	 * @param context
	 *            AppContext
	 * @param upd
	 *            FragmentComunicationIF for communication with fragment
	 * @see FragmentComunicationIF
	 * */
	public ElementGroup(Context context, FragmentComunicationIF upd) {
		super(context);
		fragmentupdater = upd;
	}

	/***/
	@Override
	public void addElement(ControlItemIF item) {
		groupelements.add(item);
	}

	/***/
	@Override
	public ControlItemIF getElement(int index) {
		return groupelements.get(index);
	}

	/***/
	@Override
	public LinkedList<ControlItemIF> getAllControls() {
		return groupelements;
	}

	/***/
	@Override
	public void setGroupName(String name) {
		this.groupname = name;
	}

	/***/
	@Override
	public String getGroupName() {
		return this.groupname;
	}

	/***/
	@Override
	public void setMessage(Message m) {
		this.msg = m;
	}

	/***/
	@Override
	public Message getMessage() {
		return this.msg;
	}

	/***/
	@Override
	public void update() {
		// hier wird die Nachricht, die verschickt werden soll erstellt
		// Jedes grafische Object(Slider,Switch,Button) kann entweder einen oder
		// keinen
		// Parameter in der Nachricht ändern. z.B. RGB-Slider hat 3 Slider Rot
		// Grün Blau
		// Es wird der ROT_Wert genommen und die Nachricht nach param1
		// durchsucht und mit dem Wert ersetzt
		// für grün ist es param2 und blau param3
		// daraus folgt: die Rheienfolge der Slider beim erstellen des Plugins
		// muss der Reihenfolge der Nummer
		// des "param" entsprechen
		Message msgcopy = new Message();
		msgcopy.setTyp(this.msg.getTyp());
		msgcopy.setTopic(this.msg.getTopic());

		String tmpMsg = this.msg.getMessage().toString();
		int i = 0;
		for (ControlItemIF ci : groupelements) {
			i++;
			tmpMsg = tmpMsg.replaceFirst(
					SmartHomeControllerActivity.mainActivity
							.getString(R.string.param_regex)
							+ Integer.toString(i), ci.getParameter());
		}

		msgcopy.setMessage(tmpMsg);
		this.updateChildren(((SmartHomeControllerActivity) SmartHomeControllerActivity.mainActivity)
				.getPointer());
		fragmentupdater.statusChanged(msgcopy);
	}

	/**
	 * when the parent control item was triggered all children of them will be
	 * recursively triggered too
	 * 
	 * @param pointer
	 *            parent Node
	 * */
	private void updateChildren(Node<String, NodeProperties> pointer) {
		if (pointer.hasChildren()) {
			for (Node<String, NodeProperties> node : pointer.getChildList()) {
				if(node.getData()!=null){
					for (ElementGroup eg : node.getData().getElementGroupList()) {	
						// gleiche Gruppe des KindKnotens
						if (eg.getGroupName().equals(this.groupname)) {
							for (int i = 0; i < eg.getAllControls().size(); i++) {
								eg.getAllControls()
										.get(i)
										.setState(
												this.groupelements.get(i)
														.getState());
							}
						}
					}
				} 
				updateChildren(node);
			}
		}
	}

	/***/
	@Override
	public void createIt(Context context) {
		setOrientation(LinearLayout.VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		int width = LayoutParams.FILL_PARENT;
		int height = LayoutParams.WRAP_CONTENT;

		namefield = new TextView(context);
		namefield.setText(this.groupname);

		addView(namefield, new LinearLayout.LayoutParams(width, height));
		for (ControlItemIF ci : this.groupelements) {
			ci.createIt(context);
			ci.addObserver(this);
			addView((View) ci, new LinearLayout.LayoutParams(width, height));
		}

	}

	/***/
	@Override
	public void setLPlabel(String label) {
		this.lplabel = label;
	}

	/***/
	@Override
	public String getLPlabel() {
		return this.lplabel;
	}
}
