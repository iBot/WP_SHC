/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.datenstructure;

import java.util.LinkedList;

import de.ba.uielements.ElementGroup;

/**
 * NodeProperties is a part of data structure. It manages a List of
 * ElementGroup, that contains all of control items belongs to node
 * 
 * @see ElementGroup
 * */
public class NodeProperties {
	private LinkedList<ElementGroup> controls = new LinkedList<ElementGroup>();

	/**
	 * @return List of ElementGroup belongs to node
	 * */
	public LinkedList<ElementGroup> getElementGroupList() {
		return controls;
	}

	/**
	 * @param ElementGroup
	 *            that will be added to a List
	 * */
	public void addItemToList(ElementGroup item) {
		controls.add(item);
	}

	/**
	 * @return true if the node don't have any ElementGroup entries in the list
	 * */
	public boolean isEmpty() {
		return controls.isEmpty();
	}
}
