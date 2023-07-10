package io.github.liamvi.magistersmonths.calendar;

import java.util.Date;

public class HarptosDate extends Date {

    // HarptosDate is only used to extend the Date object with useful methods for calculating time on the Harptos calendar.
    // It should not be involved in calculating the current temporal position on the Harptos calendar.
    // Didn't realise "Date" was essentially deprecated, but it's good practice so will use it for now and Time in future.

    private final long EPOCHDATETIME = 0; // filler for now, will eventually pull from config files.

    public HarptosDate() {
        super();
    }

    public long getRealTime() {
        return super.getTime();
    }

    // Calculate the difference between the epoch and current real time, then multiply the result by 3.
    // The result will be 3x longer than the time passed in the real world, thus speeding time by 3x.
    // This allows for a deterministic calculation of Harptos datetime so long as you know the epoch.
    public long getHarptosTime() {
        long realTime = super.getTime();
        return (EPOCHDATETIME - realTime) * 3;
    }

    public long getEpoch() {
        return EPOCHDATETIME;
    }






}

