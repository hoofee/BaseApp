package com.hoofee.everything.main.dao;

import com.hoofee.everything.main.api.ArticleApi;
import com.hoofee.everything.main.dao.config.BaseDao;
import com.hoofee.everything.main.dao.config.callback.RetrofitCallback;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by hufei on 2016/9/12.
 */
public class ArticleDao extends BaseDao<ArticleApi> {

    public ArticleDao(Retrofit retrofit) {
        super(retrofit);
    }

    public void getArticleSessionPageList(RetrofitCallback callbackEvent) {
        Call call = apiService.getArticleSessionPageList(3);
        appRequestManager.addCall(ArticleApi.GET_ARTICLE_SESSION_PAGE_LIST, call);
        call.enqueue(callbackEvent);
    }

    public void getAccessToken(RetrofitCallback callbackEvent) {
        Call call = apiService.getAccessToken();
        appRequestManager.addCall(ArticleApi.GET_ARTICLE_SESSION_PAGE_LIST, call);
        call.enqueue(callbackEvent);
    }
}
