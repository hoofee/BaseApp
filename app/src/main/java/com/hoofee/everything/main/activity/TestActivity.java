package com.hoofee.everything.main.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.hoofee.everything.R;
import com.hoofee.everything.TestBinding;
import com.hoofee.everything.main.activity.base.BaseActivity;
import com.hoofee.everything.main.listener.base.IViewModelCallback;
import com.hoofee.everything.main.viewmodel.TestViewModel;

/**
 * Created by hufei on 2016/7/28.
 * 测试参照Activity
 */
public class TestActivity extends BaseActivity<TestViewModel> implements IViewModelCallback<String> {

    private TestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setViewModel(viewModel);
    }


    public synchronized void getArticleListClick(View view) {
        viewModel.getArticlePageList();
    }

    @Override
    protected void initViewModel() {
        viewModel = new TestViewModel(mContext);
    }

    @Override
    protected void initEvents() {
        viewModel.setViewModelListener(this);
    }

    @Override
    public void successCallback(String success) {

    }

    @Override
    public void failCallback(String fail) {

    }
}
