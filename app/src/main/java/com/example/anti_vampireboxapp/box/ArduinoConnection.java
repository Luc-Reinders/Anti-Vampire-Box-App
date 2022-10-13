package com.example.anti_vampireboxapp.box;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * This class represents the connection from the app to an Arduino.
 */
public class ArduinoConnection {

    private ConnectionThread connectionThread = new ConnectionThread();
    private BluetoothAdapter bluetoothAdapter;

    public boolean connect(String deviceAddress, Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!connectionThread.createConnection(bluetoothAdapter, deviceAddress, context)) {
            return false;
        }
        if(!connectionThread.startConnection()) {
            return false;
        }
        return true;
    }



    public VampDeviceUsageInfo getVampDeviceUsageInfo() {
        return connectionThread.getVampDeviceUsageInfo();
    }

    public double getCurrentVampEnabledTime() {
        return connectionThread.getCurrentEnabledTime();
    }

    public double getCurrentVampDisabledTime() {
        return connectionThread.getCurrentDisabledTime();
    }

}
