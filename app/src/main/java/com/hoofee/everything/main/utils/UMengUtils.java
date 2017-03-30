package com.hoofee.everything.main.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.widget.Toast;
//
//import com.umeng.analytics.MobclickAgent;
//import com.umeng.socialize.ShareAction;
//import com.umeng.socialize.UMAuthListener;
//import com.umeng.socialize.UMShareAPI;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.umeng.socialize.media.UMEmoji;
//import com.umeng.socialize.media.UMImage;
//import com.umeng.socialize.media.UMVideo;
//import com.umeng.socialize.media.UMediaObject;
//import com.umeng.socialize.media.UMusic;
//import com.umeng.socialize.view.UMFriendListener;
//
//import java.util.Map;
//
///**
// * user hufei
// * date 2016/2/17:17:50
// * describe:调用友盟的工具类，现包含友盟的更新、统计、分享、第三方登录
// * version:友盟集成版本 5.1.1
// */
public class UMengUtils {}
//
//    private static Context mContext;
//    private static SHARE_MEDIA currentPlatform;
//    private static UMShareAPI mShareAPI;
//
//    private UMengUtils() {
//    }
//
//    /**
//     * 设置分享的平台
//     * 需要检查Android 6.0权限的：SHARE_MEDIA.SMS
//     */
//    public static final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{
//            SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
//            SHARE_MEDIA.QZONE, SHARE_MEDIA.TENCENT, SHARE_MEDIA.EMAIL,
//    };
//
//    public static UMengUtils getInstance(Context context, SHARE_MEDIA platform) {
//        mContext = context;
//        currentPlatform = platform;
//        mShareAPI = UMShareAPI.get(mContext);
//        return new UMengUtils();
//    }
//
//    //----------------------------------友盟第三方登录-----------------------------------
//
//    public UMShareAPI getUMShareAPI() {
//        return mShareAPI;
//    }
//
//    /**
//     * 判断用户是否安装了第三方登录平台的客户端
//     *
//     * @return
//     */
//    public boolean isInstall() {
//        return mShareAPI.isInstall((Activity) mContext, currentPlatform);
//    }
//
//    /**
//     * 调用第三方登录
//     *
//     * @param platformLoginListener 登录之后的监听
//     */
//    public void otherLogin(UMAuthListener platformLoginListener) {
//        mShareAPI.doOauthVerify((Activity) mContext, currentPlatform, platformLoginListener);
//    }
//
//    /**
//     * 判断第三方平台是否登陆
//     */
//    public boolean isLogin() {
//        return mShareAPI.isAuthorize((Activity) mContext, currentPlatform);
//    }
//
//    /**
//     * 退出第三方登录
//     *
//     * @param platformLoginListener
//     */
//    public void otherLogout(UMAuthListener platformLoginListener) {
//        mShareAPI.deleteOauth((Activity) mContext, currentPlatform, platformLoginListener);
//    }
//
//    /**
//     * 获取平台用户信息
//     *
//     * @param platformInfoListener
//     */
//    public void getPlatformInfo(UMAuthListener platformInfoListener) {
//        mShareAPI.getPlatformInfo((Activity) mContext, currentPlatform, platformInfoListener);
//    }
//
//    /**
//     * 获取平台用户好友
//     *
//     * @param plaformFriendListener notNull
//     */
//    public void getPlatformFriend(UMFriendListener plaformFriendListener) {
//        mShareAPI.getFriend((Activity) mContext, currentPlatform, plaformFriendListener);
//    }
//
//
//    /**
//     * 友盟获取第三方授权监听回调
//     */
//    public static abstract class AbsUMAuthListener implements UMAuthListener {
//
//        @Override
//        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//            LogUtils.e("UMeng_LoginError,平台：" + share_media.toString() + ",Code:" + i);
//            ToastUtils.showToast(mContext, "抱歉，登录发生了错误，请手动进行登录！", Toast.LENGTH_LONG);
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA share_media, int i) {
//            LogUtils.e("UMeng_LoginCancel,平台：" + share_media.toString() + ",Code:" + i);
//        }
//    }
//
//    /**
//     * 友盟获取第三方好友的监听回调
//     */
//    public static abstract class AbsUMFriendListener implements UMFriendListener {
//
//        @Override
//        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//            LogUtils.e("UMeng_GetFriendError,平台：" + share_media.toString() + ",Code:" + i);
//            ToastUtils.showToast(mContext, "抱歉，获取好友发生了错误！", Toast.LENGTH_LONG);
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA share_media, int i) {
//            LogUtils.e("UMeng_GetFriendCancel,平台：" + share_media.toString() + ",Code:" + i);
//        }
//    }
//
//    //------------------------------------友盟统计---------------------------------------
//
//    /**
//     * 统计发生次数
//     *
//     * @param mContext
//     * @param eventId  为当前统计的事件ID。
//     */
//    public static void getCount(Context mContext, String eventId) {
//        MobclickAgent.onEvent(mContext, eventId);
//    }
//
//    /**
//     * 统计某一次操作的各个属性被触发的次数
//     *
//     * @param mContext
//     * @param eventId  为当前统计的事件ID。
//     * @param map      为当前事件的属性和取值（Key-Value键值对）。
//     */
//    public static void getSessionCount(Context mContext, String eventId, Map map) {
//        MobclickAgent.onEvent(mContext, "purchase", map);
//    }
//
//    /**
//     * 统计数值型变量的值的分布
//     *
//     * @param mContext
//     * @param id       为当前统计的事件ID。
//     * @param map      为当前事件的属性和取值（Key-Value键值对）。
//     * @param du       为当前事件的数值为当前事件的数值，取值范围是-2,147,483,648 到 +2,147,483,647 之间的有符号整数，即int 32类型，如果数据超出了该范围，会造成数据丢包，影响数据统计的准确性。
//     */
//    public static void getSessionDistribute(Context mContext, String id, Map<String, String> map, int du) {
//        MobclickAgent.onEventValue(mContext, id, map, du);
//    }
//
//    //-----------------------------------友盟分享-----------------------------------
//
//    /**
//     * 简单的分享，设置分享内容并打开默认的分享面板
//     *
//     * @param activity    当前的activity
//     * @param content     分享的内容
//     * @param title       分享的标题
//     * @param targetUrl   分享的目标URL
//     * @param mediaObject 分享的媒体文件，可以是图片，音乐，视频
//     */
//
//    public static void share(Activity activity, String content, String title, String targetUrl, UMediaObject mediaObject) {
//        ShareAction action = new ShareAction(activity).setDisplayList(displaylist)
//                .withText(content)
//                .withTitle(title)
//                .withTargetUrl(targetUrl);
//
//        if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.MUSIC) == 0) {
//            action.withMedia((UMusic) mediaObject);
//        } else if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.IMAGE) == 0) {
//            action.withMedia((UMImage) mediaObject);
//        } else if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.VEDIO) == 0) {
//            action.withMedia((UMVideo) mediaObject);
//        } else if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.TEXT_IMAGE) == 0) {
//            action.withMedia((UMEmoji) mediaObject);
//        }
//        action.open();
//    }
//
//    /**
//     * 将要分享的内容分享到自定义的平台
//     *
//     * @param activity
//     * @param platform
//     * @param content
//     * @param title
//     * @param targetUrl
//     * @param mediaObject
//     */
//    public static void share(Activity activity, SHARE_MEDIA platform, String content, String title, String targetUrl, UMediaObject mediaObject) {
//        ShareAction action = new ShareAction(activity).setPlatform(platform)
//                .withText(content)
//                .withTitle(title)
//                .withTargetUrl(targetUrl);
//        if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.MUSIC) == 0) {
//            action.withMedia((UMusic) mediaObject);
//        } else if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.IMAGE) == 0) {
//            action.withMedia((UMImage) mediaObject);
//        } else if (mediaObject.getMediaType().compareTo(UMediaObject.MediaType.VEDIO) == 0) {
//            action.withMedia((UMVideo) mediaObject);
//        }
//        action.share();
//    }
//
//    /**
//     * 返回一个确定分享平台的分享对象，方便外部做复杂的分享操作
//     *
//     * @param activity
//     * @return
//     */
//    public static ShareAction share(Activity activity) {
//        return new ShareAction(activity).setDisplayList(displaylist);
//    }
//
//    /**
//     * 设置分享媒体文件用法：.withMedia(mediaObject)方法参数可以放以下对象UMImage/UMusic/UMVideo
//     * 使用ShareAction(this).setDisplayList( displaylist)可以进行分享面板的平台设置。
//     * 同时使用setContentList(),可以进行不同分享内容的处理，
//     * 使用setListenerList() 进行不同回调的处理。
//     * 使用setShareboardclickCallback() 可以进行分享面板不同按钮的点击回调，方便开发者进行特殊处理。
//     */
////    图片(url)
////    UMImage image = new UMImage(ShareActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
////
////    图片(本地资源引用)
////    UMImage image = new UMImage(ShareActivity.this, BitmapFactory.decodeResource(getResources(), R.drawable.image));
////
////    图片(本地绝对路径)
////    UMImage image = new UMImage(ShareActivity.this, BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));
//
////    URL音频及图片
////    UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
////    music.setTitle("sdasdasd");
////    music.setThumb(new UMImage(ShareActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));
////
////    url视频
////    UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
//
//
//
//}
