package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year = initYear();
        int month = initMonth();
        int date = initDay();
        int hour = initHour();
        int minute = initMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int initHour() {
        int hour;
        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
        } else {
            hour = getTimeComponent(11,13,"Hour",0,23,2);
        }
        return hour;
    }

    private int initDay() {
        return getTimeComponent(8,10,"Date",1,31,2);
    }

    private int initYear() {
        return getTimeComponent(0,4,"Year",2000,2012,4);
    }

    private int initMonth() {
        return getTimeComponent(5,7,"Month",1,12,2);
    }

    private int initMinute() {
        int minute;
        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            minute = 0;
        } else {
            minute = getTimeComponent(14,16,"Minute",0,59,2);
        }
        return minute;
    }

    private int getTimeComponentFromStringToInt(int start,int end){
        String dateComponentString = dateAndTimeString.substring(start, end);
        return Integer.parseInt(dateComponentString);
    }

    private int getTimeComponent(int start,int end,String timeComponent,int min,int max,int length){
        int time = 0;
        try {
            time = getTimeComponentFromStringToInt(start,end);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(timeComponent+" string is less than "+length+" characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(timeComponent+" is not an integer");
        }
        if (time < min || time > max)
            throw new IllegalArgumentException(timeComponent+" cannot be less than "+min+" or more than "+max);
        return time;
    }
}
