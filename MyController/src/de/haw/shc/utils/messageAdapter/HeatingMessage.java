package de.haw.shc.utils.messageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

class HeatingMessage implements Message {

    private final JSONObject messageContent;
    private final String messageforat = "{\"%s\":\"%s\"}";

    HeatingMessage(String heatingModule, String valueAsHexString) {
        try {
            this.messageContent = new JSONObject(String.format(messageforat, heatingModule, valueAsHexString));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    @Override
	public String getContent() {
		return messageContent.toString();
	}

	@Override
	public String getTopic() {
		return TOPIC_HEATINGCONTROL;
	}

}
