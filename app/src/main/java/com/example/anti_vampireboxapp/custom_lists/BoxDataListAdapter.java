package com.example.anti_vampireboxapp.custom_lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anti_vampireboxapp.R;
import com.example.anti_vampireboxapp.box.AntiVampBox;

import java.util.List;

/**
 * Custom ArrayAdapter for a ListView to show the data collected with the help of the Arduino which
 * is shown in the DataActivity class.
 */
public class BoxDataListAdapter extends ArrayAdapter<AntiVampBox> {

    /**
     * Match constructor from super.
     * @param context idk
     * @param resource idk
     * @param boxes List of the Anti-Vampire boxes.
     */
    public BoxDataListAdapter(@NonNull Context context, int resource, @NonNull List<AntiVampBox> boxes) {
        super(context, resource, boxes);
    }

    /**
     * Override {@link #getView(int, View, ViewGroup)} from super to make it return the custom
     * row view that shows the data.
     * @param position index of the row.
     * @param convertView idk
     * @param parent idk
     * @return the row of the list as a View.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get non-initialized row view from LayoutInflater.
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.box_data_list, null, true);

        //Get Anti-Vampire box that corresponds with this row.
        AntiVampBox selectedBox = this.getItem(position);

        //Get all TextView components.
        TextView boxName = rowView.findViewById(R.id.box_name_data_text);
        TextView powerUsageVamp = rowView.findViewById(R.id.power_usage_data_text);
        TextView moneySaved = rowView.findViewById(R.id.money_saved_data_text);

        //Set text to TextView components so that the data shows up in the row.
        if(selectedBox != null) {
            boxName.setText(selectedBox.getName());
            powerUsageVamp.setText(Double.toString(selectedBox.getVampDeviceUsage()));
            moneySaved.setText(Double.toString(selectedBox.getMoneySaved()));
        }
        else {
            if(position == 0) { //Legend
                boxName.setText("Box name");
                powerUsageVamp.setText("Vamp usage");
                moneySaved.setText("Money saved");
            }
            else { //total
                boxName.setText("Total"); //TODO: HAS TO BE CALCULATED
                powerUsageVamp.setText("0");
                moneySaved.setText("0");
            }
        }

        return rowView;
    }
}
