package com.example.anti_vampireboxapp.box;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.os.ParcelUuid;
import android.util.Log;
import android.os.Message;

import java.io.IOException;
import java.util.UUID;

import android.os.Handler;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import static com.example.anti_vampireboxapp.Constants.CONNECTING_STATUS;
import static com.example.anti_vampireboxapp.Constants.MESSAGE_READ;

import com.example.anti_vampireboxapp.Constants;
import com.example.anti_vampireboxapp.activities.Activity;

/**
 * This class handles the initialization of the connection and once connected, handles
 * the messages send to the Arduino and received from the Arduino.
 * TODO: The Permissions are really scuffed. Def something to look at if we have enough time.
 */
public class ConnectionThread extends Thread {

    private ConnectedThread connectedThread = null;
    private BluetoothSocket mmSocket;
    private Activity activityContext;

    private boolean currentEnabledForVampDevice = false;
    private double timeSinceLastCheck;
    /**
     * The time the current has been enabled for the Vampire device in millis
     */
    private double currentEnabledTime;
    /**
     * The time that current has been disabled for the Vampire device in millis
     */
    private double currentDisabledTime;

    public boolean isCurrentEnabledForVampDevice() {
        return currentEnabledForVampDevice;
    }
    /**
     * This object keeps track of the vampire device usage.
     */
    private VampDeviceUsageInfo vampDeviceUsageInfo = new VampDeviceUsageInfo();

    public VampDeviceUsageInfo getVampDeviceUsageInfo() {
        return vampDeviceUsageInfo;
    }


    public double getCurrentEnabledTime() {
        if (currentEnabledForVampDevice) {
            currentEnabledTime += getTimeSinceLastCheck();
            timeSinceLastCheck = System.currentTimeMillis();
        }
        return currentEnabledTime;
    }

    public double getCurrentDisabledTime() {
        if (!currentEnabledForVampDevice) {
            currentDisabledTime += getTimeSinceLastCheck();
            timeSinceLastCheck = System.currentTimeMillis();
        }
        return currentDisabledTime;
    }

    /*TODO: These commands should be send to the Arduino. Discuss with Iwo what these commands
       should be.*/
    public void enableCurrentToVampDevice() {
        currentEnabledForVampDevice = true;
        connectedThread.write(Constants.TURN_CURRENT_VAMP_DEVICE_ON);
    }

    public void disableCurrentToVampDevice() {
        currentEnabledForVampDevice = false;
        connectedThread.write(Constants.TURN_CURRENT_VAMP_DEVICE_OFF);
    }

    private double getTimeSinceLastCheck() {
        return System.currentTimeMillis() - timeSinceLastCheck;
    }

    /*
        This handler handles the feedback of the Arduino and connection status to said Arduino.
         */
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case CONNECTING_STATUS:
                    //This handles GUI, which is not needed I suppose?
                    switch (msg.arg1) {
                        case 1: //Device is connected
                            /*
                            toolbar.setSubtitle("Connected to " + deviceName);
                            progressBar.setVisibility(View.GONE);
                            buttonConnect.setEnabled(true);
                            buttonToggle.setEnabled(true);
                             */
                            break;
                        case -1: //Connection to device failed
                            /*
                            toolbar.setSubtitle("Device fails to connect");
                            progressBar.setVisibility(View.GONE);
                            buttonConnect.setEnabled(true);
                             */
                            break;
                    }

                    break;

                case MESSAGE_READ:
                    /*We only receive information about the current.*/
                    String arduinoMsg = msg.obj.toString(); //ASCII Msg of current
                    double power = Integer.parseInt(arduinoMsg) * 0.230; //

                    System.out.println("Average sleep: " + vampDeviceUsageInfo.getSleepUsageAvg());
                    System.out.println("Average active: " + vampDeviceUsageInfo.getActiveUsageAvg());

                    vampDeviceUsageInfo.add(power);
                    break;
            }

            if(connectedThread != null) {
                connectedThread.write(Constants.REQUEST_POWER_LEVEL);
            }
        }
    };


    public boolean createConnection(BluetoothAdapter bluetoothAdapter, String address, Activity activity) {
        /*
        Use a temporary object that is later assigned to mmSocket
        because mmSocket is final.
        */
        if (!BluetoothAdapter.checkBluetoothAddress(address)) {
            return false;
        }
        activityContext = activity;
        timeSinceLastCheck = System.currentTimeMillis();
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
        BluetoothSocket tmp = null;

        /* TODO: Android studio is telling me there is a better way to do this with the help of
            activityCompat.requestPermissions(). Here is the full message: */
        /* TODO: Consider calling
            ActivityCompat#requestPermissions here to request the missing permissions, and then
            overriding public void onRequestPermissionsResult(int requestCode, String[] permissions,
            int[] grantResults) to handle the case where the user grants the permission.
            See the documentation for ActivityCompat#requestPermissions for more details. */

        if (ActivityCompat.checkSelfPermission(activityContext, Manifest.permission.BLUETOOTH_CONNECT ) != PackageManager.PERMISSION_GRANTED ) {
            return false;
        }

        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if(uuids.length == 0) {
            return false;
        }
        UUID uuid = bluetoothDevice.getUuids()[0].getUuid();

        try {
            /*
            Get a BluetoothSocket to connect with the given BluetoothDevice.
            Due to Android device varieties,the method below may not work fo different devices.
            You should try using other methods i.e. :
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            */
            tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Log.e(TAG, "Socket's create() method failed", e);
            return false;
        }
        mmSocket = tmp;
        return true;
    }


    //TODO: THIS DOES PROBABLY NOT EVEN GIVE CORRECT FEEDBACK DUE TO PARALLEL THREADING
    private boolean connectionSucceeded = true;
    public boolean startConnection() {
        start();
        return connectionSucceeded;
    }
    public void run() {
        // Cancel discovery because it otherwise slows down the connection.
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (ActivityCompat.checkSelfPermission(activityContext,
                Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            connectionSucceeded = false;
            return;
        }
        bluetoothAdapter.cancelDiscovery();
        try {
            /* Connect to the remote device through the socket. This call blocks
            until it succeeds or throws an exception. */
            mmSocket.connect();
            Log.e("Status", "Device connected");
            handler.obtainMessage(CONNECTING_STATUS, 1, -1).sendToTarget();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            connectionSucceeded = false;
            try {
                mmSocket.close();
                Log.e("Status", "Cannot connect to device");
                handler.obtainMessage(CONNECTING_STATUS, -1, -1).sendToTarget();
            } catch (IOException closeException) {
                Log.e(TAG, "Could not close the client socket", closeException);
            }
            return;
        }

        // The connection attempt succeeded. Perform work associated with
        // the connection in a separate thread.
        connectedThread = new ConnectedThread(mmSocket, handler);
        connectedThread.run();
    }



    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the client socket", e);
        }
    }

}
