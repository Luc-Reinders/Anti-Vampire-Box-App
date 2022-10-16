package com.example.anti_vampireboxapp.box;

/**
 * This class keeps track of the power usage of a vampire device. This contains especially
 * data about the average power usage in sleep mode and average power usage while active.
 */
public class VampDeviceUsageInfo {

    /**
     * The usage at this moment in time.
     */
    private double usage = 0;
    /**
     * Average power usage while in sleep mode
     */
    private double sleepUsageAvg = 0;
    /**
     * Average power usage while in active mode
     */
    private double activeUsageAvg = 1;

    /**
     * Gets the usage in Watts at this moment in time.
     * @return A double in Watts
     */
    public double getUsage() {
        return usage;
    }
    /**
     * Gets the usage in Watts when the device is active.
     * @return A double in Watts
     */
    public double getActiveUsageAvg() {
        return activeUsageAvg;
    }
    /**
     * Gets the usage in Watts when the device is sleeping.
     * @return A double in Watts
     */
    public double getSleepUsageAvg() {
        return sleepUsageAvg;
    }

    /**
     * Adds the current power usage of the vampire device to this class for data collection.
     * We change the value of {@link #sleepUsageAvg} or {@link #activeUsageAvg} based on whether
     * powerUsage is closer to sleepUsageAvg or activeUsageAvg.
     * @param powerUsage a double greater than zero
     */
    public void give(double powerUsage) {
        usage = powerUsage;
        if(Math.abs(sleepUsageAvg - powerUsage) < Math.abs(activeUsageAvg - powerUsage)) {
            sleepUsageAvg = (sleepUsageAvg + powerUsage)/2;
        } else {
            activeUsageAvg = (activeUsageAvg + powerUsage)/2;
        }
    }

}
