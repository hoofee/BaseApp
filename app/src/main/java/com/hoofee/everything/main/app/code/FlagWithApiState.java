package com.hoofee.everything.main.app.code;

/**
 * Created by hufei on 2016/8/30.
 * 有关接口的回调flag
 */
public class FlagWithApiState {
    //普通的接口标记
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String NOMORE = "nomore";

    public static final String SUCCESS_CODE = "200";
    public static final String FAIL_CODE = "400";

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    //分页数据接口的标记
    public static final String PAGE_SUCCESS_FIRST = "page_success_first";   //第一页
    public static final String PAGE_SUCCESS_MIDDLE = "page_success_middle";   //中间页
    public static final String PAGE_SUCCESS_NOMORE = "page_success_nomore";     //最后一页
    public static final String PAGE_FAIL = "page_fail";     //分页数据获取失败
}
