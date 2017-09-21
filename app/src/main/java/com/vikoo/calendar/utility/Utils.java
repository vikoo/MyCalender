package com.vikoo.calendar.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vikoo on 16/09/17.
 */
public class Utils {

    public static long Day2MS(int day, int month, int year, int hour, int minute, int seconds) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, hour, minute, seconds);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();
    }

    public static int getCurrentDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);
    }

    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static String getTimeInAMPM(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return formatter.format(calendar.getTime());
    }

    public static String getHours(long ms) {
        int seconds = (int) (ms / 1000);
        int h = seconds/ 3600;
        int m = (seconds % 3600) / 60;
        String sh = (h > 0 ? String.valueOf(h) + " " + "h" : "");
        String sm = (m > 0 ? String.valueOf(m) + " " + "m" : "");
        return sh + (h > 0 ? " " : "") + sm;
    }

    public static long getMSForMin(int minutes) {
        return minutes * 60 * 1000;
    }

    public static String getCalenderMonth(Date date){
        SimpleDateFormat f = new SimpleDateFormat("MMMM-yyyy");
        return f.format(date);
    }

    public static long getDayMS(long ms) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ms);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        return Day2MS(day, month, year, 0, 0, 0);
    }

    public static String getDisplayDayMS(long ms){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        String data = formatter.format(calendar.getTime());
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DATE) == calendar.get(Calendar.DATE) ) {
            return "Today - " + data;
        } else if (now.get(Calendar.DATE) - calendar.get(Calendar.DATE) == 1  ){
            return "Yesterday - " +  data;
        } else if (calendar.get(Calendar.DATE) - now.get(Calendar.DATE) == 1  ){
            return "Tomorrow - " + data;
        } else {
            return data;
        }
    }

    public static long getStartTime(Date date){
        long ms = date.getTime();
        return getDayMS(ms);
    }

    public static long getEndTime(Date date, int noOfDays){
        long ms = date.getTime() + (noOfDays * 24 * 60 * 60 * 1000);
        return getDayMS(ms);
    }

    public static long getEndTime(long date){
        long ms = date + (24 * 60 * 59 * 1000);
        return getDayMS(ms);
    }

    public static long getPlusOneDay(long date){
        long ms = date + (24 * 60 * 60 * 1000);
        return getDayMS(ms);
    }

    public static boolean isToday(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DATE) == calendar.get(Calendar.DATE);
    }

    public static boolean isTimeInWeatherRange(long start, long end, long time){
        return (time <= end && time >= start);
    }

    public static long getTimeFromInput(String date, String time){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date parsedDate = formatter.parse(date + " " + time);
            return parsedDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String formatInTwoDigit(int num){
        return String.format("%02d", num);
    }

}
