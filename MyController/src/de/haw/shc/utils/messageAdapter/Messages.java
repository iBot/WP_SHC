package de.haw.shc.utils.messageAdapter;

import java.util.HashMap;
import java.util.Map;

//public static final Message 

public final class Messages {


    //Konstanten für Fenster-Geschwindigkeit
    public static final String WINDOW_SPEED_FAST = "FAST";
    public static final String WINDOW_SPEED_SLOW = "SLOW";
    public static final String WINDOW_SPEED_STOP = "STOP";
    public static final String WINDOW_SPEED_NSTP = "NSTP";


    //Light Messages - Küche:
    public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_OFF = createLightMessage("kitchen_main_light_off", getNoLightMap());
    public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_ON = createLightMessage("kitchen_main_light_on", getIntensityMap());
    public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_RED = createLightMessage("kitchen_main_light_color", getRedLightMap());
    public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_GREEN = createLightMessage("kitchen_main_light_color", getGreenLightMap());
    public static final Message MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_BLUE = createLightMessage("kitchen_main_light_color", getBlueLightMap());
    //Light Messages - Esszimmer:
    public static final Message MSG_LIGHT_DINING_LIGHT_OFF = createLightMessage("dining_light_off", getNoLightMap());
    public static final Message MSG_LIGHT_DINING_LIGHT_ON = createLightMessage("dining_light_on", getIntensityMap());
    public static final Message MSG_LIGHT_DINING_LIGHT_COLOR_RED = createLightMessage("dining_light_color", getRedLightMap());
    public static final Message MSG_LIGHT_DINING_LIGHT_COLOR_GREEN = createLightMessage("dining_light_color", getGreenLightMap());
    public static final Message MSG_LIGHT_DINING_LIGHT_COLOR_BLUE = createLightMessage("dining_light_color", getBlueLightMap());
    //Light Messages - Flur
    public static final Message MSG_LIGHT_CORRIDOR_LIGHT_OFF = createLightMessage("corridor_light_off", getNoLightMap());
    public static final Message MSG_LIGHT_CORRIDOR_LIGHT_ON = createLightMessage("corridor_light_on", getIntensityMap());
    //Light Messages - Schlafzimmer
    public static final Message MSG_LIGHT_SLEEPING_LIGHT_OFF = createLightMessage("sleeping_light_off", getFadeMap());
    public static final Message MSG_LIGHT_SLEEPING_LIGHT_ON = createLightMessage("sleeping_light_on", getIntensityFadeMap());
    public static final Message MSG_LIGHT_SLEEPING_COLOR_RED = createLightMessage("sleeping_light_color", getRedLightMap());
    public static final Message MSG_LIGHT_SLEEPING_COLOR_GREEN = createLightMessage("sleeping_light_color", getGreenLightMap());
    public static final Message MSG_LIGHT_SLEEPING_LIGHT_COLOR_BLUE = createLightMessage("sleeping_light_color", getBlueLightMap());
    //Light Messages - Bad
    public static final Message MSG_LIGHT_BATHROOM_LIGHT_OFF = createLightMessage("sleeping_light_off", getFadeMap());
    public static final Message MSG_LIGHT_BATHROOM_LIGHT_ON = createLightMessage("sleeping_light_on", getIntensityFadeMap());
    public static final Message MSG_LIGHT_BATHROOM_COLOR_RED = createLightMessage("sleeping_light_color", getRedLightMap());
    public static final Message MSG_LIGHT_BATHROOM_COLOR_GREEN = createLightMessage("sleeping_light_color", getGreenLightMap());
    public static final Message MSG_LIGHT_BATHROOM_LIGHT_COLOR_BLUE = createLightMessage("sleeping_light_color", getBlueLightMap());
    //
    //Curtain Message
    public static final Message MSG_CURTAIN_LOUNGE_CURTAIN_OPEN = createCurtainMessage("lounge_curtain_open");
    public static final Message MSG_CURTAIN_LOUNGE_CURTAIN_CLOSE = createCurtainMessage("lounge_curtain_close");
    public static final Message MSG_CURTAIN_SLEEPING_HALL_CURTAIN_OPEN = createCurtainMessage("sleeping_hall_curtain_open");
    public static final Message MSG_CURTAIN_SLEEPING_HALL_CURTAIN_CLOSE = createCurtainMessage("sleeping_hall_curtain_close");
    public static final Message MSG_CURTAIN_SLEEPING_WINDOW_CURTAIN_OPEN = createCurtainMessage("sleeping_window_curtain_open");
    public static final Message MSG_CURTAIN_SLEEPING_WINDOW_CURTAIN_CLOSE = createCurtainMessage("sleeping_window_curtain_close");
    //
    //Blinds Message
    public static final Message MSG_BLINDS_ALLROOMS_BLINDS_OPEN = createBlindsMessage("blinds_open");
    public static final Message MSG_BLINDS_ALLROOMS_BLINDS_CLOSE = createBlindsMessage("blinds_close");
    //
    //Window Messages
    public static final Message MSG_WINDOW_ALLROOMS_WINDOW_OPEN = createWindowMessage("ALL", 10, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_ALLROOMS_WINDOW_CLOSE = createWindowMessage("ALL", 0, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_KITCHEN_WINDOW_OPEN = createWindowMessage("KITCHEN", 10, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_KITCHEN_WINDOW_CLOSE = createWindowMessage("KITCHEN", 0, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_DINING_WINDOW_OPEN = createWindowMessage("DINING", 10, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_DINING_WINDOW_CLOSE = createWindowMessage("DINING", 0, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_LOUNGE_WINDOW_OPEN = createWindowMessage("LOUNGE", 10, WINDOW_SPEED_FAST);
    public static final Message MSG_WINDOW_LOUNGE_WINDOW_CLOSE = createWindowMessage("LOUNGE", 0, WINDOW_SPEED_FAST);

    private Messages() {
    }

    //Messages manuell erstellen
    public static Message createLightMessage(String action, Map<String, Object> valueMap) {
        return new LightMessage(action, valueMap);
    }

    public static Message createHeatingMessage() {
        throw new Error("Not implemented yet!");
    }

    public static Message createWindowMessage(String windowID, int targetPosition, String speed) {
        return new WindowMessage(windowID, targetPosition,speed);
    }

    public static Message createCurtainMessage(String action) {
        return new CurtainMessage(action);
    }

    public static Message createBlindsMessage(String action) {
        return new BlindsMessage(action);
    }

    //Private Hilfsmethoden
    private static Map<String, Object> getNoLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 0);
        valueMap.put("green", 0);
        valueMap.put("blue", 0);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    private static Map<String, Object> getRedLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 255);
        valueMap.put("green", 0);
        valueMap.put("blue", 0);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    private static Map<String, Object> getGreenLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 0);
        valueMap.put("green", 255);
        valueMap.put("blue", 0);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    private static Map<String, Object> getBlueLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 0);
        valueMap.put("green", 0);
        valueMap.put("blue", 255);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    private static Map<String, Object> getAllLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 255);
        valueMap.put("green", 255);
        valueMap.put("blue", 255);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    private static Map<String, Object> getIntensityMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("intensity", 255);
        return valueMap;
    }

    private static Map<String, Object> getIntensityFadeMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("fadeTime", 10);
        valueMap.put("intensity", 255);
        return valueMap;
    }

    private static Map<String, Object> getFadeMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("fadeTime", 10);
        valueMap.put("intensity", 255);
        return valueMap;
    }


}
