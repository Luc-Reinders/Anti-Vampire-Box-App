package com.example.anti_vampireboxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        buildComponents();
    }

    public abstract int getLayoutID();
    public abstract void buildComponents();



    @Override
    public void onBackPressed() {}

    public void goToActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }
}
