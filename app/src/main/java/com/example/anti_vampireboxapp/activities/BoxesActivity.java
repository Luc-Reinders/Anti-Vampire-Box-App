package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ListView;

import com.example.anti_vampireboxapp.BoxesManager;
import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.example.anti_vampireboxapp.custom_lists.BoxListAdapter;

import java.util.ArrayList;

/**
 * This class is an Activity to manage the Anti-Vampire boxes.
 */
public class BoxesActivity extends Activity {

    /**
     * Listview of the Anti-Vampire boxes.
     */
    ListView boxListView;
    /**
     * ArrayList of the currently active Anti-Vampire boxes
     */
    ArrayList<AntiVampBox> boxes = BoxesManager.getBoxes();
    /**
     * BoxListAdapter for {@link #boxListView} to connect to.
     */
    BoxListAdapter boxListAdapter;

    /**
     * Get layout ID
     * @return
     */
    @Override
    public int getLayoutID() {
        return R.layout.activity_boxes;
    }

    /**
     * Declare {@link #boxListView} and {@link #boxListAdapter}. Also connects the adapter
     * to the ListView.
     */
    @Override
    public void declareComponents() {
        boxListView = findViewById(R.id.box_list_view);
        boxListAdapter = new BoxListAdapter(this, R.layout.box_list, boxes);
        boxListView.setAdapter(boxListAdapter);
    }

    /**
     * Method that adds a box to the list.
     * @param v
     */
    public void addBox(View v) {
        boxes.add(new AntiVampBox("*BoxName*"));
        updateAdapter();
    }

    /**
     * Method that updates the adapter.
     */
    private void updateAdapter() {
        boxListAdapter.notifyDataSetChanged();
    }

    /**
     * Method that goes back to the main menu activity.
     * @param v
     */
    public void backToMainMenu(View v) {
        goToActivity(MainMenuActivity.class);
    }
}