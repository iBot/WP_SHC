package de.haw.shc.utils.messageAdapter;

import org.json.JSONObject;

class HeatingMessage implements Message {

    private final JSONObject messageContent;

    HeatingMessage() {
        messageContent = null;
    }

    @Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTopic() {
		return TOPIC_HEATINGCONTROL;
	}

}
