package com.hoofee.everything.main.alertwindow;

import android.content.Context;
import android.widget.PopupWindow;

/**
 * Created by hufei on 2016/8/30.
 * PopupWindow的基类，所有的PopupWindow都要继承这个，并实现启动方法show()
 */
public abstract class BasePopupWindow extends PopupWindow {

    protected Context mContext;

    public BasePopupWindow(Context _mContext) {
        mContext = _mContext;
    }

    /**
     * 一种统一的展示方式
     */
    public abstract void show();
}
