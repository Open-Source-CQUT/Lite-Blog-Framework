package com.lite.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String DEFAULT_TIME_PATTERN_DETAILS = "HH:mm:ss:SSS";
    public static final String DEFAULT_TIME_PATTERN_ROUGH = "HH:mm:ss";

    public static final Long SECONDS = 1000L;

    public static final Long MINUTES = SECONDS * 60;

    public static final Long HOURS = MINUTES * 60;

    public static final Long DAY = HOURS * 24;

    public static DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(String.format("%s %s",DEFAULT_DATE_PATTERN,DEFAULT_TIME_PATTERN_ROUGH));

    public static String formatNow(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DEFAULT_FORMATTER);
    }

    public static String formatDefault(LocalDateTime dateTime){
        return dateTime.format(DEFAULT_FORMATTER);
    }

    public static String formatNow(String pattern){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now.format(formatter);
    }

    public static Boolean isBefore(String dateA,String dateB){

        LocalDateTime localDateTimeA = LocalDateTime.parse(dateA,DEFAULT_FORMATTER);

        LocalDateTime localDateTimeB = LocalDateTime.parse(dateB,DEFAULT_FORMATTER);

        return localDateTimeA.isBefore(localDateTimeB);
    }

    public static Boolean isBefore(String dateA,String dateB,DateTimeFormatter dft){

        LocalDateTime localDateTimeA = LocalDateTime.parse(dateA,dft);

        LocalDateTime localDateTimeB = LocalDateTime.parse(dateB,dft);

        return localDateTimeA.isBefore(localDateTimeB);
    }
}
