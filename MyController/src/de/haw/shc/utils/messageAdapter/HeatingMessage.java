package de.haw.shc.utils.messageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

class HeatingMessage implements Message {

    private final JSONObject messageContent;
    private final String messageformat = "{\"%s\":\"%s\"}";

    HeatingMessage(String heatingModule, String valueAsHexString) {
        try {
            this.messageContent = new JSONObject(String.format(messageformat, heatingModule, valueAsHexString));
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

    @Override
    public String toString() {
        return "HeatingMessage{" +
                "messageContent=" + messageContent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeatingMessage that = (HeatingMessage) o;

        if (!messageContent.equals(that.messageContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messageContent.hashCode();
    }
}
