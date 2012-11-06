/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.ui;

import de.ba.datenstructure.MyTree;
import de.ba.datenstructure.NodeProperties;

/**
 * This is some container class, that just hold values and uses getter and
 * setter. This class represent the structure of one Tab/Plugin
 * */
public class TabContext {

	private MyTree<NodeProperties> liste;// =new MyNodeList();
	private String pointer;// ="LP";
	private NavigationListFragment fragment;// =new NavigationListFragment();
	private String tabName;
	private String server;
	private int port;

	/**
	 * @return Navigation Tree
	 * */
	public MyTree<NodeProperties> getTree() {
		return liste;
	}

	/**
	 * @param liste
	 *            Navigation Tree
	 * */
	public void setTree(MyTree<NodeProperties> liste) {
		this.liste = liste;
	}

	/**
	 * @return Path to Node as String
	 * */
	public String getPointer() {
		return pointer;
	}

	/**
	 * @param pointer
	 *            Path to Node as String
	 * */
	public void setPointer(String pointer) {
		this.pointer = pointer;
	}

	/**
	 * @return NavigationListFragment
	 * */
	public NavigationListFragment getFragment() {
		return fragment;
	}

	/**
	 * @param fragment
	 *            NavigationListFragment
	 * */
	public void setFragment(NavigationListFragment fragment) {
		this.fragment = fragment;
	}

	/**
	 * @return Name of tab
	 * */
	public String getTabName() {
		return tabName;
	}

	/**
	 * @param tabName
	 *            Name of tab
	 * */
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	/**
	 * @return Server
	 * */
	public String getServer() {
		return server;
	}

	/**
	 * @param server
	 *            Server
	 * */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return Port number
	 * */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            Port number
	 * */
	public void setPort(int port) {
		this.port = port;
	}
}
