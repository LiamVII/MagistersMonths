package io.github.liamvi.magistersmonths.calendar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HarptosDate {

    private int year;
    private int month;
    private int day;

    private int minutes;

    private int seconds;

    public HarptosDate() {

    }

    public String getDate() {
        return "" + year + "" + month + "" + day;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String getMonthName(int monthValue) {
        String monthName = "";
        if (monthValue > 0 && monthValue < 13) {
            switch (monthValue) {
                case 1 -> monthName = "Hammer";
                case 2 -> monthName = "Alturiak";
                case 3 -> monthName = "Ches";
                case 4 -> monthName = "Tarsakh";
                case 5 -> monthName = "Mirtul";
                case 6 -> monthName = "Kythorn";
                case 7 -> monthName = "Flamerule";
                case 8 -> monthName = "Eleasis";
                case 9 -> monthName = "Eleint";
                case 10 -> monthName = "Marpenoth";
                case 11 -> monthName = "Uktar";
                case 12 -> monthName = "Nightal";
            }
            return monthName;
        }
        return null;
    }

    public void setDate(String date) {
        List<Integer> list = Arrays.stream(date.split("-"))
                .map(Integer::parseInt)
                .toList();
        if (list.get(1) > 12 || list.get(1) < 1) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        } else {
            this.month = list.get(1);
        }

        if (list.get(2) > 31 || list.get(1) < 1) {
            throw new IllegalArgumentException("Day must be between 1 and 31.");
        } else {
            this.day = list.get(2);
        }
        this.year = list.get(0);
    }

    public void setMonth(int month) {
        if (month > 12 || month < 1) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        } else {
            this.month = month;
        }
    }

    public void setDay(int day) {
        if (day > 31 || day < 1) {
            throw new IllegalArgumentException("Day must be between 1 and 31.");
        } else {
            this.day = day;
        }
    }

    public void setYear(int year) {
        this.year = year;
    }

}
