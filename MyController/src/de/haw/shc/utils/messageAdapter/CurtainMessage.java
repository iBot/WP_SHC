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
}
