package com.hoofee.everything.main.dao.config.callback;

import com.hoofee.everything.main.app.AppRequestManager;
import com.hoofee.everything.main.listener.base.ApiCallbackEvent;
import com.hoofee.everything.main.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hufei on 2016/8/31.
 * 默认的回调
 */
public abstract class RetrofitCallback<T> implements Callback<T>, ApiCallbackEvent<T> {

    private int retryTimes = 1;//重试次数

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        LogUtil.i("BaseCallBack_" + response.body());
        retryTimes = 0;
        if (response.isSuccessful()) {     //200||300
            AppRequestManager.getInstance().removeCall(call);
            onSuccessEvent(response);
        } else {                            //400||500
            onFailEvent(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        try {
            if (!call.isCanceled() && retryTimes > 0) {
                retryTimes--;
                call.enqueue(RetrofitCallback.this);
            } else {
                LogUtil.e(String.valueOf(call.request().url().url()), t);
            }
        } catch (Exception e) {
            LogUtil.e(String.valueOf(call.request().url().url()), t);
        }
    }
}
