package com.hoofee.everything.main.app;

import android.os.Environment;

import com.hoofee.everything.BuildConfig;
import com.hoofee.everything.main.bindingadapter.ImageBindAdapter;
import com.hoofee.everything.main.utils.StringUtils;

/**
 * Created by hufei on 2016/7/28.
 * APP全局一些配置
 */
public class AppConfig {

    //App包名
    public static final String APP_PACKAGE_NAME = "cn.vtutor.Temple";

    //APP的Logo
    public static final String APP_ICON = "http://content.17donor.com/content/appimage/todayDonorAPPIcon.png";

    //域名
    public static String HOST_URL = "https://bs.sharing8.cn";
//    public static String HOST_URL = "https://www.17donor.com";

    //接口API
    public static String HOST_URL_API = HOST_URL + "/app/";

    public static String IMAGE_ZOOM_SUFFIX = "@!200";

    public static String IMAGE_ZOOM_SUFFIX_BY_CIRCLE_PHOTO = "@!100";

    //App的完整下载链接
    // public static String AppDownloadLink = HOST_URL_API + "/manage/stations/appDownload?from=groupmessage";
    //App的下载端链接
    //public static String AppDownloadLinkShort = "http://t.cn/R5xamSF";

    //APP ICON URL,用于分享中的图标
    //public static String APPICON = "http://content.17donor.com/content/appimage/todayDonorAPPIcon.png";

    //网页携带的UA信息
    public static String UA_WEB = " DonorToday/" + BuildConfig.VERSION_NAME;

    //App使用的文件目录
    public static String FILE_PATH_ROOT = Environment.getExternalStorageDirectory().getPath();

    /**
     * 获取完整的带域名的Url
     */
    public static String getAppImage(String imageUrl) {
        if (StringUtils.isEmpty(imageUrl)) return "";
        return imageUrl.contains("http") ? imageUrl : HOST_URL + imageUrl;
    }

    /**
     * 获取完整的带域名的Url
     */
    public static String getAppImage(String imageUrl, ImageBindAdapter.TransformationEnum transf, String zoomType) {
        if (StringUtils.isEmpty(imageUrl)) return "";
        if (StringUtils.isEmpty(zoomType)) zoomType = "";
        //个人头像
        if (transf != null && transf == ImageBindAdapter.TransformationEnum.CROP_CIRCLE) {
            return imageUrl.startsWith("http") ? imageUrl : HOST_URL + imageUrl + IMAGE_ZOOM_SUFFIX_BY_CIRCLE_PHOTO;
        }
        return (imageUrl.startsWith("http") ? imageUrl : HOST_URL + imageUrl) + zoomType;
    }

}
