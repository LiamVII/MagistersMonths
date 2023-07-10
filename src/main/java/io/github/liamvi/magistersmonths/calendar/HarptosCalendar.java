package io.github.liamvi.magistersmonths.calendar;

import java.util.Calendar;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class HarptosCalendar {

    // What do I need to define -
    /*
    Months - 12, 30 days each
    Weeks? No
    Days of week? No
    Just day, month, year & time

    86,400,000 milliseconds in a day
    3,600,000 milliseconds in an hour
    60,000 milliseconds in a minute

    Taking an example epoch of 0 milliseconds

    0 + 3,600,000 milliseconds = Day 1, 1:00am, Month 1, Year 1

    We can use Java TimeUnit to make the conversion from milliseconds into seconds, minutes, and days
    Realistically we only need to update the time every 20 seconds - 10 seconds if we're being really serious.

    Something like:

    [list of months], [Hammer = day 0 - 30, Alturiak day 31 - 60, etc.)
    [current time would be (current time in ms - days in ms) converted to minutes]
    Much less robust but much easier to implement for our purposes
     */

    private static final TreeMap<Integer, String> monthMap = new TreeMap<>();

    static {
        monthMap.put(30, "Hammer");
        monthMap.put(60, "Alturiak");
        monthMap.put(90, "Ches");
        monthMap.put(120, "Tarsakh");
        monthMap.put(150, "Mirtul");
        monthMap.put(180, "Kythorn");
        monthMap.put(210, "Flamerule");
        monthMap.put(240, "Eleasis");
        monthMap.put(270, "Eleint");
        monthMap.put(300, "Marpenoth");
        monthMap.put(330, "Uktar");
        monthMap.put(360, "Nightal");
    }

    private HarptosDate harptosDate;
    private long harptosTime;

    public HarptosCalendar(HarptosDate harptosDate) {
        this.harptosDate = harptosDate;
        this.harptosTime = harptosDate.getHarptosTime();
    }

    public int getCurrentYear() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        int startYear = 3000; // can make this configurable
        int yearCount = days / 365;
        return startYear + yearCount;
    }

    public String getCurrentMonth() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        if (days > 365) {
            days = days - (365 * (days / 365));
        }
        return monthMap.floorEntry(days).getValue();
    }

    public int getCurrentDay() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        int dayOfMonth;
        if (days > 365) {
            days = days - (365 * (days / 365));
        }
        if (days > 30) {
            dayOfMonth = days / 30;
        } else {
            dayOfMonth = days;
        }
        return dayOfMonth;
    }

}
