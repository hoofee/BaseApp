package com.hoofee.everything.main.app.filter;

import android.content.Context;
import android.content.Intent;

/**
 * Created by hufei on 2016/8/30.
 * 跳转拦截器
 */
public interface IFilterSkip {
    /**
     * @param mContext
     * @param className 跳转的ActivityClassName
     * @param intent    意图
     * @return 检查是否通过
     */
    boolean doFilter(Context mContext, String className, Intent intent);
}
