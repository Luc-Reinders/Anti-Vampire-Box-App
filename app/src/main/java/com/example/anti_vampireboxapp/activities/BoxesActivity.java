package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anti_vampireboxapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxesActivity extends Activity {

    ListView boxListView;
    ArrayList<String> boxes = new ArrayList<>(); //TODO: MAKE THIS BOX OBJECTS INSTEAD OF STRINGS
    ArrayAdapter boxListAdapter; //TODO: CUSTOM LISTVIEW AND/OR ADAPTER

    @Override
    public int getLayoutID() {
        return R.layout.activity_boxes;
    }

    @Override
    public void buildComponents() {
        boxListView = findViewById(R.id.boxListView);
        boxListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, boxes);
        boxListView.setAdapter(boxListAdapter);
    }



    public void addBox(View v) {
        boxes.add("Box " + boxes.size());
        updateAdapter();
    }

    private void updateAdapter() {
        boxListAdapter.notifyDataSetChanged();
    }



    public void backToMainMenu(View v) {
        goToActivity(MainMenuActivity.class);
    }
}