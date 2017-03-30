package com.hoofee.everything.main.viewmodel.base;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.hoofee.everything.R;
import com.hoofee.everything.main.model.base.RegexModel;
import com.hoofee.everything.main.utils.LogUtil;
import com.hoofee.everything.main.utils.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by hufei on 2016/8/22
 * 基于正则表达式校验的ViewModel.
 */
public class RegexCheckViewModel extends BaseViewModel {

    public ObservableBoolean obsIsUserPhoneChecked    = new ObservableBoolean();
    public ObservableBoolean obsIsVerifyChecked       = new ObservableBoolean();
    public ObservableBoolean obsIsUserPasswordChecked = new ObservableBoolean();
    public ObservableBoolean obsIsUserIdcardChecked   = new ObservableBoolean();
    public ObservableBoolean obsIsUserNameChecked     = new ObservableBoolean();
    public ObservableBoolean obsIsUserAliasChecked    = new ObservableBoolean();
    public ObservableBoolean obsIsUserEmailChecked    = new ObservableBoolean();

    public ObservableBoolean obsIsUserPhoneNewChecked    = new ObservableBoolean();//用户新手机号
    public ObservableBoolean obsIsUserPasswordNewChecked = new ObservableBoolean();//用户新密码

    public RegexCheckViewModel(Context _context) {
        super(_context);
    }

    @Override
    protected void initField() {
        obsIsUserPhoneChecked.set(false);
        obsIsVerifyChecked.set(false);
        obsIsUserPasswordChecked.set(false);
        obsIsUserIdcardChecked.set(false);
        obsIsUserNameChecked.set(false);
        obsIsUserAliasChecked.set(false);
        obsIsUserEmailChecked.set(false);
        obsIsUserPhoneNewChecked.set(false);
        obsIsUserPasswordNewChecked.set(false);
    }

    @Override
    public String toString() {
        return "RegexCheckViewModel{" +
                ", obsIsUserPhoneChecked=" + obsIsUserPhoneChecked.get() +
                ", obsIsVerifyChecked=" + obsIsVerifyChecked.get() +
                ", obsIsUserIdcardChecked=" + obsIsUserIdcardChecked.get() +
                ", obsIsUserNameChecked=" + obsIsUserNameChecked.get() +
                '}';
    }

    /**
     * 用UserViewModel初始化内部绑定值
     *
     * @param viewModel
     */
    public void checkUserViewModelField(BaseViewModel viewModel) {
        Method[] methodArr = getClass().getDeclaredMethods();
        for (Method method : methodArr) {
            CheckUserViewModelAnnotation annotation = method.getAnnotation(CheckUserViewModelAnnotation.class);
            if (annotation != null) {
                checkFieldByViewModel(method, annotation.fieldName(), viewModel);
            }
        }
    }

