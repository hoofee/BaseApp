package com.hoofee.everything.main.dao.config.callback;

import com.hoofee.everything.main.model.base.BaseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by hufei on 2016/9/12.
 */
public abstract class RetrofitListCallback<T extends BaseModel> extends RetrofitCallback<List<T>> {

    public void onResponse(Call<List<T>> call, Response<List<T>> response) {
        super.onResponse(call, response);
    }

    public void onFailure(Call<List<T>> call, Throwable t) {
        super.onFailure(call, t);
    }
}
