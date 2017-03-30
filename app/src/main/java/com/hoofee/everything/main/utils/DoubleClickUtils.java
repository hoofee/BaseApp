package com.hoofee.everything.main.utils;

/**
 * Author: 帅气的勇勇
 * Data: 2016/2/15
 * Email:zyy@sharing8.com
 */
public class DoubleClickUtils {

    /**
     * Author: 帅气的勇勇
     * Description:防止同一个按钮连续点击的bug
     * @return true表示有效点击，false表示两次点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();

        if (lastClickTime == 0 || time - lastClickTime > 1000) {
            lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
