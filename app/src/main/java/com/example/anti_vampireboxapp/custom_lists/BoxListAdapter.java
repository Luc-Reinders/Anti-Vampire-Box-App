package com.example.anti_vampireboxapp.custom_lists;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Custom ArrayAdapter for a ListView to manage the Anti-Vampire boxes.
 */
public class BoxListAdapter extends ArrayAdapter<AntiVampBox> {

    /**
     * The Activity (or Context) in which this boxListAdapter is created.
     */
    private Context currContext;

    /**
     * Match constructor from super.
     * @param context idk
     * @param resource idk
     * @param boxes List of Anti-Vampire boxes.
     */
    public BoxListAdapter(@NonNull Context context, int resource, @NonNull List<AntiVampBox> boxes,
                          Context currContext) {
        super(context, resource, boxes);
        this.currContext = currContext;
    }

    /**
     * Override {@link #getView(int, View, ViewGroup)} from super to make it return the custom
     * row view with all the components to manage the box at that row.
     * @param position index of the row in the ListView.
     * @param convertView idk
     * @param parent idk
     * @return the row of the list as a View.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get non-initialized row view from LayoutInflater.
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.box_list, null, true);

        //Get Anti-Vampire box that corresponds with this row.
        AntiVampBox selectedBox = this.getItem(position);

        //Get components: box name text, wifi button and an individual power switch.
        EditText boxNameEditText = rowView.findViewById(R.id.box_name);
        EditText deviceAddressEditText = rowView.findViewById(R.id.mac_address);
        FloatingActionButton wifiButton = rowView.findViewById(R.id.box_wifi_button);
        Switch powerSwitch = rowView.findViewById(R.id.box_switch);

        /*Set states of components to match the state of the Anti-Vampire box that corresponds
        to this row. */
        boxNameEditText.setText(selectedBox.getName());
        deviceAddressEditText.setText(selectedBox.getAddress());
        powerSwitch.setChecked(selectedBox.isCurrentEnabled());

        switch(selectedBox.getConnectionState()) {
            case NOT_CONNECTED:
                wifiButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                break;
            case CONNECTING:
                wifiButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                break;
            case CONNECTED:
                wifiButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;
            case CONNECTION_FAILED:
                wifiButton.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                break;
        }

        //Add listener to update names of the box.
        boxNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                selectedBox.setName(boxNameEditText.getText().toString());
            }
        });
        //Add listener to update the given device address of the box.
        deviceAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                selectedBox.setAddress(deviceAddressEditText.getText().toString());
            }
        });
        wifiButton.setOnClickListener(view -> {
            selectedBox.connect(currContext);
            //System.out.println(selectedBox.getName() + ", " + selectedBox.getAddress());
        });
        /*Set the state of the powerSwitch to the corresponding state of the Anti-Vampire box
        that corresponds to this row. */
        powerSwitch.setOnCheckedChangeListener(
                (compoundButton, b) -> selectedBox.setCurrentEnabled(powerSwitch.isChecked())
        );

        return rowView;
    }
}
