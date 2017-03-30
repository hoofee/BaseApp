package com.hoofee.everything.main.dao.config.interceptor;

import com.hoofee.everything.main.app.AppContext;
import com.hoofee.everything.main.utils.DeviceUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hufei on 2016/8/31.
 * 请求数据加缓存
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
        cacheBuilder.maxStale(5, TimeUnit.DAYS);//这个是控制缓存的过时时间
        CacheControl cacheControl = cacheBuilder.build();

        if (!DeviceUtils.isOnline(AppContext.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .tag(request.url().encodedPassword())
                    .build();
        }

        Response response = chain.proceed(request);

        if (DeviceUtils.isOnline(AppContext.getInstance())) {
            int maxAge = 0;// read from cache
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 5;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
