package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ListView;

import com.example.anti_vampireboxapp.BoxesManager;
import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.example.anti_vampireboxapp.custom_lists.BoxDataListAdapter;

import java.util.ArrayList;

/**
 * This class is an Activity that presents the Data collected with the Arduino power sensor.
 */
public class DataActivity extends Activity {

    /**
     * Listview of the Anti-Vampire boxes.
     */
    ListView boxDataListView;
    /**
     * ArrayList of the currently active Anti-Vampire boxes
     */
    ArrayList<AntiVampBox> boxes = new ArrayList<>();
    /**
     * BoxDataListAdapter for {@link #boxDataListView} to connect to.
     */
    BoxDataListAdapter boxDataListAdapter;

    /**
     * Get layout ID
     * @return Layout ID corresponding with the XML file.
     */
    @Override
    public int getLayoutID() {
        return R.layout.activity_data;
    }

    /**
     * Declare {@link #boxDataListView} and {@link #boxDataListAdapter}. Also connects the adapter
     * to the ListView.
     */
    @Override
    public void declareComponents() {
        boxes.add(null);
        boxes.addAll(BoxesManager.getBoxes());
        boxes.add(null);

        boxDataListView = findViewById(R.id.box_list_data_view);
        boxDataListAdapter = new BoxDataListAdapter(this, R.layout.box_data_list, boxes);
        boxDataListView.setAdapter(boxDataListAdapter);
    }

    /**
     * Method that goes back to the main menu activity.
     * @param v View of this activity.
     */
    public void backToMainMenu(View v) {
        goToActivity(MainMenuActivity.class);
    }
}