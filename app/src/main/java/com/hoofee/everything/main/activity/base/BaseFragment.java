package com.hoofee.everything.main.activity.base;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hoofee.everything.main.app.AppContext;
import com.hoofee.everything.main.utils.ActivityEffectUtil;

/**
 * create by hufei on 2016/8/30
 */
public class BaseFragment extends Fragment {

    protected String TAG_FRAGMENT_NAME = "BaseFragment";//当前Fragment的simpleName

    protected AppContext appContext;
    protected Resources  res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_FRAGMENT_NAME = getClass().getSimpleName();
        appContext = AppContext.getInstance();
        res = getResources();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("Fragment"); // 统计页面LogUtils.generateTag(LogUtils.getCallerStackTraceElement())
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd("Fragment");//MainScreen
    }

    /**
     * startActivityForResult 跳转
     */
    public void startActivityForResult(Class<? extends BaseActivity> activity, int requestCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        ActivityEffectUtil.setActivityEffect(getActivity(), ActivityEffectUtil.ENTER_BOTTOM_TO_TOP);
    }
}
