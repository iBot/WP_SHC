
package de.haw.shc.utils.messageAdapter;

import android.util.Log;
import de.haw.shc.utils.Context;

import java.util.*;

//public static final Message 

/**
 *
 */
public final class Messages {

    //Konstanten für Fenster-Steuerung
    public static final String WINDOW_SPEED_FAST = "FAST";
    public static final String WINDOW_SPEED_SLOW = "SLOW";
    public static final String WINDOW_SPEED_STOP = "STOP";
    public static final String WINDOW_SPEED_NSTP = "NSTP";
    //Light Messages - Küche:
    public static final LightMessage MSG_LIGHT_KITCHEN_MAIN_LIGHT_OFF = createLightMessage("kitchen_main_light_off", getNoLightMap());
    public static final LightMessage MSG_LIGHT_KITCHEN_MAIN_LIGHT_ON = createLightMessage("kitchen_main_light_on", getIntensityMap());
    public static final LightMessage MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_RED = createLightMessage("kitchen_main_light_color", getRedLightMap());
    public static final LightMessage MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_GREEN = createLightMessage("kitchen_main_light_color", getGreenLightMap());
    public static final LightMessage MSG_LIGHT_KITCHEN_MAIN_LIGHT_COLOR_BLUE = createLightMessage("kitchen_main_light_color", getBlueLightMap());
    //Light Messages - Esszimmer:
    public static final LightMessage MSG_LIGHT_DINING_LIGHT_OFF = createLightMessage("dining_light_off", getNoLightMap());
    public static final LightMessage MSG_LIGHT_DINING_LIGHT_ON = createLightMessage("dining_light_on", getIntensityMap());
    public static final LightMessage MSG_LIGHT_DINING_LIGHT_COLOR_RED = createLightMessage("dining_light_color", getRedLightMap());
    public static final LightMessage MSG_LIGHT_DINING_LIGHT_COLOR_GREEN = createLightMessage("dining_light_color", getGreenLightMap());
    public static final LightMessage MSG_LIGHT_DINING_LIGHT_COLOR_BLUE = createLightMessage("dining_light_color", getBlueLightMap());
    //Light Messages - Flur
    public static final LightMessage MSG_LIGHT_CORRIDOR_LIGHT_OFF = createLightMessage("corridor_light_off", getNoLightMap());
    public static final LightMessage MSG_LIGHT_CORRIDOR_LIGHT_ON = createLightMessage("corridor_light_on", getIntensityMap());
    //Light Messages - Schlafzimmer
    public static final LightMessage MSG_LIGHT_SLEEPING_LIGHT_OFF = createLightMessage("sleeping_light_off", getFadeMap());
    public static final LightMessage MSG_LIGHT_SLEEPING_LIGHT_ON = createLightMessage("sleeping_light_on", getIntensityFadeMap());
    public static final LightMessage MSG_LIGHT_SLEEPING_LIGHT_COLOR_RED = createLightMessage("sleeping_light_color", getRedLightMap());
    public static final LightMessage MSG_LIGHT_SLEEPING_LIGHT_COLOR_GREEN = createLightMessage("sleeping_light_color", getGreenLightMap());
    public static final LightMessage MSG_LIGHT_SLEEPING_LIGHT_COLOR_BLUE = createLightMessage("sleeping_light_color", getBlueLightMap());
    //Light Messages - Bad
    public static final LightMessage MSG_LIGHT_BATHROOM_LIGHT_OFF = createLightMessage("bathroom_light_off", getFadeMap());
    public static final LightMessage MSG_LIGHT_BATHROOM_LIGHT_ON = createLightMessage("bathroom_light_on", getIntensityFadeMap());
    public static final LightMessage MSG_LIGHT_BATHROOM_LIGHT_COLOR_RED = createLightMessage("bathroom_light_color", getRedLightMap());
    public static final LightMessage MSG_LIGHT_BATHROOM_LIGHT_COLOR_GREEN = createLightMessage("bathroom_light_color", getGreenLightMap());
    public static final LightMessage MSG_LIGHT_BATHROOM_LIGHT_COLOR_BLUE = createLightMessage("bathroom_light_color", getBlueLightMap());
    //Light Messages - Wohnzimmer
    public static final LightMessage MSG_LIGHT_LOUNGE_LIGHT_OFF = createLightMessage("lounge_light_off", getFadeMap());
    public static final LightMessage MSG_LIGHT_LOUNGE_LIGHT_ON = createLightMessage("lounge_light_on", getFadeMap());
    public static final LightMessage MSG_LIGHT_LOUNGE_LIGHT_COLOR_RED = createLightMessage("lounge_light_color", getRedLightMap());
    public static final LightMessage MSG_LIGHT_LOUNGE_LIGHT_COLOR_GREEN = createLightMessage("lounge_light_color", getGreenLightMap());
    public static final LightMessage MSG_LIGHT_LOUNGE_LIGHT_COLOR_BLUE = createLightMessage("lounge_light_color", getBlueLightMap());
    //
    //Curtain Message
    public static final CurtainMessage MSG_CURTAIN_LOUNGE_CURTAIN_OPEN = createCurtainMessage("lounge_curtain_open");
    public static final CurtainMessage MSG_CURTAIN_LOUNGE_CURTAIN_CLOSE = createCurtainMessage("lounge_curtain_close");
    public static final CurtainMessage MSG_CURTAIN_SLEEPING_HALL_CURTAIN_OPEN = createCurtainMessage("sleeping_hall_curtain_open");
    public static final CurtainMessage MSG_CURTAIN_SLEEPING_HALL_CURTAIN_CLOSE = createCurtainMessage("sleeping_hall_curtain_close");
    public static final CurtainMessage MSG_CURTAIN_SLEEPING_WINDOW_CURTAIN_OPEN = createCurtainMessage("sleeping_window_curtain_open");
    public static final CurtainMessage MSG_CURTAIN_SLEEPING_WINDOW_CURTAIN_CLOSE = createCurtainMessage("sleeping_window_curtain_close");
    //
    //Blinds Message
    public static final BlindsMessage MSG_BLINDS_ALLROOMS_BLINDS_OPEN = createBlindsMessage("blinds_open");
    public static final BlindsMessage MSG_BLINDS_ALLROOMS_BLINDS_HALF = createBlindsMessage("blinds_half");
    public static final BlindsMessage MSG_BLINDS_ALLROOMS_BLINDS_CLOSE = createBlindsMessage("blinds_close");
    public static final BlindsMessage MSG_BLINDS_KITCHEN_BLINDS_OPEN = createBlindsMessage("blinds_dining_kitchen_open");
    public static final BlindsMessage MSG_BLINDS_KITCHEN_BLINDS_HALF = createBlindsMessage("blinds_dining_kitchen_half");
    public static final BlindsMessage MSG_BLINDS_KITCHEN_BLINDS_CLOSE = createBlindsMessage("blinds_dining_kitchen_close");
    public static final BlindsMessage MSG_BLINDS_DINING_BLINDS_OPEN = MSG_BLINDS_KITCHEN_BLINDS_OPEN;
    public static final BlindsMessage MSG_BLINDS_DINING_BLINDS_HALF = MSG_BLINDS_KITCHEN_BLINDS_HALF;
    public static final BlindsMessage MSG_BLINDS_DINING_BLINDS_CLOSE = MSG_BLINDS_KITCHEN_BLINDS_CLOSE;
    public static final BlindsMessage MSG_BLINDS_LOUNGE_BLINDS_OPEN = createBlindsMessage("blinds_lounge_open");
    public static final BlindsMessage MSG_BLINDS_LOUNGE_BLINDS_HALF = createBlindsMessage("blinds_lounge_half");
    public static final BlindsMessage MSG_BLINDS_LOUNGE_BLINDS_CLOSE = createBlindsMessage("blinds_lounge_close");
    public static final BlindsMessage MSG_BLINDS_SLEEPING_BLINDS_OPEN = createBlindsMessage("blinds_sleeping_open");
    public static final BlindsMessage MSG_BLINDS_SLEEPING_BLINDS_HALF = createBlindsMessage("blinds_sleeping_half");
    public static final BlindsMessage MSG_BLINDS_SLEEPING_BLINDS_CLOSE = createBlindsMessage("blinds_sleeping_close");
    //
    //Window Messages
    public static final WindowMessage MSG_WINDOW_ALLROOMS_WINDOW_OPEN = createWindowMessage("ALL", 10, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_ALLROOMS_WINDOW_CLOSE = createWindowMessage("ALL", 0, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_KITCHEN_WINDOW_OPEN = createWindowMessage("KITCHEN", 10, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_KITCHEN_WINDOW_CLOSE = createWindowMessage("KITCHEN", 0, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_DINING_WINDOW_OPEN = createWindowMessage("DINING", 10, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_DINING_WINDOW_CLOSE = createWindowMessage("DINING", 0, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_LOUNGE_WINDOW_OPEN = createWindowMessage("LOUNGE", 10, WINDOW_SPEED_FAST);
    public static final WindowMessage MSG_WINDOW_LOUNGE_WINDOW_CLOSE = createWindowMessage("LOUNGE", 0, WINDOW_SPEED_FAST);
    static final String LOG_TAG = "Messages";

    /**
     * Private constructor - not used, because it's an utility-class
     */
    private Messages() {
        throw new Error("THIS IS AN UTILITY CLASS!!! DO NOT CREATE INSTANCES OF THIS!!!");
    }

    /**
     * This method should be used to create messages for light control
     *
     * @param action
     * @param valueMap
     * @return
     */
    public static LightMessage createLightMessage(String action, Map<String, Object> valueMap) {
        return new LightMessage(action, valueMap);
    }

    /**
     * @param room
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public static Message createColorLightMessage(Context room, int red, int green, int blue) {
        Map<String, Object> colors = new HashMap<String, Object>();
        colors.put("red", red);
        colors.put("green", green);
        colors.put("blue", blue);

        LightMessage result;
        if (room == Context.KITCHEN) {
            result = createLightMessage("kitchen_main_light_color", colors);
        } else if (room == Context.SLEEPING) {
            result = createLightMessage("sleeping_main_light_color", colors);
        } else if (room == Context.DINING) {
            result = createLightMessage("dining_main_light_color", colors);
        } else if (room == Context.HALL) {
            result = createLightMessage("corridor_main_light_color", colors);
        } else if (room == Context.LOUNGE) {
            result = createLightMessage("lounge_main_light_color", colors);
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Light Control!", room));
        }
        return result;
    }

    /**
     * This method should be used to create a message to switch the main light of a room on and set it to full intensity
     *
     * @param room
     * @return
     */
    public static LightMessage createLightOnMessage(Context room) {
        LightMessage result;
        if (room == Context.KITCHEN) {
            result = MSG_LIGHT_KITCHEN_MAIN_LIGHT_ON;
        } else if (room == Context.SLEEPING) {
            result = MSG_LIGHT_SLEEPING_LIGHT_ON;
        } else if (room == Context.DINING) {
            result = MSG_LIGHT_DINING_LIGHT_ON;
        } else if (room == Context.HALL) {
            result = MSG_LIGHT_CORRIDOR_LIGHT_ON;
        } else if (room == Context.LOUNGE) {
            result = MSG_LIGHT_LOUNGE_LIGHT_ON;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Light Control!", room));
        }
        return result;
    }

    public static Collection<LightMessage> createAllLightOnMessage() {
        Set<LightMessage> lightMessages = new TreeSet<LightMessage>();
        lightMessages.add(MSG_LIGHT_KITCHEN_MAIN_LIGHT_ON);
        lightMessages.add(MSG_LIGHT_SLEEPING_LIGHT_ON);
        lightMessages.add(MSG_LIGHT_DINING_LIGHT_ON);
        lightMessages.add(MSG_LIGHT_CORRIDOR_LIGHT_ON);
        lightMessages.add(MSG_LIGHT_LOUNGE_LIGHT_ON);
        return lightMessages;
    }

    public static Collection<LightMessage> createAllLightOffMessage() {
        Set<LightMessage> lightMessages = new TreeSet<LightMessage>();
        lightMessages.add(MSG_LIGHT_KITCHEN_MAIN_LIGHT_OFF);
        lightMessages.add(MSG_LIGHT_SLEEPING_LIGHT_OFF);
        lightMessages.add(MSG_LIGHT_DINING_LIGHT_OFF);
        lightMessages.add(MSG_LIGHT_CORRIDOR_LIGHT_OFF);
        lightMessages.add(MSG_LIGHT_LOUNGE_LIGHT_OFF);
        return lightMessages;
    }

    public static BlindsMessage createBlindsOpenMessage(Context room) {
        BlindsMessage result;
        if (room == Context.ALL) {
            result = MSG_BLINDS_ALLROOMS_BLINDS_OPEN;
        } else if (room == Context.KITCHEN) {
            result = MSG_BLINDS_KITCHEN_BLINDS_OPEN;
        } else if (room == Context.SLEEPING) {
            result = MSG_BLINDS_SLEEPING_BLINDS_OPEN;
        } else if (room == Context.DINING) {
            result = MSG_BLINDS_DINING_BLINDS_OPEN;
        } else if (room == Context.LOUNGE) {
            result = MSG_BLINDS_LOUNGE_BLINDS_OPEN;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Blinds Control!", room));
        }
        return result;
    }

    public static BlindsMessage createBlindsHalfOpenMessage(Context room) {
        BlindsMessage result;
        if (room == Context.ALL) {
            result = MSG_BLINDS_ALLROOMS_BLINDS_HALF;
        } else if (room == Context.KITCHEN) {
            result = MSG_BLINDS_KITCHEN_BLINDS_HALF;
        } else if (room == Context.SLEEPING) {
            result = MSG_BLINDS_SLEEPING_BLINDS_HALF;
        } else if (room == Context.DINING) {
            result = MSG_BLINDS_DINING_BLINDS_HALF;
        } else if (room == Context.LOUNGE) {
            result = MSG_BLINDS_LOUNGE_BLINDS_HALF;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Blinds Control!", room));
        }
        return result;
    }

    public static BlindsMessage createBlindsCloseMessage(Context room) {
        BlindsMessage result;
        if (room == Context.ALL) {
            result = MSG_BLINDS_ALLROOMS_BLINDS_CLOSE;
        } else if (room == Context.KITCHEN) {
            result = MSG_BLINDS_KITCHEN_BLINDS_CLOSE;
        } else if (room == Context.SLEEPING) {
            result = MSG_BLINDS_SLEEPING_BLINDS_CLOSE;
        } else if (room == Context.DINING) {
            result = MSG_BLINDS_DINING_BLINDS_CLOSE;
        } else if (room == Context.LOUNGE) {
            result = MSG_BLINDS_LOUNGE_BLINDS_CLOSE;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Blinds Control!", room));
        }
        return result;
    }

    public static CurtainMessage createCurtainsOpenMessage(Context room) {
        CurtainMessage result;
        if (room == Context.LOUNGE) {
            result = MSG_CURTAIN_LOUNGE_CURTAIN_OPEN;
        } else if (room == Context.SLEEPING) {
            result = MSG_CURTAIN_SLEEPING_HALL_CURTAIN_OPEN;
        } else if (room == Context.HALL) {
            result = MSG_CURTAIN_SLEEPING_HALL_CURTAIN_OPEN;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Curtain Control!", room));
        }
        return result;
    }

    public static CurtainMessage createCurtainsCloseMessage(Context room) {
        CurtainMessage result;
        if (room == Context.LOUNGE) {
            result = MSG_CURTAIN_LOUNGE_CURTAIN_CLOSE;
        } else if (room == Context.SLEEPING) {
            result = MSG_CURTAIN_SLEEPING_HALL_CURTAIN_CLOSE;
        } else if (room == Context.HALL) {
            result = MSG_CURTAIN_SLEEPING_HALL_CURTAIN_CLOSE;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Curtain Control!", room));
        }
        return result;
    }

    public static WindowMessage createWindowOpenMessage(Context room) {
        WindowMessage result;
        if (room == Context.ALL) {
            result = MSG_WINDOW_ALLROOMS_WINDOW_OPEN;
        } else if (room == Context.DINING) {
            result = MSG_WINDOW_DINING_WINDOW_OPEN;
        } else if (room == Context.KITCHEN) {
            result = MSG_WINDOW_KITCHEN_WINDOW_OPEN;
        } else if (room == Context.LOUNGE) {
            result = MSG_WINDOW_LOUNGE_WINDOW_OPEN;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Curtain Control!", room));
        }
        return result;
    }

    public static WindowMessage createWindowCloseMessage(Context room) {
        WindowMessage result;
        if (room == Context.ALL) {
            result = MSG_WINDOW_ALLROOMS_WINDOW_CLOSE;
        } else if (room == Context.DINING) {
            result = MSG_WINDOW_DINING_WINDOW_CLOSE;
        } else if (room == Context.KITCHEN) {
            result = MSG_WINDOW_KITCHEN_WINDOW_CLOSE;
        } else if (room == Context.LOUNGE) {
            result = MSG_WINDOW_LOUNGE_WINDOW_CLOSE;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Curtain Control!", room));
        }
        return result;
    }

    /**
     * This method should be used to create a message to switch the main light of a room on and set it to the given intensity
     * If intensityInPerCent less equals 0, the light be will switch off.
     * If intensityInPerCent greater equals 100, the light will be switch on with full intensity of 100%.
     *
     * @param room
     * @param intensityInPerCent Value between 0 and 100
     * @return
     */
    public static LightMessage createLightIntesityMessage(Context room, int intensityInPerCent) {
        LightMessage result;
        int intensity = intensityPerCentToInt(intensityInPerCent);
        if (intensity == 255) {
            result = createLightOnMessage(room);
        } else if (intensity == 0) {
            result = createLightOffMessage(room);
        } else {
            if (room == Context.KITCHEN) {
                result = createLightMessage("kitchen_main_light_on", getIntensityMap(intensity));
            } else if (room == Context.SLEEPING) {
                result = createLightMessage("sleeping_light_on", getIntensityMap(intensity));
            } else if (room == Context.DINING) {
                result = createLightMessage("dining_light_on", getIntensityMap(intensity));
            } else if (room == Context.HALL) {
                result = createLightMessage("corridor_light_on", getIntensityMap(intensity));
            } else if (room == Context.LOUNGE) {
                result = createLightMessage("lounge_light_on", getIntensityMap(intensity));
            } else {
                result = null;
                Log.w(LOG_TAG, String.format("%s is not a valid Context for Light Control!", room));
            }
        }
        return result;
    }

    /**
     * This method should be used to create a message to switch the main light of a room off
     *
     * @param room
     * @return
     */
    public static LightMessage createLightOffMessage(Context room) {
        LightMessage result;
        if (room == Context.KITCHEN) {
            result = MSG_LIGHT_KITCHEN_MAIN_LIGHT_OFF;
        } else if (room == Context.SLEEPING) {
            result = MSG_LIGHT_SLEEPING_LIGHT_OFF;
        } else if (room == Context.DINING) {
            result = MSG_LIGHT_DINING_LIGHT_OFF;
        } else if (room == Context.HALL) {
            result = MSG_LIGHT_CORRIDOR_LIGHT_OFF;
        } else if (room == Context.LOUNGE) {
            result = MSG_LIGHT_LOUNGE_LIGHT_OFF;
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid Context for Light Control!", room));
        }
        return result;
    }

    /**
     * This method should be used to create messages for heating control
     *
     * @param heating
     * @return
     */
    public static HeatingMessage createHeatingMessage(Heating heating) {
        return new HeatingMessage(heating.getModule(), heating.getValue());
    }

    /**
     * This method should be used to create messages for window control
     *
     * @param windowID
     * @param targetPosition
     * @param speed
     * @return
     */
    public static WindowMessage createWindowMessage(String windowID, int targetPosition, String speed) {
        return new WindowMessage(windowID, targetPosition, speed);
    }

    /**
     * This method should be used to create messages for curtain control
     *
     * @param action
     * @return
     */
    public static CurtainMessage createCurtainMessage(String action) {
        return new CurtainMessage(action);
    }

    /**
     * This method should be used to create messages for blinds control
     *
     * @param action
     * @return
     */
    public static BlindsMessage createBlindsMessage(String action) {
        return new BlindsMessage(action);
    }

    /**
     * Create an Map with all colors = 0 and fadeTime = 0
     *
     * @return
     */
    private static Map<String, Object> getNoLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 0);
        valueMap.put("green", 0);
        valueMap.put("blue", 0);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    //Private Hilfsmethoden

    /**
     * Create an Map with red color = 255, other colors = 0 and fadeTime = 0
     *
     * @return
     */
    private static Map<String, Object> getRedLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 255);
        valueMap.put("green", 0);
        valueMap.put("blue", 0);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    /**
     * Create an Map with green color = 255, other colors = 0 and fadeTime = 0
     *
     * @return
     */
    private static Map<String, Object> getGreenLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 0);
        valueMap.put("green", 255);
        valueMap.put("blue", 0);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    /**
     * Create an Map with blue color = 255, other colors = 0 and fadeTime = 0
     *
     * @return
     */
    private static Map<String, Object> getBlueLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 0);
        valueMap.put("green", 0);
        valueMap.put("blue", 255);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    /**
     * Create an Map with all colors = 255 and fadeTime = 0
     *
     * @return
     */
    private static Map<String, Object> getAllLightMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("red", 255);
        valueMap.put("green", 255);
        valueMap.put("blue", 255);
        valueMap.put("fadeTime", 0);
        return valueMap;
    }

    /**
     * Create an Map with intensity = 255
     *
     * @return
     */
    private static Map<String, Object> getIntensityMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("intensity", 255);
        return valueMap;
    }

