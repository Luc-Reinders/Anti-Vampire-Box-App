package com.example.anti_vampireboxapp.box;

import static com.example.anti_vampireboxapp.Constants.ARDUINO_MAX_COST_SECOND;
import static com.example.anti_vampireboxapp.Constants.ARDUINO_MAX_COST_YEAR;
import static com.example.anti_vampireboxapp.Constants.J_COST;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.anti_vampireboxapp.activities.Activity;

/**
 * This class represents the Anti-Vampire box.
 */
public class AntiVampBox {

    /**
     * The connection to the Arduino
     */
    private ArduinoConnection arduinoConnection = new ArduinoConnection();
    private ConnectionState connectionState = ConnectionState.NOT_CONNECTED;

    /**
     * Name of the Anti-Vampire box.
     */
    private String name = "";

    /**
     * The MAC address of the Bluetooth device given by the user.
     */
    private String address = "";


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ConnectionState getConnectionState() {
        return connectionState;
    }

    public boolean isCurrentEnabled() {
        return arduinoConnection.isCurrentVampDeviceEnabled();
    }
    public void setCurrentEnabled(boolean currentEnabled) {
        arduinoConnection.setCurrentVampDeviceEnabled(currentEnabled);
    }


    /**
     * This method tries to connect the app to the Arduino with the given device address.
     * @param deviceAddress The MAC address of the Arduino
     * @param currActivity The activity that is currently active when initializing this
     *                     Anti-Vampire box. This is needed because of permissions.
     */
    public void connect(String deviceAddress, Activity currActivity) {
        if(connectionState == ConnectionState.NOT_CONNECTED || connectionState == ConnectionState.CONNECTION_FAILED) {
            connectionState = ConnectionState.CONNECTING;
            if(arduinoConnection.connect(deviceAddress, currActivity)) {
                connectionState = ConnectionState.CONNECTED;
            } else {
                connectionState = ConnectionState.CONNECTION_FAILED;
            }
        }
    }
    public void connect(Activity currActivity) {
        connect(address, currActivity);
    }



    /**
     * Amount of Watts used by the connected vampire device(s) at this moment in time.
     */
    public double getVampDeviceUsage() {
        return arduinoConnection.getVampDeviceUsageInfo().getUsage();
    }

    /**
     * Gets the amount of money we have saved with this Anti-Vampire box.
     * @return money
     */
    public double getMoneySaved() {
        double arduinoCost = getActiveTime()*ARDUINO_MAX_COST_SECOND;

        double sleepUsageAvg = arduinoConnection.getVampDeviceUsageInfo().getSleepUsageAvg();
        double vampDeviceMoneySaved = sleepUsageAvg * getCurrentVampDisabledTime() * J_COST;

        return vampDeviceMoneySaved - arduinoCost;
    }



    /**
     * Variable to keep track of the time that the current has been enabled in seconds.
     */
    public double getCurrentVampEnabledTime() {
        return arduinoConnection.getCurrentVampEnabledTime()/1000d;
    }

    /**
     * Variable to keep track of the time that the current has been disabled in seconds.
     */
    public double getCurrentVampDisabledTime() {
        return arduinoConnection.getCurrentVampDisabledTime()/1000d;
    }

    /**
     * Gets active running time of the Anti Vampire Box
      * @return time in seconds
     */
    public double getActiveTime() {
        return getCurrentVampEnabledTime() + getCurrentVampDisabledTime();
    }

    /**
     * Overriding the toString method to return the name of the box.
     * @return name of this box.
     */
    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
