package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;

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
        initYear();
        initMonth();
        initDay();
        initHour();
        initMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private void initHour() {
        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
        } else {
            try {
                hour = getTimeComponentFromStringToInt(11,13);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Hour string is less than 2 characters");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Hour is not an integer");
            }
            if (hour < 0 || hour > 23)
                throw new IllegalArgumentException("Hour cannot be less than 0 or more than 23");
        }
    }

    private void initDay() {
        try {
            date = getTimeComponentFromStringToInt(8,10);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Date string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Date is not an integer");
        }
        if (date < 1 || date > 31)
            throw new IllegalArgumentException("Date cannot be less than 1 or more than 31");
    }

    private void initYear() {
        try {
            year = getTimeComponentFromStringToInt(0,4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Year string is less than 4 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Year is not an integer");
        }
        if (year < 2000 || year > 2012)
            throw new IllegalArgumentException("Year cannot be less than 2000 or more than 2012");
    }

    private void initMonth() {
        try {
            month = getTimeComponentFromStringToInt(5,7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Month string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Month is not an integer");
        }
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month cannot be less than 1 or more than 12");
    }

    private void initMinute() {
        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            minute = 0;
        } else {
            exceptionHandler(11,12,"Minute",0,59);
        }
    }

    private int getTimeComponentFromStringToInt(int start,int end){
        String dateComponentString = dateAndTimeString.substring(start, end);
        return Integer.parseInt(dateComponentString);
    }

    private void exceptionHandler(int stat,int end,String timeComponent,int min,int max){
        try {
            minute = getTimeComponentFromStringToInt(14,16);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(timeComponent+" string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(timeComponent+" is not an integer");
        }
        if (minute < min || minute > max)
            throw new IllegalArgumentException(timeComponent+" cannot be less than "+min+" or more than "+max);
    }
}
