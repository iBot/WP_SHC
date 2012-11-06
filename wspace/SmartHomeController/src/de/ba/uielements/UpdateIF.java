/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 **************************/
package de.ba.uielements;
/**
 * This Interface is used to grant communication between control items and ElementGroup
 * */
public interface UpdateIF {
	/**
	 * notify ElementGroup that control item changes state
	 * */
    public void update();
}
