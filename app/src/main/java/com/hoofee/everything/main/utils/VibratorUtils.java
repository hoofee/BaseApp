/**
 *
 */
package com.hoofee.everything.main.utils;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

/**
 * @项目名: cn-sharing8-blood
 * @包名: cn.sharing8.blood.common
 * @类名: VibratorUtils
 * @创建者: 张文辉
 * @创建时间: 2015-11-6 上午9:29:47
 * @描述: 手机震动工具类
 */
public class VibratorUtils {

    private Vibrator vibrator;

    /**
     * @param activity     调用该方法的Activity实例
     * @param milliseconds 震动的时长，单位是毫秒
     * @描述: 定义时长震动
     */
    public void Vibrate(final Activity activity, long milliseconds) {
        vibrator = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

    /**
     * @param activity 调用该方法的Activity实例
     * @param pattern  自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * @param isRepeat 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     * @描述:自定义样式震动
     */
    public void Vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
        vibrator = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, isRepeat ? 1 : -1);
    }

    /**
     * @描述:关闭资源
     */
    public void Cancel() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}
