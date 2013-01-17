package de.haw.shc.utils.messageAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Tobi
 * Date: 21.12.12
 * Time: 14:09
 */
class BlindsMessage implements Message {

    private final JSONObject messageContent;

    public BlindsMessage(String action) {
        JSONObject jsonValues = new JSONObject(new HashMap<String, Object>());
        Map<String, Object> contentMap = new HashMap<String, Object>();
        contentMap.put("values", jsonValues);
        contentMap.put("Id", CLIENT_ID);
        contentMap.put("Version", null);
        contentMap.put("action", action);

        this.messageContent = new JSONObject(contentMap);
    }

    @Override
    public String getContent() {
        return messageContent.toString();
    }

    @Override
    public String getTopic() {
        return TOPIC_BLINDSCONTROL;
    }

    @Override
    public String toString() {
        return "BlindsMessage{" +
                "messageContent=" + messageContent.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlindsMessage that = (BlindsMessage) o;

        if (!messageContent.equals(that.messageContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messageContent.hashCode();
    }
}
