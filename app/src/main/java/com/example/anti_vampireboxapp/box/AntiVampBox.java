package com.example.anti_vampireboxapp.box;

import androidx.annotation.NonNull;

/**
 * This class represents the Anti-Vampire box.
 */
public class AntiVampBox {

    /**
     * Name of the Anti-Vampire box.
     */
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Boolean to indicate if we are letting power through to the Vampire device or not.
     */
    private boolean currentEnabled = false;
    public boolean isCurrentEnabled() {
        return currentEnabled;
    }
    public void setCurrentEnabled(boolean currentEnabled) {
        this.currentEnabled = currentEnabled;
    }

    /**
     * Variable to keep track of the time that the current has been enabled in seconds.
     */
    private double currentEnabledTime = 0;
    /**
     * Variable to keep track of the time that the current has been disabled in seconds.
     */
    private double currentDisabledTime = 0; //Disabled time in secs

    /**
     * Amount of Watts used by the connected vampire device(s).
     */
    private double vampDevicePowerUsage = 0;
    public double getVampDeviceUsage() {
        return vampDevicePowerUsage;
    }

    /**
     * Constructor of this class.
     * @param name Name of the Anti-Vampire box.
     */
    public AntiVampBox(String name) {
        setName(name);
    }

    /**
     * Gets the amount of money we have saved with this Anti-Vampire box.
     * @return money
     */
    public double getMoneySaved() {
        return 0; //TODO: do the calculations
    }

    /**
     * Overriding the {@link #toString()} method to return the name of the box.
     * @return name of this box.
     */
    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
