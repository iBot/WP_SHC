/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.datenstructure;

/**
 * Message contains and manages information about topic name, type (topic or
 * queue) and message that will be sent to ActiveMQ.
 * */
public class Message {

	private String topic;
	private String typ;
	private String message;

	/**
	 * @return Name of topic
	 * */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            Name of topic
	 * */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return Typ topic or queue
	 * */
	public String getTyp() {
		return typ;
	}

	/**
	 * @param typ
	 *            Typ topic or queue
	 * */
	public void setTyp(String typ) {
		this.typ = typ;
	}

	/**
	 * @return Message for ActiveMQ
	 * */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            Message for ActiveMQ
	 * */
	public void setMessage(String message) {
		this.message = message;
	}
}
