package de.haw.shc.utils.messageAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

class CurtainMessage implements Message{

    private final JSONObject messageContent;

	@Override
	public String getContent() {
		return this.messageContent.toString();
	}

	@Override
	public String getTopic() {
		return TOPIC_CURTAINCONTROL;
	}

    public CurtainMessage(String action){
        JSONObject jsonValues = new JSONObject(new HashMap<String, Object>());
        Map<String, Object> contentMap = new HashMap<String, Object>();
        contentMap.put("values", jsonValues);
        contentMap.put("Id", CLIENT_ID);
        contentMap.put("Version", null);
        contentMap.put("action", action);

        this.messageContent = new JSONObject(contentMap);
    }

    @Override
    public String toString() {
        return "CurtainMessage{" +
                "messageContent=" + messageContent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurtainMessage that = (CurtainMessage) o;

        if (!messageContent.equals(that.messageContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messageContent.hashCode();
    }
}
