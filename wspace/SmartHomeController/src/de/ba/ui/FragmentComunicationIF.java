/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.ui;

import de.ba.datenstructure.Message;
import de.ba.datenstructure.Node;
import de.ba.datenstructure.NodeProperties;
import de.ba.uielements.ElementGroup;
/**
 * This class is used for communication between SmartHomeControllerActivity and NavigationListFragment and
 * between SmartHomeControllerActivity and ElementGroup
 * @see setNewPointer
 * @see statusChanged
 * */
public interface FragmentComunicationIF {
	/**
	 * If some entry in the NavigationListFragment was selected, it will call this method
	 * to publish the actual pointer node
	 * @param pointer actual node in the navigation tree
	 * */
    public void setNewPointer(Node<String,NodeProperties> pointer);
    /** 
     * After control items changed their state, this method will be called by ElementGroup
     * @param msg modified message that will be sent to ActiveMQ
     * @see ElementGroup.update
     * */
    public void statusChanged(Message msg);
}
