package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ListView;

import com.example.anti_vampireboxapp.BoxesManager;
import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.example.anti_vampireboxapp.custom_lists.BoxListAdapter;

import java.util.ArrayList;

public class BoxesActivity extends Activity {

    ListView boxListView;
    ArrayList<AntiVampBox> boxes = BoxesManager.getBoxes();
    BoxListAdapter boxListAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_boxes;
    }

    @Override
    public void buildComponents() {
        boxListView = findViewById(R.id.box_list_view);
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