    /**
     * Create an Map with intensity = value of given intensity parameter
     *
     * @param intensity
     * @return
     */
    private static Map<String, Object> getIntensityMap(int intensity) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("intensity", intensity);
        return valueMap;
    }

    /**
     * Create an Map with intensity = 255 and fadeTime = 10;
     *
     * @return
     */
    private static Map<String, Object> getIntensityFadeMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("fadeTime", 10);
        valueMap.put("intensity", 255);
        return valueMap;
    }

    /**
     * Create an Map with intensity = 255 and fadeTime = 10;
     *
     * @return
     */
    private static Map<String, Object> getFadeMap() {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("fadeTime", 10);
        valueMap.put("intensity", 255);
        return valueMap;
    }

    /**
     * calculates an fitting int value between 0 and 255 for the given per cent parameter value
     * 100% = 255
     * 0% =   0
     *
     * @param perCent
     * @return
     */
    private static int intensityPerCentToInt(int perCent) {
        int result;
        if (perCent >= 0) {
            result = 0;
        } else if (perCent >= 100) {
            result = 255;
        } else {
            result = (255 * perCent) / 100;
        }
        return result;
    }


    //Enum für Heizung
    public static enum Heating {
        HEAT_DINING("heatModule0", "01"),
        HEAT_SLEEP("heatModule0", "02"),
        HEAT_LOUNGE_1("heatModule1", "01"),
        HEAT_LOUNGE_0("heatModule1", "02"),
        HEAT_BATHROOM("heatModule1", "04");
        private final String module;
        private final String value;

        private Heating(String module, String value) {
            this.module = module;
            this.value = value;
        }

        public String getModule() {
            return this.module;
        }

        public String getValue() {
            return this.value;
        }
    }


}
