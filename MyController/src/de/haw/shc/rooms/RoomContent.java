package de.haw.shc.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomContent {

    public static class RoomItem {

        public String id;
        public String content;

        public RoomItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<RoomItem> ITEMS = new ArrayList<RoomItem>();
    public static Map<String, RoomItem> ITEM_MAP = new HashMap<String, RoomItem>();

    static {
        addItem(new RoomItem("1", "Küche"));
        addItem(new RoomItem("2", "Esszimmer"));
        addItem(new RoomItem("3", "Wohnzimmer"));
        addItem(new RoomItem("4", "Schlafzimmer"));
        addItem(new RoomItem("5", "Flur"));
        addItem(new RoomItem("6", "Bad"));
    }

    private static void addItem(RoomItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
