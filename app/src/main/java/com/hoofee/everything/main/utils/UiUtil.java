package com.hoofee.everything.main.utils;


import android.os.Build;
import android.os.Looper;

import com.hoofee.everything.main.app.AppContext;


public class UiUtil {
    /**
     * @param runnable
     * @description 把Runnable 方法提交到主线程运行
     */
    public static void runOnUiThread(Runnable runnable) {
        // 在主线程运行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Looper.getMainLooper().isCurrentThread()) {
            runnable.run();
        } else {
            //获取handler
            AppContext.getInstance().getMainHandler().post(runnable);
        }
    }


    public static void runOnUiThreadDelayed(Runnable runnable, long delayTimeMillion) {
        AppContext.getInstance().getMainHandler().postDelayed(runnable, delayTimeMillion);
    }
}
