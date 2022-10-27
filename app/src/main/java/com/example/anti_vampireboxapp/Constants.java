package com.example.anti_vampireboxapp;

/**
 * A class for constants.
 */
public class Constants {

    //TODO: COMMENT SPECIFICATIONS ABOUT ELECTRONICS AND LINK SOURCES.

    /**
     * Arduino maximum electric cost per year in euros as of 2022.
     */
    public static double ARDUINO_MAX_COST_YEAR = 4;

    /**
     * Arduino maximum electric cost per second in euros as of 2022.
     */
    public static double ARDUINO_MAX_COST_SECOND = ARDUINO_MAX_COST_YEAR/31536000d;
    /**
     * Average cost for one kWh in euros in the Netherlands.
     */
    public static double KWH_COST = 0.885;
    /**
     * Average cost of one J in euros in the Netherlands.
     */
    public static double J_COST = KWH_COST/(3600000d);



    /**
     * used in bluetooth handler to identify message status
     */
    public final static int CONNECTING_STATUS = 1;
    /**
     * used in bluetooth handler to identify message update
     */
    public final static int MESSAGE_READ = 2;



    public final static String TURN_CURRENT_VAMP_DEVICE_OFF = "0";
    public final static String TURN_CURRENT_VAMP_DEVICE_ON = "1";
    public final static String REQUEST_POWER_LEVEL = "2";


    //MAC ADDRESS: 98:D3:41:F6:8A:83

}
