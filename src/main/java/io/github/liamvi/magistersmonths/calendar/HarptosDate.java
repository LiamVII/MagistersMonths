package io.github.liamvi.magistersmonths.calendar;

import java.time.Instant;
import java.util.Date;

public class HarptosDate {

    private final long EPOCHDATETIME = 0; // filler for now, will eventually pull from config files.
    private final Instant instant;
    public HarptosDate() {
        instant = Instant.now();
    }

    public long getRealTime() {
        return instant.toEpochMilli();
    }

    // Calculate the difference between the epoch and current real time, then multiply the result by 3.
    // The result will be 3x longer than the time passed in the real world, thus speeding time by 3x.
    // This allows for a deterministic calculation of Harptos datetime so long as you know the epoch.
    public long getHarptosTime() {
        long realTime = instant.toEpochMilli();
        return (realTime - EPOCHDATETIME) * 3;
    }

    public long getEpoch() {
        return EPOCHDATETIME;
    }






}

