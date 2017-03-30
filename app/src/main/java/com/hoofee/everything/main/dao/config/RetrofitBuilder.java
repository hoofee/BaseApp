package com.hoofee.everything.main.dao.config;

import com.hoofee.everything.main.app.AppConfig;
import com.hoofee.everything.main.app.AppContext;
import com.hoofee.everything.main.dao.config.interceptor.CacheInterceptor;
import com.hoofee.everything.main.dao.config.interceptor.HeaderInterceptor;
import com.hoofee.everything.main.dao.config.interceptor.LogInterceptor;
import com.hoofee.everything.main.utils.ObjectUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by hufei on 2016/9/1.
 * Retrofit 构建器
 */
public class RetrofitBuilder {

    private List<Converter.Factory> converterList   = new ArrayList<>();
    private List<Interceptor>       interceptorList = new ArrayList<>();

    private static Cache cache;

    static {
        File httpCacheDirectory = new File(AppContext.getInstance().getCacheDir(), "api");
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        cache = new Cache(httpCacheDirectory, cacheSize);
    }

    public RetrofitBuilder addConverterFactory(Converter.Factory converter) {
        converterList.add(converter);
        return this;
    }

    public RetrofitBuilder addInterceptor(Interceptor interceptor) {
        interceptorList.add(interceptor);
        return this;
    }

    public Retrofit builde(Boolean isCache) {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)        //默认超时5秒
                .addInterceptor(new HeaderInterceptor())     //默认的header
                .addInterceptor(new LogInterceptor());      //默认的接口log打印

        //https
        SSLHandler.onHttps(okHttpBuilder);

        if (isCache != null && isCache) {
            okHttpBuilder.addInterceptor(new CacheInterceptor())
                    .cache(cache);
        }
        if (ObjectUtil.nonEmpty(interceptorList)) {
            for (Interceptor intercept : interceptorList) {
                okHttpBuilder.addNetworkInterceptor(intercept);
            }
        }


        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl(AppConfig.HOST_URL_API);       //BaseUrl
        if (ObjectUtil.nonEmpty(converterList)) {
            for (Converter.Factory convert : converterList) {
                retrofitBuilder.addConverterFactory(convert);
            }
        } else {
            retrofitBuilder.addConverterFactory(FastJsonConverterFactory.create());     //默认的转换器
        }
        return retrofitBuilder.build();
    }

    public Retrofit builde() {
        return builde(null);
    }
}
