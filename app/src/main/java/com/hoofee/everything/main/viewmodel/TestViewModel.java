package com.hoofee.everything.main.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.hoofee.everything.main.app.AppConfig;
import com.hoofee.everything.main.app.AppRequestManager;
import com.hoofee.everything.main.dao.ArticleDao;
import com.hoofee.everything.main.dao.config.SSLHandler;
import com.hoofee.everything.main.dao.config.callback.RetrofitListCallback;
import com.hoofee.everything.main.model.ArticleSectionModel;
import com.hoofee.everything.main.utils.LogUtil;
import com.hoofee.everything.main.viewmodel.base.BaseViewModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by hufei on 2016/8/31.
 * 测试参照ViewModel
 */
public class TestViewModel extends BaseViewModel {

    public static final String FLAG = "flag";

    public ObservableField<String> obsTest = new ObservableField<>();

    private ArticleDao articleDao;

    public TestViewModel(Context _context) {
        super(_context);
        initField();

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        SSLHandler.onHttps(okHttpBuilder);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpBuilder.build())
                .baseUrl(AppConfig.HOST_URL_API);

        articleDao = new ArticleDao(builder.build());
    }

    @Override
    protected void initField() {
        obsTest.set("哈哈哈");
    }

    public void getArticlePageList() {

        articleDao.getArticleSessionPageList(new RetrofitListCallback<ArticleSectionModel>() {
            @Override
            public void onSuccessEvent(Response<List<ArticleSectionModel>> response) {
                LogUtil.i("api_count_" + AppRequestManager.getInstance().size());

                List<ArticleSectionModel> articleSectionModelList = response.body();
                LogUtil.i("getArticlePageList_" + articleSectionModelList.toString());
            }

            @Override
            public void onFailEvent(Response<List<ArticleSectionModel>> response) {

            }
        });

        LogUtil.i("api_count_" + AppRequestManager.getInstance().size());
    }
}
