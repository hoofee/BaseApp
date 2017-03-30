package com.hoofee.everything.main.listener.base;

/**
 * Created by hufei on 2016/8/31.
 * 分页数据回调
 */
public interface IPageListCallback<T> {
    /***
     * 第一页回调
     */
    void onPageListFirstSuccess(T first);

    /**
     * 最后一页回调
     */
    void onPageListLastSuccess(T last);

    /**
     * 中间页回调
     */
    void onPageListMiddleSuccess(T middle);

    /**
     * 获取数据失败回调
     */
    void onPageListFail(T fail);

}
