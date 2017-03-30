package com.hoofee.everything.main.api;

import com.hoofee.everything.main.model.TestModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hufei on 2016/9/1.
 * 关于用户的接口
 */
public interface UserApi{

    @GET("persons/getAccessToken")
    Call<TestModel> getAccessToken();
}
