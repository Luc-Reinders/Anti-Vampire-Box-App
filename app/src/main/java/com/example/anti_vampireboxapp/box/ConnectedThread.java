package com.example.anti_vampireboxapp.box;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.anti_vampireboxapp.Constants.MESSAGE_READ;

import com.example.anti_vampireboxapp.Constants;

/**
 * This class handles the connection between the app and the Arduino once the connection has been
 * initialized. It also handles the messages send to the arduino and read from the Arduino on the
 * lowest level.
 */
public class ConnectedThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private Handler handler;



    public ConnectedThread(BluetoothSocket socket, Handler handler) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        this.handler = handler;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }



    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes = 0; // bytes returned from read()
        this.write(Constants.REQUEST_POWER_LEVEL); //Starting message to start loop
        this.write(Constants.TURN_CURRENT_VAMP_DEVICE_OFF);
        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                /*
                Read from the InputStream from Arduino until termination character is reached.
                Then send the whole String message to GUI Handler. ඞ
                */
                byte charByte = (byte) mmInStream.read();
                if(charByte == 35) { //Stopping char is an hashtag '#'
                    String readMessage = new String(buffer,0,bytes);
                    handler.obtainMessage(MESSAGE_READ,readMessage).sendToTarget();
                    bytes = 0;
                } else {
                    buffer[bytes] = charByte;
                    bytes++;
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }



    /* Call this to send data to the remote device */
    public void write(String input) {
        byte[] bytes = input.getBytes(); //converts entered String into bytes
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) {
            Log.e("Send Error","Unable to send message",e);
        }
    }



    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
