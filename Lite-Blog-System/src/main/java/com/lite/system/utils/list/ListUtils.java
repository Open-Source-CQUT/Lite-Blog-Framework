package com.lite.system.utils.list;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/26 22:56
 */
@Slf4j
public class ListUtils {

    public static <T,S> List<S> castList(List<T> list, Class<S> sClass){
        return list.stream().map(sClass::cast).collect(Collectors.toList());
    }

    public static  <T> void logList(List<T> list) {
        Optional.ofNullable(list)
                .ifPresent(
                        notNullList -> notNullList.forEach(
                                ele -> log.info(ele.toString())));
    }
}
