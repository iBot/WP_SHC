/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.datenstructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Node is a part of data structure that will be used by a program to save a
 * data from plugin. Node is an generic class with Key-Value entries. Several
 * Node contains a Key, Value, List of children nodes and reference to parent
 * node.
 * */
public class Node<K, V> {

	private K nodeName;
	private V nodeData;
	private Node<K, V> parent;
	private List<Node<K, V>> childrenList;

	/**
	 * Stadard constructor creates List of child nodes
	 * */
	public Node() {
		childrenList = new ArrayList<Node<K, V>>();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            Name of node
	 * */
	public Node(K name) {
		this();
		this.nodeName = name;
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            Name of node
	 * @param data
	 *            Value of node
	 * */
	public Node(K name, V data) {
		this(name);
		this.nodeData = data;
	}

	/**
	 * Sets node as root
	 * */
	public void setRoot() {
		this.parent = this;
	}

	/**
	 * Returns true if the node is root
	 * */
	public boolean isRoot() {
		return (this.parent == this) ? true : false;
	}

	/**
	 * Set parent node
	 * 
	 * @param parent
	 *            Parent node
	 * */
	protected void setParent(Node<K, V> parent) {
		this.parent = parent;
	}

	/**
	 * Set the Value of node
	 * 
	 * @param value
	 *            Value of node
	 * */
	public void setValue(V value) {
		this.nodeData = value;
	}

	/**
	 * Add child node to the List of childs
	 * 
	 * @param child
	 *            Child node
	 * */
	public void addChild(Node<K, V> child) {
		child.setParent(this);
		childrenList.add(child);
	}

	/**
	 * @return child list of node
	 * */
	public List<Node<K, V>> getChildList() {
		return childrenList;
	}

	/**
	 * @return Set of Keys of all child nodes, that node contains
	 * */
	public Set<K> getChildListKeySet() {
		TreeSet<K> set = new TreeSet<K>();
		for (Node<K, V> item : childrenList) {
			set.add(item.getKey());
		}
		return set;
	}

	/**
	 * @return List of Values of all child nodes, that node contains
	 * */
	public List<V> getChildListValueSet() {
		List<V> set = new ArrayList<V>();
		for (Node<K, V> item : childrenList) {
			set.add(item.getData());
		}
		return set;
	}

	/**
	 * @return Key of node
	 * */
	public K getKey() {
		return this.nodeName;
	}

	/**
	 * @return Value of node
	 * */
	public V getData() {
		return this.nodeData;
	}

	/**
	 * @return Key of parent node
	 * */
	public K getParentKey() {
		return this.parent.getKey();
	}

	/**
	 * @return Value of parent node
	 * */
	public V getParentValue() {
		return this.parent.getData();
	}

	/**
	 * @return Reference to parent node
	 * */
	public Node<K, V> getParent() {
		return this.parent;
	}

	/**
	 * @return true if node has children nodes
	 * */
	public boolean hasChildren() {
		return !childrenList.isEmpty();
	}

	/**
	 * @param key
	 *            Key of searching node
	 * @return Reference to searching node by a key
	 * */
	public Node<K, V> getChildNode(K key) {
		for (Node<K, V> node : childrenList) {
			if (node.getKey().equals(key))
				return node;
		}
		return null;
	}
}
