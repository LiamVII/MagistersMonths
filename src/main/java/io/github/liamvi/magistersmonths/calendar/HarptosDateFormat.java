package io.github.liamvi.magistersmonths.calendar;

import java.time.LocalTime;

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

    public String getFullDate() {
        return DAY + MONTH + YEAR + TIME.toString();
    }

}
