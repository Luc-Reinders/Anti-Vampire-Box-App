package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ListView;

import com.example.anti_vampireboxapp.BoxesManager;
import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.example.anti_vampireboxapp.custom_lists.BoxDataListAdapter;
import com.example.anti_vampireboxapp.custom_lists.BoxListAdapter;

import java.util.ArrayList;

public class DataActivity extends Activity {

    ListView boxDataListView;
    ArrayList<AntiVampBox> boxes = new ArrayList<>();
    BoxDataListAdapter boxDataListAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_data;
    }

    @Override
    public void buildComponents() {
        boxes.add(null);
        boxes.addAll(BoxesManager.getBoxes());
        boxes.add(null);

        boxDataListView = findViewById(R.id.box_list_data_view);
        boxDataListAdapter = new BoxDataListAdapter(this, R.layout.box_data_list, boxes);
        boxDataListView.setAdapter(boxDataListAdapter);
    }

    public void backToMainMenu(View v) {
        goToActivity(MainMenuActivity.class);
    }
}