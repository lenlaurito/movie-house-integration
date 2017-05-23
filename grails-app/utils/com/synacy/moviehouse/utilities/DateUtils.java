package com.synacy.moviehouse.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatDateAsString(Date date) {
        return formatDateAsString(date, DEFAULT_PATTERN);
    }

    public static String formatDateAsString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date formatStringAsDateTime(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        Date dateToParse;
        try {
            dateToParse = dateFormat.parse(dateStr);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Cannot format the string into date. Kindly use this format 'yyyy-MM-dd HH:mm:ss' instead");
        }
        return dateToParse;
    }
    
    public static Date formatStringAsDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToParse;
        try {
            dateToParse = dateFormat.parse(dateStr);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Cannot format the string into date. Kindly use this format 'yyyy-MM-dd' instead");
        }
        return dateToParse;
    }

}
