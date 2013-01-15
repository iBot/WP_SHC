package de.haw.shc.utils.messageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

class WindowMessage implements Message {

    private final JSONObject messageContent;
    private final String messageforat = "{\"%s\":[\"%d\",\"%s\"]}";

    WindowMessage(String windowID, int targetPosition, String speed) {
        try {
            this.messageContent = new JSONObject(String.format(messageforat, windowID, targetPosition, speed));
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
        return TOPIC_WINDOWCONTROL;
    }

}
