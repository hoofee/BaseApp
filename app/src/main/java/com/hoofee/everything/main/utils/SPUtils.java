package com.hoofee.everything.main.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * spXml操作
 *
 * @author 李立强 完善目前Sp不足地方
 *         修改：panhc
 *         修改备注：改为非静态类，增加设置文件名，获取xml文件大小等
 */
public class SPUtils {

    //默认存放位置
    public final static String LABELDATE = "user_label";//新用户第一次登录时要选择的标签

    //存放用户信息的缓存文件
    public static String CACHE_USER_INFO = "cn-sharing8-blood-user";
    public static String BLOOD_TYPE = "approveBloodType";//血检查询后用户的真实血型
    public static String IS_BLOOD_APPROVED_SUCCESS = "approveBloodType";//血检查询认证是否认证成功，查不到这个key表示没有认证过(值为"true"/"false")
    public static String WEIXIN_LOGIN_MODEL = "weixin_login_model";//微信登录后的信息Model

    //存放AccessTokem的缓存文件
    public static String CACHE_ACCESSTOKEN_INFO = "cn-sharing8-blood-accesstoken";
    //SP中CACHE_ACCESSTOKEN_INFO缓存中存放的key
    public final static String IS_FIRST_USE = "isFirstUse";
    public static final String ACCESSTOKEN = "accesstokenModel";
    public static final String ACCESSTOKEN_LOGIN = "accesstokenModelLogin";
    public static final String LOCATION = "location";//定位的城市
    public static final String USER_AGENT_INFO = "user_agent_info";
    public final static String FRAGMENT_HOME_BG = "framentHomeBg";//首页背景图的url缓存

    public static final String PWD_ERROR_TIMES = "pwd_error_times"; // 密码输入错误次数
    public static final String PWD_ERROR_TIMESTAMP = "pwd_error_timestamp"; // 上次密码输入错误时间

    public final String FILE_NAME = "share_data";   //保存在手机里面的文件名
    public final String SHAREDDIR = "shared_prefs";   //SharedPreferences 文件夹默认名称
    public SharedPreferences sp = null;
    public String newfilename = null;

    public SPUtils() {
        /* cannot be instantiated */
        //throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置非默认xml文件名
     *
     * @param context
     * @param filename
     */
    public SPUtils(Context context, String filename) {
        sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        newfilename = filename;
    }

    public void setSharedFileName(Context context, String filename) {
        sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        newfilename = filename;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public void put(Context context, String key, Object object) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public Object get(Context context, String key, Object defaultObject) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return defaultObject;
    }

    /**
     * 获取key的Value,默认为""
     *
     * @param context
     * @param key
     * @return
     */
    public String getString(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, "");
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public void remove(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public void clear(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public boolean contains(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public Map<String, ?> getAll(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        return sp.getAll();
    }

    /**
     * 获取SharedPreferences大小
     *
     * @param context
     * @return
     */
    public long getSharedSize(Context context) {
        File file = getSharedFile(context);
        try {
            long filesize;
            if (file == null) {
                filesize = 0L;
            }
            filesize = file.length();
            return filesize;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取sharedpreference文件
     *
     * @param context
     * @return
     */
    public File getSharedFile(Context context) {
        File file = context.getCacheDir();
        String filename = FILE_NAME;
        if (newfilename != null) {
            filename = newfilename;
        }
        String path = file.getParent() + File.separator + file.getName().replace("cache", this.SHAREDDIR) + File.separator + filename + ".xml";
        file = new File(path);
        return file;
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}