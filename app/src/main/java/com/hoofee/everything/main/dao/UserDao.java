package com.hoofee.everything.main.dao;

import com.hoofee.everything.main.api.UserApi;
import com.hoofee.everything.main.dao.config.BaseDao;
import com.hoofee.everything.main.dao.config.callback.RetrofitCallback;
import com.hoofee.everything.main.model.TestModel;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by hufei on 2016/8/31.
 * 用户数据层
 */
public class UserDao extends BaseDao<UserApi> {

    public UserDao(Retrofit retrofit) {
        super(retrofit);
    }

    public Call<TestModel> getAccessToken(RetrofitCallback callbackEvent) {
        Call<TestModel> call = apiService.getAccessToken();
        call.enqueue(callbackEvent);
        return call;
    }
}
