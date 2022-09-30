package com.example.anti_vampireboxapp.custom_lists;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class BoxListAdapter extends ArrayAdapter<AntiVampBox> {

    public BoxListAdapter(@NonNull Context context, int resource, @NonNull List<AntiVampBox> boxes) {
        super(context, resource, boxes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.box_list, null, true);

        AntiVampBox selectedBox = this.getItem(position);

        EditText editText = rowView.findViewById(R.id.box_name);
        FloatingActionButton wifiButton = rowView.findViewById(R.id.box_wifi_button);
        Switch powerSwitch = rowView.findViewById(R.id.box_switch);

        editText.setText(selectedBox.getName());
        powerSwitch.setChecked(selectedBox.isEnabled());

        //TODO: DOES THIS NOT CONSTANTLY ADD THE SAME LISTENERS TO THE SAME COMPONENTS?
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
        powerSwitch.setOnCheckedChangeListener(
                (compoundButton, b) -> selectedBox.setEnabled(powerSwitch.isChecked())
        );

        return rowView;
    }
}
