package com.example.anti_vampireboxapp.box;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * This class represents the connection from the app to an Arduino.
 */
public class ArduinoConnection {

    private ConnectionThread connectionThread;
    private BluetoothAdapter bluetoothAdapter;


    /**
     * Constructor of this class, which automatically initializes the connection.
     */
    public ArduinoConnection(String deviceAddress, Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        connectionThread = new ConnectionThread(bluetoothAdapter, deviceAddress, context);
        connectionThread.start();
    }

}
