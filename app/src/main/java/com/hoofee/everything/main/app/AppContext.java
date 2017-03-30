package com.hoofee.everything.main.app;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;

//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG

/**
 * Created by hufei on 2016/7/28.
 */
public class AppContext extends Application {

    private static AppContext appContext;
    public static  Handler    mainHandler;

    private AppManager appManager;
    private Object     transmitData;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        mainHandler = new Handler();
    }

    public static AppContext getInstance() {
        return appContext;
    }

    public Handler getMainHandler() {
        return mainHandler;
    }

    /**
     * 设置传递的数据
     */
    public void setTransmitData(Object data) {
        this.transmitData = data;
    }

    /**
     * 获取传递的数据
     */
    public Object getTransmitData() {
        return transmitData;
    }

    /**
     * 跳转activity
     *
     * @param :       或 cn类名
     */
    /*public void startActivity(Context context, String str, Bundle bundle) {
        if (context == null) {
            Activity activity = AppManager.getInstance().currentActivity();
            context = activity == null ? this : activity;
        }
        if (StringUtils.isEmpty(str)) {
            ToastUtils.showToast(context, R.string.function_not_open, Toast.LENGTH_SHORT);
            return;
        }

        Intent intent = new Intent();
        UserDao userDao = new UserDao(context);
        List<String> needLoginArr = Arrays.asList(getResources().getStringArray(R.array.array_need_login_mode));
        List<String> needEncryption = Arrays.asList(getResources().getStringArray(R.array.array_need_encryption_mode));

        // 判断需要登陆权限
        if (str.equals(RegLoginActivity.class.getName()) || (needLoginArr.contains(str) && !isLogin(context))) {
            //判断是否有微信第三方登录
            WeixinLoginModel weixinLoginModel = getWeixinLoginModel(context);
            if (weixinLoginModel != null) {
                intent.setClass(context, BindingCellPhoneActivity.class);
            } else {
                intent.setClass(context, RegLoginActivity.class);
            }
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.push_up_in, R.anim.push_no_move);
            return;
        }

        //判断需要保护密码
        if (needEncryption.contains(str) && isLogin(context) && userDao.getSafeKeyState()) {
            intent.setClass(context, SelfInfoLockActivity.class);
            intent.putExtra(TargetActivity, str);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return;
        }
        startActivityForRoot(context, str, bundle);
    }

    *//**
     * 无限制判断强制跳转
     *
     * @param context
     * @param str
     * @param bundle
     *//*
    public void startActivityForRoot(Context context, String str, Bundle bundle) {
        Intent intent = new Intent();
        if (str.contains("http://")) {
            if (!isNetworkConnected()) {
                this.displayNotConnectedNetwork();
                return;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("url", str);
            intent.putExtras(bundle);
            intent.setClass(context, WebViewActivity.class);
        } else {
            try {
                intent.setClass(context, Class.forName(str));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                ToastUtils.showToast(context, "找不到对应的界面", Toast.LENGTH_SHORT);
                return;
            }
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (str.equals(RegLoginActivity.class.getName())) {
            ActivityEffectUtil.setActivityEffect(context, ActivityEffectUtil.ENTER_BOTTOM_TO_TOP);
        } else {
            ActivityEffectUtil.setActivityEffect(context, ActivityEffectUtil.ENTER_RIGHT_TO_LEFT);
        }
    }

    *//**
     * 跳转activity
     *//*
    public void startActivity(Context context, Class<?> activityClass, Bundle bundle) {
        this.startActivity(context, activityClass.getName(), bundle);
    }*/

    /**
     * 重启APP
     */
    public void restartApplication() {
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
