package de.haw.shc.utils.messageAdapter;

import org.json.JSONObject;

class WindowMessage implements Message {

    private final JSONObject messageContent;

    WindowMessage() {
        this.messageContent = null;
    }

    @Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTopic() {
		return TOPIC_WINDOWCONTROL;
	}

}
