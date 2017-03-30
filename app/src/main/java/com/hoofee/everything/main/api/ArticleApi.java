package com.hoofee.everything.main.api;

import com.hoofee.everything.main.model.ArticleSectionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hufei on 2016/9/12.
 */
public interface ArticleApi {

    String GET_ARTICLE_SESSION_PAGE_LIST = "getArticleSessionPageList";

    @GET("officeNews/news")
    Call<List<ArticleSectionModel>> getArticleSessionPageList(@Query("pageSize") int pageSize);

    @GET("persons/getAccessToken")
    Call<String> getAccessToken();
}
