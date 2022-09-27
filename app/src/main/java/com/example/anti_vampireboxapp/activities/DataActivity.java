package com.example.anti_vampireboxapp.activities;


import android.view.View;

import com.example.anti_vampireboxapp.R;

public class DataActivity extends Activity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_data;
    }

    @Override
    public void buildComponents() {

    }

    public void backToMainMenu(View v) {
        goToActivity(MainMenuActivity.class);
    }
}