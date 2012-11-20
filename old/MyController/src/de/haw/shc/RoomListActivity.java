package de.haw.shc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class RoomListActivity extends FragmentActivity
        implements RoomListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_twopane);

        if (findViewById(R.id.room_detail_container) != null) {
            mTwoPane = true;
            ((RoomListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.room_list))
                    .setActivateOnItemClick(true);
        }
    }

   
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(RoomDetailFragment.ARG_ITEM_ID, id);
            RoomDetailFragment fragment = new RoomDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.room_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, RoomDetailActivity.class);
            detailIntent.putExtra(RoomDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
