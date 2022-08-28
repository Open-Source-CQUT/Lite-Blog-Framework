package com.lite.system.utils.list;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: 用于快速从两个list比较获取结果
 * @date 2022/8/26 19:44
 */
public interface ListComparator<T> {


    List<? extends T> findExtraPart(List<? extends T> refer,List<? extends T> target);

    List<? extends T> findLessPart(List<? extends T> refer,List<? extends T> target);

    List<? extends T> findUpdatePart(List<? extends T> refer,List<? extends T> target);
}
