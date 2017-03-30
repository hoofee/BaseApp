package com.hoofee.everything.main.app.code;

/**
 * Created by hufei on 2016/8/30.
 * 接口回调的返回状态码
 */
public class CodeWithApiState {

    public final static int ILLEGALOPER = 4011;   //非法操作
    public final static int NOTLIMITTEDOPER = 4012; //缺乏访问权限
    public final static int INVALIDACCESSTOKEN = 4013; //accesstoken超时或失效
}
