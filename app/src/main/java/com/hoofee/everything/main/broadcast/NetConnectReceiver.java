package com.hoofee.everything.main.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hoofee.everything.main.utils.LogUtil;


/**
 * Created by Tommy on 2016/8/27.
 * 网络变化的广播监听
 */
public class NetConnectReceiver extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            checkNetWorkStatus();
        }
    }

    private void checkNetWorkStatus() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager == null) return;

        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo == null) {
            LogUtil.e("没有网络");
        } else {
            if (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {// 判断3G网  手机网络
                LogUtil.e("手机网络");
            }
            if (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {// 判断WIFI网
                LogUtil.e("wifi网络");
            }
        }
    }

}