    public void checkFieldByViewModel(Method method, String fieldValue, Object srcViewModel) {
        if (!StringUtils.isEmpty(fieldValue)) {
            Object argsValue;
            try {
                if (fieldValue.contains("-")) {
                    String[] fieldArr = fieldValue.split("-");
                    String outerFieldName = fieldArr[0];
                    String innerFieldName = fieldArr[1];

                    Field outerField = srcViewModel.getClass().getDeclaredField(outerFieldName);
                    outerField.setAccessible(true);
                    Object outerValue = outerField.get(srcViewModel);
                    if (outerValue instanceof ObservableField) {
                        ObservableField observableField = (ObservableField) outerValue;
                        outerValue = observableField.get();
                    }
                    Field innerField = outerValue.getClass().getDeclaredField(innerFieldName);
                    innerField.setAccessible(true);
                    argsValue = innerField.get(outerValue);
                } else {
                    Field field = srcViewModel.getClass().getDeclaredField(fieldValue);
                    field.setAccessible(true);
                    argsValue = field.get(srcViewModel);
                }
                if (argsValue != null) {
                    if (argsValue instanceof ObservableField) {
                        argsValue = ((ObservableField) argsValue).get();
                    }
                    if (argsValue instanceof String) {
                        method.invoke(RegexCheckViewModel.this, argsValue);
                    }
                }
            } catch (Exception e) {
                LogUtil.e("RegexCheckViewModel_Exception", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 校验手机号--有Toast
     */
    public boolean checkPhoneWithToast(String phoneText) {
        return RegexModel.checkTextWithToast(mContext, phoneText, RegexModel.REGEX_CELLPHONE, res.getString(R.string.tips_null_cellphone_number), res.getString(R.string.tips_error_cellphone_number));
    }

    /**
     * 校验验证码--有Toast
     */
    public boolean checkVerifyWithToast(String verifyText) {
        return RegexModel.checkTextWithToast(mContext, verifyText, RegexModel.REGEX_VERIFY_CODE, res.getString(R.string.tips_null_verify), res.getString(R.string.tips_error_verify));
    }

    /**
     * 校验密码--有Toast
     */
    public boolean checkPwdWithToast(String passWordText) {
        return RegexModel.checkTextWithToast(mContext, passWordText, RegexModel.REGEX_PASSWORD, res.getString(R.string.tips_null_password), res.getString(R.string.tips_error_password));
    }


    /**
     * 校验用户身份证号--有Toast
     */
    public boolean checkUserIdcardWithToast(String idcardText) {
        return RegexModel.checkTextWithToast(mContext, idcardText, RegexModel.REGEX_IDCARD, getString(R.string.tips_null_idcard), getString(R.string.tips_error_idcard));
    }

    /**
     * 校验用户Email--有Toast
     */
    public boolean checkUserEmailWithToast(String emailText) {
        return RegexModel.checkTextWithToast(mContext, emailText, RegexModel.REGEX_EMAIL, getString(R.string.tips_null_email), getString(R.string.tips_error_email));
    }

    /**
     * 校验别名--有Toast
     */
    public boolean checkUserAliasWithToast(String userAliastext) {
        return RegexModel.checkTextWithToast(mContext, userAliastext, RegexModel.REGEX_USER_ALIAS, getString(R.string.tips_null_user_alias), getString(R.string.tips_error_alias));
    }

    /**
     * 校验用户姓名--有Toast
     */
    public boolean checkUserNameWithToast(String userNameText) {
        return RegexModel.checkTextWithToast(mContext, userNameText, RegexModel.REGEX_USER_NAME, getString(R.string.tips_null_user_name), getString(R.string.tips_error_user_name));
    }

    //------------------------------------------------------------------------------------------

    /**
     * 校验手机号
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserPhone")
    public void checkPhone(String phoneText) {
        obsIsUserPhoneChecked.set(RegexModel.checkText(phoneText, RegexModel.REGEX_CELLPHONE));
    }

    /**
     * 校验新手机号
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserPhoneNew")
    public void checkPhoneNew(String phoneText) {
        obsIsUserPhoneNewChecked.set(RegexModel.checkText(phoneText, RegexModel.REGEX_CELLPHONE));
    }

    /**
     * 校验验证码
     */
    @CheckUserViewModelAnnotation(fieldName = "obsVerifyCode")
    public void checkVerify(String verifyText) {
        obsIsVerifyChecked.set(RegexModel.checkText(verifyText, RegexModel.REGEX_VERIFY_CODE));
    }

    /**
     * 校验密码
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserPwd")
    public void checkPwd(String passWordText) {
        obsIsUserPasswordChecked.set(RegexModel.checkText(passWordText, RegexModel.REGEX_PASSWORD));
    }

    /**
     * 校验新密码
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserPwdNew")
    public void checkPwdNew(String passWordText) {
        obsIsUserPasswordNewChecked.set(RegexModel.checkText(passWordText, RegexModel.REGEX_PASSWORD));
    }

    /**
     * 校验用户姓名
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserModel-userName")
    public void checkUserName(String userNameText) {
        obsIsUserNameChecked.set(RegexModel.checkText(userNameText, RegexModel.REGEX_USER_NAME));
    }

    /**
     * 校验别名
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserModel-userAlias")
    public void checkUserAlias(String userAliastext) {
        obsIsUserAliasChecked.set(RegexModel.checkText(userAliastext, RegexModel.REGEX_USER_ALIAS));
    }

    /**
     * 校验用户Email
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserModel-userEmail")
    public void checkUserEmail(String emailText) {
        obsIsUserEmailChecked.set(RegexModel.checkText(emailText, RegexModel.REGEX_EMAIL));
    }

    /**
     * 校验用户身份证号
     */
    @CheckUserViewModelAnnotation(fieldName = "obsUserModel-userIdentityCard")
    public void checkUserIdcard(String idcardText) {
        obsIsUserIdcardChecked.set(RegexModel.checkText(idcardText, RegexModel.REGEX_IDCARD));
    }
}

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface CheckUserViewModelAnnotation {
    String fieldName() default "";
}
