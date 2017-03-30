package com.hoofee.everything.main.dao.config;

/**
 * Created by hufei on 2016/8/31.
 */
public class AppRetrofit {
/*
    public static Cache cache;

    private static API apiGson;
    private static API apiFastJson;
    private static API apiCacheGson;
    private static API apiCacheFastJson;

    private AppRetrofit() {
    }

    static {
        File httpCacheDirectory = new File(AppContext.getInstance().getCacheDir(), "api");
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        cache = new Cache(httpCacheDirectory, cacheSize);
    }

    public static API getInstanceByGson() {
        if (apiGson == null) {
            synchronized (AppRetrofit.class) {
                if (apiGson == null) {
                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HeaderInterceptor())
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .build();

                    apiGson = new Retrofit.Builder()
                            .client(httpClient)
                            .baseUrl(AppConfig.HOST_URL_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(API.class);
                }
            }
        }
        return apiGson;
    }

    public static API getInstanceByFastJson() {
        if (apiCacheFastJson == null) {
            synchronized (AppRetrofit.class) {
                if (apiCacheFastJson == null) {
                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HeaderInterceptor())
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .build();

                    apiCacheFastJson = new Retrofit.Builder()
                            .client(httpClient)
                            .baseUrl(AppConfig.HOST_URL_API)
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .build().create(API.class);
                }
            }
        }
        return apiCacheFastJson;
    }

    public static API getInstanceCacheByGson() {
        if (apiCacheGson == null) {
            synchronized (AppRetrofit.class) {
                if (apiCacheGson == null) {
                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HeaderInterceptor())
                            .addNetworkInterceptor(new CacheInterceptor())
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .build();

                    apiCacheGson = new Retrofit.Builder()
                            .client(httpClient)
                            .baseUrl(AppConfig.HOST_URL_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(API.class);
                }
            }
        }
        return apiCacheGson;
    }

    public static API getInstanceCacheByFastJson() {
        if (apiCacheFastJson == null) {
            synchronized (AppRetrofit.class) {
                if (apiCacheFastJson == null) {
                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HeaderInterceptor())
                            .addNetworkInterceptor(new CacheInterceptor())
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .build();

                    apiCacheFastJson = new Retrofit.Builder()
                            .client(httpClient)
                            .baseUrl(AppConfig.HOST_URL_API)
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .build().create(API.class);
                }
            }
        }
        return apiCacheFastJson;
    }
    */
}
