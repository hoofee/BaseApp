package com.hoofee.everything.main.utils;

import java.util.Collection;

/**
 * Created by hufei on 2016/8/30.
 * 判断集合及其他对象是否为空
 */
public class ObjectUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean nonEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }
}
