package com.hoofee.everything.main.utils;

import android.app.AppOpsManager;
import android.content.Context;

/**
 * user hufei
 * date 2016/1/13:14:10
 * describe:将权限名转换成响应的权限码,主要用于android6.0以下系统的判断
 */
public class PermissionsUtil {

//    private PermissionsUtil() {
//    }
//
//    private static PermissionsUtil permissionsUtils;
//    private static Context mContext;
//    private static Map<String, Integer> permissionMap = new HashMap<String, Integer>();
//
//    public static PermissionsUtil getInstance(Context mContext) {
//        if (permissionsUtils == null) {
//            synchronized (PermissionsUtil.class) {
//                if (permissionsUtils == null) {
//                    PermissionsUtil.mContext=mContext;
//                    permissionsUtils = new PermissionsUtil();
//                }
//            }
//        }
//        return permissionsUtils;
//    }

    /**
     * 动态判断是否有权限
     * @param mContext
     * @param permissions 如果出现权限名不识别就需要对照"android.app.AppOpsManager"这个类中去找相应的权限码
     * @return 是否有传入的这些权限
     */
    public static boolean hasPermissions(Context mContext,int... permissions) {
        boolean isPermissions=true;
        if(permissions!=null){
            int i=0;
            int[] permissionArr=new int[permissions.length];
            for(int j=0;j<permissionArr.length;j++){
                permissionArr[j]=9;
            }
            AppOpsManager mAppOps = (AppOpsManager) mContext.getSystemService(Context.APP_OPS_SERVICE);
            try {
                Class mAppOpsManagerClass = Class.forName("android.app.AppOpsManager");
                int uid = mContext.getApplicationInfo().uid;
                String pkg = mContext.getPackageName();
                //返回 0 就代表有权限，1代表没有权限，2函数出错啦。
                for(int permission:permissions){
                    permissionArr[i++]=(int)mAppOpsManagerClass.getDeclaredMethod("checkOp", int.class,int.class,String.class).invoke(mAppOps, permission,uid,pkg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(int flag:permissionArr){
                //返回 0 就代表有权限，1代表没有权限。
                if(flag!=0){
                    isPermissions=false;
                    break;
                }
            }
        }
        return isPermissions;
    }
}
