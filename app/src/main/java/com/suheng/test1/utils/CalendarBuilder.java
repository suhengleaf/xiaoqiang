package com.suheng.test1.utils;

import java.util.Calendar;

public class CalendarBuilder {
    static public Calendar get(int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, minute, second);
        return calendar;
    }
}
