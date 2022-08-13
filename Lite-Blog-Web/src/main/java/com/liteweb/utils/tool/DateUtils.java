package com.liteweb.utils.tool;

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

    public static String formatNow(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("%s %s",DEFAULT_DATE_PATTERN,DEFAULT_TIME_PATTERN_ROUGH));
        return now.format(formatter);
    }

    public static String formatNow(String pattern){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now.format(formatter);
    }

}
