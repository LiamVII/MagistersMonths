package io.github.liamvi.magistersmonths.calendar;

import org.bukkit.ChatColor;

import java.time.LocalTime;
import java.time.Month;

public class HarptosDateFormat {

    private final LocalTime TIME;
    private final String DAY;
    private final String MONTH;
    private final String YEAR;

    public HarptosDateFormat(int day, String month, int year, LocalTime time) {
        this.DAY = Integer.toString(day);
        this.MONTH = month;
        this.YEAR = Integer.toString(year);
        this.TIME = time;
    }

    public HarptosDateFormat(HarptosCalendar harptosCalendar) {
        this(harptosCalendar.getDayOfMonth(), harptosCalendar.getMonthString(), harptosCalendar.getYear(), harptosCalendar.getCalendarTime());
    }



    public String getDayOfMonth() {
        return DAY;
    }

    public String getMonth() {
        return MONTH;
    }

    public String getYear() {
        return YEAR;
    }

    public String getTime() {
        return TIME.toString();
    }

    public String getFormattedDate() {
        return ChatColor.GOLD + DAY + " " + MONTH + " " + YEAR + ": " + TIME.toString();

    }

}
