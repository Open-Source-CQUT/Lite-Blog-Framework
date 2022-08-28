package com.lite.system.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SystemStringUtils extends StringUtils {

    public static final String COMMA = ",";

    public static final String OPEN_BRACKET = "(";

    public static final String CLOSE_BRACKET = ")";

    public static String concatWithComma(String[] arr) {
        if (arr.length == 0)
            return SystemStringUtils.EMPTY;

        return concatWithComma(Arrays.stream(arr));
    }

    public static String concatWithComma(List<String> list){
        if (list == null || list.isEmpty())
            return SystemStringUtils.EMPTY;
        return concatWithComma(list.stream());
    }

    public static String concatWithComma(Stream<String> stream) {
        if (Objects.isNull(stream))
            return SystemStringUtils.EMPTY;

        String result = stream.reduce(SystemStringUtils.EMPTY, (res, val) -> res + COMMA + val);

        return SystemStringUtils.isEmpty(result) ? SystemStringUtils.EMPTY : result.substring(1);
    }

    public static String removeBracketContent(String str){
        return StringUtils.isEmpty(str) ? null : str.substring(0,str.indexOf(OPEN_BRACKET));
    }
}
