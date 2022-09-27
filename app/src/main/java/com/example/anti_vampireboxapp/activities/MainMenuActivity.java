package com.example.anti_vampireboxapp.activities;


import android.view.View;
import android.widget.Button;

import com.example.anti_vampireboxapp.R;

public class MainMenuActivity extends Activity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_main_menu;
    }

    @Override
    public void buildComponents() {

    }


    public void toDataActivity(View v) {
        goToActivity(DataActivity.class);
    }
    public void toBoxesActivity(View v) {
        goToActivity(BoxesActivity.class);
    }
}