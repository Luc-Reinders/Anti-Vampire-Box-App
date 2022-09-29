package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.example.anti_vampireboxapp.custom_lists.BoxListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxesActivity extends Activity {

    ListView boxListView;
    ArrayList<AntiVampBox> boxes = new ArrayList<>(); //TODO: MAKE THIS BOX OBJECTS INSTEAD OF STRINGS
    BoxListAdapter boxListAdapter; //TODO: CUSTOM LISTVIEW AND/OR ADAPTER

    @Override
    public int getLayoutID() {
        return R.layout.activity_boxes;
    }

    @Override
    public void buildComponents() {
        boxListView = findViewById(R.id.boxListView);
        boxListAdapter = new BoxListAdapter(this, R.layout.box_list, boxes);
        boxListView.setAdapter(boxListAdapter);
    }



    public void addBox(View v) {
        boxes.add(new AntiVampBox("*BoxName*"));
        updateAdapter();
    }

    private void updateAdapter() {
        boxListAdapter.notifyDataSetChanged();
    }



    public void backToMainMenu(View v) {
        goToActivity(MainMenuActivity.class);
    }
}