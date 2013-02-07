
package de.haw.shc.utils.messageAdapter;

import android.util.Log;
import de.haw.shc.utils.context.Room;

import java.util.*;

import static de.haw.shc.utils.context.Room.*;

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
    private static final String LOG_TAG = "Messages";

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
    public static Message createColorLightMessage(Room room, int red, int green, int blue) {
        Map<String, Object> colors = new HashMap<String, Object>();
        colors.put("red", red);
        colors.put("green", green);
        colors.put("blue", blue);

        LightMessage result;
        if (room == Room.KITCHEN) {
            result = createLightMessage("kitchen_main_light_color", colors);
        } else if (room == Room.SLEEPING) {
            result = createLightMessage("sleeping_light_color", colors);
        } else if (room == Room.DINING) {
            result = createLightMessage("dining_light_color", colors);
        } else if (room == Room.CORRIDOR) {
            result = createLightMessage("corridor_light_color", colors);
        } else if (room == Room.LOUNGE) {
            result = createLightMessage("lounge_light_color", colors);
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid room for Light Control!", room));
        }
        Log.d(LOG_TAG, String.format("The following Message has been created: ", result));
        return result;
    }

    /**
     * This method should be used to create a message to switch the main light of a room on and set it to full intensity
     *
     * @param room
     * @return
     */
    public static LightMessage createLightOnMessage(Room room) {
        LightMessage result;
        String addString = "";
        if (room == KITCHEN) {
            addString = "_main";
        }
        result = createLightMessage(room.getValue() + addString + "_light_on", getIntensityMap());
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static Collection<Message> createAllLightOnMessage() {
        Set<Message> lightMessages = new HashSet<Message>();
        lightMessages.add(createLightOnMessage(KITCHEN));
        lightMessages.add(createLightOnMessage(SLEEPING));
        lightMessages.add(createLightOnMessage(DINING));
        lightMessages.add(createLightOnMessage(CORRIDOR));
        lightMessages.add(createLightOnMessage(LOUNGE));
        Log.d(LOG_TAG, String.format("The following messag-Set has been created: ", lightMessages));
        return lightMessages;
    }

    public static Collection<Message> createAllLightColorMessages(int red, int green, int blue) {
        Set<Message> lightMessages = new HashSet<Message>();
        lightMessages.add(createColorLightMessage(KITCHEN, red, green, blue));
        lightMessages.add(createColorLightMessage(LOUNGE, red, green, blue));
        lightMessages.add(createColorLightMessage(CORRIDOR, red, green, blue));
        lightMessages.add(createColorLightMessage(SLEEPING, red, green, blue));
        lightMessages.add(createColorLightMessage(DINING, red, green, blue));
        Log.d(LOG_TAG, String.format("The following messag-Set has been created: ", lightMessages));
        return lightMessages;
    }

    public static Collection<Message> createAllLightOffMessage() {
        Set<Message> lightMessages = new HashSet<Message>();
        lightMessages.add(createLightOffMessage(KITCHEN));
        lightMessages.add(createLightOffMessage(SLEEPING));
        lightMessages.add(createLightOffMessage(DINING));
        lightMessages.add(createLightOffMessage(CORRIDOR));
        lightMessages.add(createLightOffMessage(LOUNGE));
        Log.d(LOG_TAG, String.format("The following messag-Set has been created: ", lightMessages));
        return lightMessages;
    }

    public static BlindsMessage createBlindsOpenMessage(Room room) {
        BlindsMessage result;
        String addString;
        if (room == Room.KITCHEN || room == Room.DINING) {
            addString = "_dining_kitchen";
        } else if (room == ALL) {
            addString = "";
        } else {
            addString = "_" + room.getValue();
        }
        result = createBlindsMessage("blinds" + addString + "_open");

        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static BlindsMessage createBlindsHalfOpenMessage(Room room) {
        BlindsMessage result;
        String addString = "";
        if (room == Room.KITCHEN || room == Room.DINING) {
            addString = "_dining_kitchen";
        } else if (room == ALL) {
            addString = "";
        } else {
            addString = "_" + room.getValue();
        }
        result = createBlindsMessage("blinds" + addString + "_half");
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static BlindsMessage createBlindsCloseMessage(Room room) {
        BlindsMessage result;
        String addString;
        if (room == Room.KITCHEN || room == Room.DINING) {
            addString = "_dining_kitchen";
        } else if ( room == Room.SLEEPING) {
            addString = "";
        } else {
            addString = "_" + room.getValue();
        }
        result = createBlindsMessage("blinds" + addString + "_close");
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static Collection<Message> createAllBlindsOpenMessages(){
        Collection<Message> messages = new HashSet<Message>();
        messages.add(createBlindsOpenMessage(LOUNGE));
        messages.add(createBlindsOpenMessage(DINING));
        messages.add(createBlindsOpenMessage(SLEEPING));
        messages.add(createBlindsOpenMessage(ALL));
        return messages;
    }

    public static Collection<Message> createAllBlindsCloseMessages(){
        Collection<Message> messages = new HashSet<Message>();
        messages.add(createBlindsCloseMessage(LOUNGE));
        messages.add(createBlindsCloseMessage(DINING));
        messages.add(createBlindsCloseMessage(SLEEPING));
        messages.add(createBlindsCloseMessage(ALL));
        return messages;
    }

    public static Collection<Message> createAllBlindsHalfMessages(){
        Collection<Message> messages = new HashSet<Message>();
        messages.add(createBlindsHalfOpenMessage(LOUNGE));
        messages.add(createBlindsHalfOpenMessage(DINING));
        messages.add(createBlindsHalfOpenMessage(SLEEPING));
        messages.add(createBlindsHalfOpenMessage(ALL));
        return messages;
    }

    public static Collection<Message> createAllCurtainsOpen() {
        Collection<Message> messages = new HashSet<Message>();
        messages.add(createCurtainsOpenMessage(LOUNGE));
        messages.add(createCurtainsOpenMessage(CORRIDOR));
        messages.add(createCurtainsOpenMessage(SLEEPING));
        return messages;
    }

    public static Collection<Message> createAllCurtainsClose() {
        Collection<Message> messages = new HashSet<Message>();
        messages.add(createCurtainsCloseMessage(LOUNGE));
        messages.add(createCurtainsCloseMessage(CORRIDOR));
        messages.add(createCurtainsCloseMessage(SLEEPING));
        return messages;
    }

    public static CurtainMessage createCurtainsOpenMessage(Room room) {
        CurtainMessage result;
        if (room == Room.LOUNGE) {
            result = createCurtainMessage("lounge_curtain_open");
        } else if (room == Room.CORRIDOR) {
            result = createCurtainMessage("sleeping_hall_curtain_open");
        } else if (room == SLEEPING) {
            result = createCurtainMessage("sleeping_window_curtain_open");
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid room for Curtain Control!", room));
        }
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static CurtainMessage createCurtainsCloseMessage(Room room) {
        CurtainMessage result;
        if (room == Room.LOUNGE) {
            result = createCurtainMessage("lounge_curtain_close");
        } else if (room == Room.CORRIDOR) {
            result = createCurtainMessage("sleeping_hall_curtain_close");
        } else if (room == Room.SLEEPING) {
            result = createCurtainMessage("sleeping_window_curtain_close");
        } else {
            result = null;
            Log.w(LOG_TAG, String.format("%s is not a valid room for Curtain Control!", room));
        }
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static WindowMessage createWindowOpenMessage(Room room) {
        WindowMessage result;
        String roomString = room.getValue().toUpperCase();
        result = createWindowMessage(roomString, 10, WINDOW_SPEED_FAST);
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static WindowMessage createWindowCloseMessage(Room room) {
        WindowMessage result;
        String roomString = room.getValue().toUpperCase();
        result = createWindowMessage(roomString, 0, WINDOW_SPEED_FAST);
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
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
    public static LightMessage createLightIntesityMessage(Room room, int intensityInPerCent) {
        LightMessage result;
        int intensity = intensityPerCentToInt(intensityInPerCent);
        if (intensity == 255) {
            result = createLightOnMessage(room);
        } else if (intensity == 0) {
            result = createLightOffMessage(room);
        } else {
            if (room == Room.KITCHEN) {
                result = createLightMessage("kitchen_main_light_on", getIntensityMap(intensity));
            } else if (room == Room.SLEEPING) {
                result = createLightMessage("sleeping_light_on", getIntensityMap(intensity));
            } else if (room == Room.DINING) {
                result = createLightMessage("dining_light_on", getIntensityMap(intensity));
            } else if (room == Room.CORRIDOR) {
                result = createLightMessage("corridor_light_on", getIntensityMap(intensity));
            } else if (room == Room.LOUNGE) {
                result = createLightMessage("lounge_light_on", getIntensityMap(intensity));
            } else {
                result = null;
                Log.w(LOG_TAG, String.format("%s is not a valid context for Light Control!", room));
            }
        }

        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
        return result;
    }

    public static Collection<Message> createAllLightIntesityMessage(int percent){
        Collection<Message> messages = new HashSet<Message>();
        messages.add(createLightIntesityMessage(LOUNGE, percent));
        messages.add(createLightIntesityMessage(KITCHEN, percent));
        messages.add(createLightIntesityMessage(CORRIDOR, percent));
        messages.add(createLightIntesityMessage(SLEEPING, percent));
        messages.add(createLightIntesityMessage(DINING, percent));
        return messages;
    }

    /**
     * This method should be used to create a message to switch the main light of a room off
     *
     * @param room
     * @return
     */
    public static LightMessage createLightOffMessage(Room room) {
        LightMessage result;
        String addString = "";
        if (room == KITCHEN) {
            addString = "_main";
        }
        result = createLightMessage(room.getValue() + addString + "_light_off", getIntensityMap());
        Log.d(LOG_TAG, String.format("The following message has been created: ", result));
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
        if (perCent <= 0) {
            result = 0;
        } else if (perCent >= 100) {
            result = 255;
        } else {
            result = (255 * perCent) / 100;
        }
        Log.d(LOG_TAG,"Dim Value after calc: "+result);
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
