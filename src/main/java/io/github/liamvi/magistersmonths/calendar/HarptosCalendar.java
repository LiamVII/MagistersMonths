package io.github.liamvi.magistersmonths.calendar;

import io.github.liamvi.magistersmonths.MagistersMonths;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class HarptosCalendar {
    private static final TreeMap<Integer, String> monthMap = new TreeMap<>();

    private MagistersMonths plugin;
    // Below is mostly a placeholder while I figure out what to do - end goal is 365 days/year.
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

    // Not sure it matters, but there's a world where a lot of these methods could just be static?
    // They're transforming data from HarptosDate
    // HarptosCalendar doesn't actually need to be constructed? I could run these methods on the HarptosDate object so far.

    private HarptosDate harptosDate;
    private long harptosTime;

    public HarptosCalendar(HarptosDate harptosDate, MagistersMonths plugin) {
        this.harptosDate = harptosDate;
        this.harptosTime = harptosDate.getHarptosTime();
        this.plugin = plugin;
    }

    /*
    A big problem currently is that the time from epoch obviously begins at Day 0.
    This is correct as 0 days have passed, but in the implementation below that would present as "Day 0 of Hammer", for ex.
    The last day of the year would be "Day 30th of Nightal, Year 2"
    I've just added +1 to the days count for now, but that is a bit too janky for my liking.
    Will also mean adding +1 all over the place, honestly. Doable, but is it a good way?
    We also need to provide for the calendar's holidays, which exist outside of any month. For our purposes, though...
    We could just include them in the month for the code and format the result?

    - Consider alternate ways of mapping days to months; the TreeMap might be making it more annoying

    Will clean up the code at the end and assign variables in the constructor where best practice.
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

    // TODO: Get current month as an integer in case we want to describe it in integer form (probably do.)
    // No use case - ignore

    public int getDayOfMonth() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(harptosTime) + 1;
        days = days % 365;
            int dayOfMonth = days % 30;
        return dayOfMonth;
    }

    /*
    Potential problems with the below?

    - First, need to verify the math checks out (which I think it does, just hard to calculate minutes & hours beyond small examples).
    It seemed to work fine on testing with 10,000,000,000 milliseconds (roughly 4 years).

    - It's a lot of math to do? I don't think a computer would have any problem with this at all..?
    - Assuming we run these calculations every 10 seconds.
    - I'm pretty sure a computer runs 10^9 quadrilliion (definitely the real number) more calculations than this every 10 seconds....

    TODO: Will have to update this to consider the +1 shenanigans later on.

     */
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
