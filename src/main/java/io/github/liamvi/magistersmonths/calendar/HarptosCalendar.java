package io.github.liamvi.magistersmonths.calendar;

import java.util.Calendar;

public class HarptosCalendar extends Calendar {

    //HarptosCalendar is to HarptosDate what Calendar is to Date - a means of converting between a specific instance
    //in time to the appropriate calendar field to be displayed.
    //As Harptos is not a Locale and it seems beyond the scope of this project to create an entirely new Locale,
    //We will handle the formatting of day/month/year elsewhere.

    public HarptosCalendar() {
        super();
    }
    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int field, int amount) {

    }

    @Override
    public void roll(int field, boolean up) {

    }

    @Override
    public int getMinimum(int field) {
        return 0;
    }

    @Override
    public int getMaximum(int field) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int field) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int field) {
        return 0;
    }
}
