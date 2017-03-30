package com.hoofee.everything.main.model.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.hoofee.everything.main.utils.StringUtils;
import com.hoofee.everything.main.utils.ToastUtils;

public class RegexModel {
    public static String REGEX_CELLPHONE = "1[34578]\\d{9}";
    public static String REGEX_USER_NAME = "[\u4E00-\u9FA5\\w]{2,20}";
    public static String REGEX_USER_ALIAS = ".{2,20}";
    public static String REGEX_PASSWORD = "\\w{6,20}";
    public static String REGEX_VERIFY_CODE = "\\d{4}";
    public static String REGEX_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    public static String REGEX_IDCARD = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[0-9X]{1}$";

    /**
     * 校验值
     */
    public static boolean checkText(String value, String regex) {
        if (value == null) return false;
        value = value.trim();
        if (!StringUtils.isEmpty(value) && value.matches(regex))
            return true;
        return false;
    }

    /**
     * 校验值，带有提示TOAST
     */
    public static boolean checkTextWithToast(@NonNull Context mContext, String value, String regex, String emptyTips, String errorTips) {
        if (!StringUtils.isEmpty(value)) {
            if (regex != null || errorTips != null) {
                if (value.trim().matches(regex)) {
                    return true;
                } else {
                    ToastUtils.showToast(mContext, errorTips, Toast.LENGTH_SHORT);
                }
            } else {
                return true;
            }
        } else {
            ToastUtils.showToast(mContext, emptyTips, Toast.LENGTH_SHORT);
        }
        return false;
    }
}
