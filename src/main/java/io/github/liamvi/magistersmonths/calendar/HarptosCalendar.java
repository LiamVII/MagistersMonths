package io.github.liamvi.magistersmonths.calendar;

import io.github.liamvi.magistersmonths.MagistersMonths;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class HarptosCalendar {

    private MagistersMonths plugin;
    private static final TreeMap<Integer, String> monthMap = new TreeMap<>();

    // Below is mostly a placeholder while I figure out what to do - end goal is 375 days/year.
    // For ease of understanding - day 31 is the first day, meaning the remaining 29 days equal (31 + 29 = 60)
    // So the below does actually work out to 360 days, which is the current minimum standard.

    static {
        monthMap.put(0, "Hammer");
        monthMap.put(31, "Alturiak");
        monthMap.put(61, "Ches");
        monthMap.put(91, "Tarsakh");
        monthMap.put(121, "Mirtul");
        monthMap.put(151, "Kythorn");
        monthMap.put(181, "Flamerule");
        monthMap.put(211, "Eleasis");
        monthMap.put(241, "Eleint");
        monthMap.put(271, "Marpenoth");
        monthMap.put(301, "Uktar");
        monthMap.put(331, "Nightal");
    }

    /*
    The following accounts for the extra 15 (5) days in the calendar, but classifies them as months.
    It works, but better way of doing it?
    Will also need to redo "Day of the Month" calculation and basically everything below
    Accounting for 375 day years
    Should be fine math wise just need to sketch it out
        monthMap.put(0, "Hammer");
        monthMap.put(31, "Midwinter");
        monthMap.put(34, "Alturiak");
        monthMap.put(64, "Ches");
        monthMap.put(94, "Tarsakh");
        monthMap.put(124, "Greengrass");
        monthMap.put(127, "Mirtul");
        monthMap.put(157, "Kythorn");
        monthMap.put(187, "Flamerule");
        monthMap.put(217, "Midsummer");
        monthMap.put(220, "Eleasis");
        monthMap.put(250, "Eleint");
        monthMap.put(280, "Highharvestide");
        monthMap.put(283, "Marpenoth");
        monthMap.put(313, "Uktar");
        monthMap.put(343, "Moonfest");
        monthMap.put(346, "Nightal");
     */

    private HarptosDate harptosDate;
    private long harptosTime;

    public HarptosCalendar(HarptosDate harptosDate, MagistersMonths plugin) {
        this.harptosDate = harptosDate;
        this.harptosTime = harptosDate.getHarptosTime();
        this.plugin = plugin;
    }

    /*
    Time from epoch begins at day 0, as 0 days have passed since the epoch.
    Therefore, we add +1 day to our day calculations to begin at day 1 rather than day 0.
    */

    public int getYear() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        int startYear = 3000; // can make this configurable
        int yearCount = days / 365;
        return startYear + yearCount;
    }

    // TODO: Rework
    public String getMonthString() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        days = days % 365;
        return monthMap.floorEntry(days).getValue();
    }

    // As written, this does not work if the days since epoch is less than 365.

    public int getDayOfMonth() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        days = days % 365;
            int dayOfMonth = days % 30;
        return dayOfMonth;
    }

    public LocalTime getCalendarTime() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;

        // Calculate the number of milliseconds into the day we are by subtracting (day count * milliseconds in a day)
        // The result can then be converted into hours, dropping the decimal.

        long millisecondsIntoDay = (harptosTime - (TimeUnit.MILLISECONDS.toDays(harptosTime) * 86400000));

        // Then, we do the same by subtracting the day values - and then we also subtract the hours values.
        // We are then left with the number of milliseconds since the last hour, which gives us the number of minutes.
        // Pretty sure we could just do some division rather than TimeUnit -> multiplication? I prefer relying on Java's math ability.

        long millisecondsIntoDaySubHours = (harptosTime - ((TimeUnit.MILLISECONDS.toDays(harptosTime) * 86400000) +
                (TimeUnit.MILLISECONDS.toHours(millisecondsIntoDay) * 3600000)));

        // Can return a string as well, but probably better to return the LocalTime object so we can use its methods.

        return LocalTime.of((int) TimeUnit.MILLISECONDS.toHours(millisecondsIntoDay),
                (int) TimeUnit.MILLISECONDS.toMinutes(millisecondsIntoDaySubHours));
    }

}
