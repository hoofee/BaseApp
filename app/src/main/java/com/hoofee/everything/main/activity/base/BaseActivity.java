package com.hoofee.everything.main.activity.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hoofee.everything.R;
import com.hoofee.everything.main.alertwindow.BasePopupWindow;
import com.hoofee.everything.main.app.AppContext;
import com.hoofee.everything.main.app.AppManager;
import com.hoofee.everything.main.utils.ActivityEffectUtil;
import com.hoofee.everything.main.utils.ConvertUtils;
import com.hoofee.everything.main.utils.ObjectUtil;
import com.hoofee.everything.main.viewmodel.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hufei
 * @created 2016-8-30
 */
public class BaseActivity<T extends BaseViewModel> extends ActivityPermissionHandle {

    protected String TAG_ACTIVITY_NAME = "BaseActivity";//当前activity的simpleName
    public static final String STATE = "STATE";

    protected T              viewModel;
    protected AppContext     appContext;
    protected AppManager     appManager;
    protected Resources      res;
    protected LayoutInflater inflater;

    protected View                top_statusbar;//状态栏View
    private List<Dialog>          dialogList;//当前Activity上承载的所有Dialog
    private List<BasePopupWindow> popupWindowList;//当前Activity上承载的所有PopupWindow

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_ACTIVITY_NAME = this.getClass().getSimpleName();
        appManager = AppManager.getInstance();
        appContext = AppContext.getInstance();
        appManager.addActivity(this);
        res = getResources();
        inflater = LayoutInflater.from(mContext);
        initWindow();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadStatusBar();
    }

    public void loadStatusBar() {
        top_statusbar = findViewById(R.id.top_statusbar);
        if (top_statusbar != null) {
            ViewGroup.LayoutParams param = top_statusbar.getLayoutParams();
            param.height = ConvertUtils.getTop(mContext);
            top_statusbar.setLayoutParams(param);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("Activity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
//        MobclickAgent.onResume(this); // 统计时长

        // 极光推送
//        JPushInterface.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd("Activity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证onPageEnd 在onPause之前调用,因为 onPause 中会保存信息
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        appManager.removeActivityOfStack(this);
    }

    @Override
    public void onBackPressed() {
        if (ObjectUtil.nonEmpty(popupWindowList)) {
            for (BasePopupWindow popupWindow : popupWindowList) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    return;
                }
            }
        }

        if (ObjectUtil.nonEmpty(dialogList)) {
            for (Dialog dialog : dialogList) {
                dialog.onBackPressed();
                if (dialog.isShowing()) {
                    return;
                }
            }
        }
//        if (appManager.getLength() <= 1) {
//            appContext.startActivity(mContext, TestActivity.class, null);
//        } else {
//            super.onBackPressed();
//        }
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected synchronized void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        appManager.finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());//杀掉App中的所有进程
        appContext.restartApplication();
    }

    protected void initState() {
    }

    protected void initView() {
    }

    protected void initViewModel() {
    }

    protected void initEvents() {
    }

    protected void initData() {
    }

    /**
     * @return 主要的ViewModel
     */
    public T getViewModel() {
        return viewModel;
    }

    /**
     * startActivityForResult 跳转
     */
    public void startActivityForResult(Class<? extends BaseActivity> activity, int requestCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        ActivityEffectUtil.setActivityEffect(mContext, ActivityEffectUtil.ENTER_BOTTOM_TO_TOP);
    }

    /**
     * 解决安卓4.4以上不能沉浸式问题
     */
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 添加当前Activity承载的Dialog
     */
    protected BaseActivity addDialog(Dialog dialog) {
        if (dialogList == null) {
            dialogList = new ArrayList<>();
        }
        dialogList.add(dialog);
        return this;
    }

    /**
     * 添加当前Activity承载的PopupWindow
     */
    protected BaseActivity addPopupWindow(BasePopupWindow popupWindow) {
        if (popupWindowList == null) {
            popupWindowList = new ArrayList<>();
        }
        popupWindowList.add(popupWindow);
        return this;
    }

    /**
     * 切换Dialog
     */
    protected synchronized void changeDialog(Dialog showDialog) {
        if (ObjectUtil.nonEmpty(dialogList)) {
            for (Dialog dialog : dialogList) {
                if (dialog != showDialog && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }
        if (showDialog.isShowing()) return;
        showDialog.show();
    }

    /**
     * 切换PopupWindow
     */
    protected synchronized void changePopupWindow(BasePopupWindow showPopupWindow) {
        if (ObjectUtil.nonEmpty(popupWindowList)) {
            for (BasePopupWindow popupWindow : popupWindowList) {
                if (popupWindow != showPopupWindow && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        }
        if (showPopupWindow.isShowing()) return;
        showPopupWindow.show();
    }

/*
 * 下面的方法作用：只要点击的区域不是EditText已经弹出的软键盘就会消失
	@Override  
	public boolean dispatchTouchEvent(MotionEvent ev) {  
	    if (ev.getAction() == MotionEvent.ACTION_DOWN) {  
	        View v = getCurrentFocus();  
	        if (isShouldHideInput(v, ev)) {  
	  
	            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
	            if (imm != null) {  
	                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  
	            }  
	        }  
	        return super.dispatchTouchEvent(ev);  
	    }  
	    // 必不可少，否则所有的组件都不会有TouchEvent了   
	    if (getWindow().superDispatchTouchEvent(ev)) {  
	        return true;  
	    }  
	    return onTouchEvent(ev);  
	} 
	public  boolean isShouldHideInput(View v, MotionEvent event) {  
	    if (v != null && (v instanceof EditText)) {  
	        int[] leftTop = { 0, 0 };  
	        //获取输入框当前的location位置   
	        v.getLocationInWindow(leftTop);  
	        int left = leftTop[0];  
	        int top = leftTop[1];  
	        int bottom = top + v.getHeight();  
	        int right = left + v.getWidth();  
	        if (event.getX() > left && event.getX() < right  
	                && event.getY() > top && event.getY() < bottom) {  
	            // 点击的是输入框区域，保留点击EditText的事件   
	            return false;  
	        } else {  
	            return true;  
	        }  
	    }  
	    return false;  
	} */
}
