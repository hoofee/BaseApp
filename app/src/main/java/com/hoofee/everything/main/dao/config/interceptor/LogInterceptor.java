package com.hoofee.everything.main.dao.config.interceptor;

import com.hoofee.everything.main.utils.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hufei on 2016/9/1.
 * 接口日志的拦截器
 */
public class LogInterceptor implements Interceptor {

    public static final String SEPARATOR = " --- ";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtil.w("method = " + String.valueOf(request.method()) + SEPARATOR
                + "url = " + String.valueOf(request.url().url()) + SEPARATOR
                + "header = " + String.valueOf(request.headers()) + SEPARATOR
                + "jsonBody = " + String.valueOf(request.body()));

        return  chain.proceed(request);
    }
}
