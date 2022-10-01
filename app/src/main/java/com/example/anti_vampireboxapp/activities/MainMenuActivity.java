package com.example.anti_vampireboxapp.activities;


import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.anti_vampireboxapp.BoxesManager;
import com.example.anti_vampireboxapp.R;

public class MainMenuActivity extends Activity {

    ToggleButton antiVampBoxesToggleButton;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main_menu;
    }

    @Override
    public void buildComponents() {
        antiVampBoxesToggleButton = findViewById(R.id.mainToggleButton);

        if(BoxesManager.getBoxes().size() > 0) {
            if(BoxesManager.areAllBoxesEnabled()) {
                antiVampBoxesToggleButton.setChecked(true);
            }
            else if(!BoxesManager.areAllBoxesDisabled()) {
                antiVampBoxesToggleButton.setChecked(true);
                antiVampBoxesToggleButton.setText("Custom");
            }
        }
    }

    public void toggleAllAntiVampBoxes(View v) {
        if(antiVampBoxesToggleButton.isChecked()) {
            BoxesManager.setCurrentAllBoxes(true);
        }
        else {
            BoxesManager.setCurrentAllBoxes(false);
        }
    }


    public void toDataActivity(View v) {
        goToActivity(DataActivity.class);
    }
    public void toBoxesActivity(View v) {
        goToActivity(BoxesActivity.class);
    }
}