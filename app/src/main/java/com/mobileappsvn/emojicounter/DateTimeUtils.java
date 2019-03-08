package com.mobileappsvn.emojicounter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {
    /**
     * Get time milliseconds from String Time input.
     *
     * @param timeString the first time
     * @param outputFormat the time string output format
     * @param timeZone the TimeZone input
     * @param locale the Locale input
     * @return milliseconds
     */
    public static long getLongTime(String timeString, String outputFormat, TimeZone timeZone, Locale locale) {
        try {
            // create a date object for testing
            DateFormat dateFormat = new SimpleDateFormat(outputFormat, locale);
            dateFormat.setTimeZone(timeZone);

            Calendar calOfTime = Calendar.getInstance();
            calOfTime.setTime(dateFormat.parse(timeString));
            //System.out.println("getTimeInMillis=" + calOfTime.getTimeInMillis());
            return calOfTime.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long convertStringToMilliseconds(String dateTimeString, String dateFormatString, String timeZoneId) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatString);
            sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));//GMT, UTC
            //String inputString = "336:00:00"; //--> 1209600000 milliseconds --> 1209600 seconds

            Date date = sdf.parse("1970-01-01 00:00:00" + dateTimeString);
            long milliseconds = date.getTime();
            System.out.println("To milliseconds=" + milliseconds);

            return milliseconds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String convertUnixMillisecondsToString(long unixMilliseconds, String outFormat, TimeZone timeZone, Locale locale) {
        if (unixMilliseconds < 1) return "";
        String result = "";
        try {
            Date date = new Date(unixMilliseconds);
            SimpleDateFormat sdf = new SimpleDateFormat(outFormat, locale);
            sdf.setTimeZone(timeZone);
            result = sdf.format(date);
            System.out.println("Time source=" + result);
            //-----------------------------
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
