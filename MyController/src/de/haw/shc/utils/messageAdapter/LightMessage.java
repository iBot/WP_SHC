package de.haw.shc.utils.messageAdapter;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

class LightMessage implements Message {
	
	private final JSONObject messageContent;

	@Override
	public String getContent() {
		return messageContent.toString();
	}
	
	@Override
	public String getTopic() {
		return TOPIC_LIGHTCONTROL;
	}
	
	LightMessage(String action,Map<String, Object> valueMap){
		JSONObject jsonValues = new JSONObject(valueMap);
		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("values", jsonValues);
		contentMap.put("Id", CLIENT_ID);
		contentMap.put("Version", null);
		contentMap.put("action", action);
		
		this.messageContent = new JSONObject(contentMap);
	}

    @Override
    public String toString() {
        return "LightMessage{" +
                "messageContent=" + messageContent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LightMessage that = (LightMessage) o;

        if (!messageContent.equals(that.messageContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messageContent.hashCode();
    }
}
