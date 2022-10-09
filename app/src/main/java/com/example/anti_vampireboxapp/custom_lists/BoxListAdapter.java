package com.example.anti_vampireboxapp.custom_lists;

import android.content.Context;
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

import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Custom ArrayAdapter for a ListView to manage the Anti-Vampire boxes.
 */
public class BoxListAdapter extends ArrayAdapter<AntiVampBox> {

    /**
     * Match constructor from super.
     * @param context idk
     * @param resource idk
     * @param boxes List of Anti-Vampire boxes.
     */
    public BoxListAdapter(@NonNull Context context, int resource, @NonNull List<AntiVampBox> boxes) {
        super(context, resource, boxes);
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
        EditText editText = rowView.findViewById(R.id.box_name);
        FloatingActionButton wifiButton = rowView.findViewById(R.id.box_wifi_button);
        Switch powerSwitch = rowView.findViewById(R.id.box_switch);

        /*Set states of components to match the state of the Anti-Vampire box that corresponds
        to this row. */
        editText.setText(selectedBox.getName());
        powerSwitch.setChecked(selectedBox.isCurrentEnabled());

        //Add listener to editText to update names of the boxes.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                selectedBox.setName(editText.getText().toString());
            }
        });
        /*Set the state of the powerSwitch to the corresponding state of the Anti-Vampire box
        that corresponds to this row. */
        powerSwitch.setOnCheckedChangeListener(
                (compoundButton, b) -> selectedBox.setCurrentEnabled(powerSwitch.isChecked())
        );

        return rowView;
    }
}
