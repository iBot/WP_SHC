package de.haw.shc.utils.messageAdapter;



public interface Message {
	
	public final String CLIENT_ID = "NFTMMyController";
	public static final String TOPIC_WINDOWCONTROL = "WINDOW.CONTROL";
	public static final String TOPIC_LIGHTCONTROL = "LIGHTCONTROL";
	public static final String TOPIC_HEATINGCONTROL = "HEATING.CONTROL";
    public static final String TOPIC_BLINDSCONTROL = TOPIC_LIGHTCONTROL;
    public static final String TOPIC_CURTAINCONTROL = TOPIC_LIGHTCONTROL;
	
	String getContent();
	String getTopic();
}
