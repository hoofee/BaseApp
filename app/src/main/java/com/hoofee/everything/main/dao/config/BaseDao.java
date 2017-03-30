package com.hoofee.everything.main.dao.config;

import com.hoofee.everything.main.app.AppRequestManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Retrofit;

/**
 * Created by hufei on 2016/9/1.
 */
public class BaseDao<T> {

    public    T                 apiService;
    protected Class<T>          apiClass;
    protected AppRequestManager appRequestManager;

    public BaseDao(Retrofit retrofit) {

        appRequestManager = AppRequestManager.getInstance();

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        apiClass = (Class) params[0];

        apiService = retrofit.create(apiClass);
    }
}
