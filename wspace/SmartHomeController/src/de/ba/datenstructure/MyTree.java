/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.datenstructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MyTree is a logical tree of Nodes
 * 
 * @see Node
 * */
public class MyTree<V> {
	private Node<String, V> root;

	/**
	 * @param String
	 *            name of root node, mostly "LP"
	 * */
	public MyTree(String rootkey) {
		root = new Node<String, V>(rootkey);
		root.setRoot();
	}

	/**
	 * adds node to the tree with path as key
	 * 
	 * @param path
	 *            String LP-path
	 * @param data
	 *            Data
	 * */
	public void addNode(String path, V data) {
		String[] pfad = path.split("\\.");
		Node<String, V> tempNode = root;
		if (path.equals("LP")) {
			tempNode.setValue(data);
		}
		for (int i = 1; i < pfad.length; i++) {
			// KindKnoten nicht vorhanden
			if (tempNode.getChildNode(pfad[i]) == null) {
				if (i == pfad.length - 1) {
					tempNode.addChild(new Node<String, V>(pfad[i], data));
					tempNode = tempNode.getChildNode(pfad[i]);
					// System.out.println("Knoten ist neu, data hinzugefügt");
				} else {
					tempNode.addChild(new Node<String, V>(pfad[i], null));
					tempNode = tempNode.getChildNode(pfad[i]);
					// System.out.println("Knoten ist neu, keine data hinzugefügt");
				}

			} else {// KindKnoten existiert
				if (i == pfad.length - 1) {
					tempNode = tempNode.getChildNode(pfad[i]);
					tempNode.setValue(data);
				} else {
					tempNode = tempNode.getChildNode(pfad[i]);
					// System.out.println("Knoten vorhanden, übernommen");
				}

			}
		}
	}

	/**
	 * @return Node root node
	 * @see Node
	 * */
	public Node<String, V> getRootNode() {
		return this.root;
	}

	/**
	 * @param path
	 *            LP-Path
	 * @return Node node at location from path
	 * */
	public Node<String, V> getNode(String path) {
		String[] pfad = path.split("\\.");
		Node<String, V> tempNode = root;
		for (int i = 1; i < pfad.length; i++) {
			if (tempNode.getChildNode(pfad[i]) == null) {
				return null;
			} else {
				// System.out.println(tempNode.getKey());
				tempNode = tempNode.getChildNode(pfad[i]);
			}
		}
		return tempNode;
	}

	/**
	 * @param node
	 *            Node
	 * @return full LP-Path to node
	 * */
	public String getFullPath(Node<String, V> node) {
		Node<String, V> tempNode = node;
		String path = node.getKey();
		if (tempNode == root)
			return path;
		while (tempNode != root) {

			if (tempNode != node) {
				path = tempNode.getKey() + "." + path;
			}

			tempNode = tempNode.getParent();

		}
		path = root.getKey() + "." + path;
		return path;
	}

	/**
	 * @return HashMap that contains full LP-path as Key and associated Nodes as
	 *         Value
	 * */
	public HashMap<String, V> getMap() {
		HashMap<String, V> map = new HashMap<String, V>();

		ArrayList<Node<String, V>> tl = new ArrayList<Node<String, V>>();
		getAllValidNodes(tl, root);

		for (Node<String, V> n : tl) {
			map.put(getFullPath(n), n.getData());
		}

		return map;
	}

	/**
	 * fills the List with all nodes, that relay contains data, also data isn't
	 * null
	 * 
	 * @param tl
	 *            List that will be filled
	 * @param n
	 *            parent node for recursion
	 * */
	private void getAllValidNodes(ArrayList<Node<String, V>> tl,
			Node<String, V> n) {
		if (n.getData() != null) {
			tl.add(n);
		}
		if (n.hasChildren()) {
			for (Node<String, V> node : n.getChildList()) {
				getAllValidNodes(tl, node);
			}
		}
	}

}
