package com.example.anti_vampireboxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This abstract class was made to have pre-determined behaviour for activities and make it more
 * streamlined.
 */
public abstract class Activity extends AppCompatActivity {

    /**
     * Override the onCreate method to include getLayoutID() and buildComponents() to make
     * it more streamlined.
     * @param savedInstanceState Idk what this is lol
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        declareComponents();
    }

    /**
     * Abstract method to get the layout ID number of the corresponding activity.
     * @return ID of the XML layout file.
     */
    public abstract int getLayoutID();

    /**
     * Abstract method to declare the components of your activity you want to use.
     */
    public abstract void declareComponents();


    /**
     * Override the onBackPressed method to do nothing to prevent being boot back to a previous
     * activity.
     */
    @Override
    public void onBackPressed() {}

    /**
     * Method to go to the given activity.
     * @param activity Activity we want to go to.
     */
    public void goToActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }
}
