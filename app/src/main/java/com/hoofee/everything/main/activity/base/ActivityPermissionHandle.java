package com.hoofee.everything.main.activity.base;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.hoofee.everything.main.app.code.CodeWithPermissionRequest;
import com.hoofee.everything.main.utils.PermissionsUtil;
import com.tencent.tauth.Tencent;

import cn.sharing8.blood_platform_widget.qq.QQShareAction;

/**
 * Created by hufei on 2016/7/28.
 */
public class ActivityPermissionHandle extends AppCompatActivity {

    protected Context mContext;
    protected IActivityResult activityResult;
    protected IPermissionsResult permissionsResult;

    private IPermissionCallback permissionCallback;

    /**
     * 单独一个照相的权限
     */
    public String P_Camera = Manifest.permission.CAMERA;
    /**
     * 存储卡的读写权限
     */
    public String P_Strorage = Manifest.permission.READ_EXTERNAL_STORAGE;
    /**
     * 定位权限
     */
    public String[] P_LocationGroup = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
    /**
     * 照相以及打开本地相册数据库的权限
     */
    public String[] P_CameraAndPhoto = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    /**
     * 录音权限
     */
    public String[] P_AudioGroup = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    /**
     * 读取手机联系人的权限
     */
    public String[] P_ContactsGroup = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.GET_ACCOUNTS};
    /**
     * 发送短信的权限
     */
    public String[] P_Sms_Send = new String[]{Manifest.permission.SEND_SMS};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //腾讯客户端的回调
        if (QQShareAction.callback != null) {
            Tencent.onActivityResultData(requestCode, resultCode, data, QQShareAction.callback);
            QQShareAction.callback = null;
        }
        if (activityResult != null) {
            activityResult.OnActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 当权限的提示框弹出后，并且用户设置完权限后的回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsResult != null) {
            permissionsResult.onPermissionsResult(requestCode, permissions, grantResults);
        } else {
            if (verifyPermissions(grantResults)) {
                permissionCallback.hasPermission(requestCode, permissions);
            } else {
                permissionCallback.notPermission(requestCode, permissions);
            }
        }
    }

    /**
     * Android 6.0以上运行时权限请求
     *
     * @param permissionCallback 请求权限回调
     * @param permissions        请求的权限（数组类型），直接从Manifest中读取相应的值，比如Manifest.permission.WRITE_CONTACTS
     */
    public void getPermission(@NonNull IPermissionCallback permissionCallback, Integer permissionRequestCode, String... permissions) {
        this.permissionCallback = permissionCallback;
        if (permissionRequestCode == null) {
            permissionRequestCode = CodeWithPermissionRequest.REQUEST_CODE;
        }
        if (permissions == null || permissions.length == 0) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermissionGranted(permissions)) {//(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)||
                permissionCallback.hasPermission(permissionRequestCode, permissions);
            } else {
                //当还没有这个权限的时候就直接请求
                //requestPermissionAlertDialog(permissionDes, permissionRequestCode, permissions);
                ActivityCompat.requestPermissions(this, permissions, permissionRequestCode);
            }
        } else {
            //返回 0 就代表有权限，1代表没有权限，-1函数出错啦。
            if (checkPermissionGranted(permissions)) {
                permissionCallback.hasPermission(permissionRequestCode, permissions);
            } else {
                ActivityCompat.requestPermissions(this, permissions, permissionRequestCode);
            }
        }
    }

    /**
     * 判断是否有该权限
     *
     * @param permissions
     * @return
     */
    public boolean hastPermission(int[] specialPermissions, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkPermissionGranted(permissions);//(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)||
        } else {
            return checkPermissionGranted(permissions) && PermissionsUtil.hasPermissions(mContext, specialPermissions);//(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)||
        }
    }

    /**
     * 请求一组权限
     *
     * @param permissions
     */
    public void requestPermissions(int permissionRequestCode, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, permissionRequestCode);
    }

    /**
     * 检查一组权限是否通过
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissionGranted(String[] permissions) {
        boolean flag = true;
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 请求一组权限
     *
     * @param permissionDes
     * @param requestCode
     * @param permissions
     */
    public void requestPermissionAlertDialog(String title, String buttonText, String permissionDes, final int requestCode, final String[] permissions) {
        //如果可以弹出提示的话就弹出权限是否允许的提示
        if (shouldShowRequestPermissionRationale(permissions)) {
            //如果用户之前拒绝过此权限，再提示一次准备授权相关权限
            new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(permissionDes)
                    .setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ActivityPermissionHandle.this, permissions, requestCode);
                        }
                    }).show();
        } else {
            //当还没有这个权限的时候就直接请求
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        boolean flag = false;
        for (String p : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, p)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean verifyPermissions(int[] grantResults) {
        // 至少有一个权限通过检查.
        if (grantResults.length < 1) {
            return false;
        }
        // 只有当所有权限都通过才返回true
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置onActivityResult回调
     *
     * @param _activityresult
     */
    public void setActivityResult(IActivityResult _activityresult) {
        activityResult = _activityresult;
    }

    /**
     * 设置IPermissionsResult检查权限回调
     *
     * @param _permissionResult
     */
    public void setOnPermissionResult(IPermissionsResult _permissionResult) {
        this.permissionsResult = _permissionResult;
    }

//----------------------------------------接口-----------------------------------------

    /**
     * 权限的回调接口
     */
    public interface IPermissionCallback {
        void hasPermission(int requestCode, String[] permissions);

        void notPermission(int requestCode, String[] permissions);
    }

    /**
     * 返回页面结果接口
     */
    public interface IActivityResult {
        void OnActivityResult(int requestCode, int resultCode, Intent data);
    }

    /**
     * android 6.0以上检查权限回调
     */
    public interface IPermissionsResult {
        void onPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }
}
