package de.haw.shc;

import de.haw.shc.rooms.RoomContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RoomDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    RoomContent.RoomItem mItem;

    public RoomDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = RoomContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.room_detail)).setText(mItem.content);
        }
        return rootView;
    }
}
