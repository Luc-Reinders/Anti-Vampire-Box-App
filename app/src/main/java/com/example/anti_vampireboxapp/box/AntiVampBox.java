package com.example.anti_vampireboxapp.box;

import androidx.annotation.NonNull;

public class AntiVampBox {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private boolean currentEnabled = false;
    public boolean isCurrentEnabled() {
        return currentEnabled;
    }
    public void setCurrentEnabled(boolean currentEnabled) {
        this.currentEnabled = currentEnabled;
    }

    private double currentEnabledTime = 0; //Enabled time in secs
    private double currentDisabledTime = 0; //Disabled time in secs

    private double vampDevicePowerUsage = 0; //Amount of Watts used by the vampire device(s).
    public double getVampDeviceUsage() {
        return vampDevicePowerUsage;
    }


    //TODO: ILL DO THE CALCULATIONS LATER
    public double getMoneySaved() {
        return 0;
    }


    public AntiVampBox(String name) {
        setName(name);
    }



    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
