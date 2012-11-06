/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 **************************/

package de.ba.uielements;

import android.content.Context;

/**
 * This interface make it possible to use any control elements that implements this interface in the program
 * */
public interface ControlItemIF {
	/**
	 * creates control elements with taking into account of set parameters
	 * @param context AppContext 
	 * */
    public void createIt(Context context);

    /**
     * @param s Name of control item
     * */
    public void setName(String s);

    /**
     * @return Name of control item
     * */
    public String getName();

    /**
     * Method to adjust control items, like min or max values
     * @param value Array of arguments to adjust Parameter
     * */
    public void setArguments(String[] value);

    /**
     * @return String[] Array of arguments
     * */
    public String[] getArguments();
    
    /**
     * @return String Actual state of control item massage conform. Also ready to sent
     * */
    public String getParameter();
    
    /**
     * @param param really state of control item as String 
     * */
    public void setState(String param);
    
    /**
     * @return String contains really state of control item 
     * */
    public String getState();

    /**
     * Add Observer, that will be notified if changes commes
     * @param observer UpdateIF
     * @see UpdateIF
     * */
    public void addObserver(UpdateIF observer);

    /**
     * Notify subscribed observer if changes arrives
     * */
    public void notifyObserver();
    
    /**
     * @return String Type of control item
     * */
    public String getTyp();
}
