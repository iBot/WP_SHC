package de.haw.shc.utils.messageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

class WindowMessage implements Message {

    private final JSONObject messageContent;
    private final String messageformat = "{\"%s\":[\"%d\",\"%s\"]}";

    WindowMessage(String windowID, int targetPosition, String speed) {
        try {
            this.messageContent = new JSONObject(String.format(messageformat, windowID, targetPosition, speed));
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

    @Override
    public String toString() {
        return "WindowMessage{" +
                "messageContent=" + messageContent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WindowMessage that = (WindowMessage) o;

        if (!messageContent.equals(that.messageContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messageContent.hashCode();
    }
}
