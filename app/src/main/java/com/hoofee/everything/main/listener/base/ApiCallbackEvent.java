package com.hoofee.everything.main.listener.base;

import retrofit2.Response;

/**
 * Created by hufei on 2016/9/1.
 * Api接口返回结果回调
 */
public interface ApiCallbackEvent<T> {

    void onSuccessEvent(Response<T> response);

    void onFailEvent(Response<T> response);
}
