package com.hoofee.everything.main.listener.base;

/**
 * Created by hufei on 2016/8/15.
 * Description: 与业务系统对接回调泛型接口
 */
public interface IViewModelCallback<T> {
    /***
     * 成功回调
     */
    void successCallback(T success);

    /**
     * 失败回调
     */
    void failCallback(T fail);
}
