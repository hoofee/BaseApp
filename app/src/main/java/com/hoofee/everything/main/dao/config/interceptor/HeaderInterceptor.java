package com.hoofee.everything.main.dao.config.interceptor;

import com.hoofee.everything.main.app.AppData;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用户在OkHttp请求中添加头部
 * Created by Kai Ding on 2016/7/20.
 */
public class HeaderInterceptor implements Interceptor {

    private static final String HEADER_ACCESS_TOKEN = "accesstoken";
    private static final String HEADER_USER_AGENT = "user-Agent";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request headerRequest = request.newBuilder()
                //.header(HEADER_ACCESS_TOKEN, AppData.ACCESS_TOKEN)
                .header(HEADER_USER_AGENT, AppData.UA_INFO)
                .build();

        return chain.proceed(headerRequest);
    }
}
