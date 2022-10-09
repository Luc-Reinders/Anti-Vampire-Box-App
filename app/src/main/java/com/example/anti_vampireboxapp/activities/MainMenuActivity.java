package com.example.anti_vampireboxapp.activities;

import android.view.View;
import android.widget.ToggleButton;
import com.example.anti_vampireboxapp.BoxesManager;
import com.example.anti_vampireboxapp.R;

/**
 * This class is an Activity for the main menu in which we have a main toggle button to toggle
 * the state of the Anti-Vampire boxes.
 */
public class MainMenuActivity extends Activity {

    /**
     * Main button to enable/disable the flow of the Anti-Vampire Boxes.
     */
    ToggleButton antiVampBoxesToggleButton;

    /**
     * Get layout ID
     * @return Layout ID of corresponding XML file.
     */
    @Override
    public int getLayoutID() {
        return R.layout.activity_main_menu;
    }

    /**
     * Declare {@link #antiVampBoxesToggleButton} and also sets the button state that matches
     * the states of the Anti-Vampire boxes. So if all the boxes are letting power through, the
     * state of the button will be on. If some boxes are letting power through and some are not,
     * the text is set to "Custom".
     */
    @Override
    public void declareComponents() {
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

    /**
     * Method that toggles the Anti-Vampire boxes.
     * @param v View of this Activity
     */
    public void toggleAllAntiVampBoxes(View v) {
        if(antiVampBoxesToggleButton.isChecked()) {
            BoxesManager.setCurrentAllBoxes(true);
        }
        else {
            BoxesManager.setCurrentAllBoxes(false);
        }
    }

    /**
     * Method that goes to the Data Activity.
     * @param v View of this activity
     */
    public void toDataActivity(View v) {
        goToActivity(DataActivity.class);
    }

    /**
     * Method that goes to the Boxes Activity.
     * @param v View of this activity.
     */
    public void toBoxesActivity(View v) {
        goToActivity(BoxesActivity.class);
    }
}