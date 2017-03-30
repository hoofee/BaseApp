package com.hoofee.everything.main.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.hoofee.everything.main.utils.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;


/**
 * Created by hufei on 2016/7/28.
 * App全局堆栈管理
 */
public class AppManager {

    private Stack<Activity> activityStack = new Stack<>();
    private static AppManager instance = new AppManager();

    private AppManager() {
    }

    public static AppManager getInstance() {
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public synchronized Activity currentActivity() {
        if (activityStack.size() > 0) {
            return activityStack.lastElement();
        } else {
            return null;
        }
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public synchronized void finishActivity(Activity activity) {
        if (activity != null) {
            removeActivityOfStack(activity);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (!activity.isDestroyed())
                    activity.finish();
            } else {
                activity.finish();
            }
        }
    }

    public void removeActivityOfStack(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public synchronized void finishActivity(Class<?>... cls) {
        if (activityStack.size() <= 0 || cls == null || cls.length <= 0)
            return;
        List<Class> classList = new ArrayList<Class>(Arrays.asList(cls));
        for (Class clazz : classList) {
            for (Activity activity : activityStack) {
                Class activityClass = activity.getClass();
                if (activityClass.getSimpleName().equals(clazz.getSimpleName())) {
                    finishActivity(activity);
                }
            }
        }
    }

    /**
     * 清除前面所有的Activity,只保留当前的activity
     */
    public synchronized void finishPreviousActivity(Activity activity) {
        if (activity != null) {
            activityStack.clear();
            activityStack.add(activity);
        }
    }

    /**
     * 清除堆栈中的所有activity
     */
    public void clearStack() {
        activityStack.clear();
    }

    /**
     * 结束所有Activity
     */
    public synchronized void finishAllActivity() {
        if (activityStack.size() > 0) {
            for (int i = 0; i < activityStack.size(); i++) {
                Activity activity = activityStack.get(i);
                if (null != activity) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            LogUtil.i("退出异常");
        }
    }

    /**
     * @param activity
     * @return 返回指定activity的索引
     */
    public int getCurrentIndex(Activity activity) {
        return activityStack.search(activity);
    }

    /**
     * @param activity
     * @return 返回指定Activity的最后一个位置
     */
    public int getLastIndex(Activity activity) {
        return activityStack.lastIndexOf(activity);
    }

    /**
     * 移除指定位置的索引
     *
     * @param index
     */
    public void removeActivity(int index) {
        activityStack.get(index).finish();
    }

    /**
     * 移除相同Activity之间的Activity
     *
     * @param activityClass
     */
    public synchronized void finishOldActivity(Class activityClass) {
        int index = 0;
        int activitySum = 0;
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (index != 0) {
                activitySum++;
            }
            if (activity.getClass().equals(activityClass)) {
                if (index == 0) {
                    index = i;
                } else {
                    break;
                }
            }
        }
        if (activitySum > 0) {
            for (int i = index; i <= activitySum; i++) {
                removeActivity(i);
            }
        }
    }

    /**
     * 关闭堆栈中之前的n个Activity
     *
     * @param length
     */
    public void finishActivityForLength(int length) {
        if (length <= activityStack.size()) {
            for (int i = activityStack.size() - 2; i >= activityStack.size() - length - 1; i--) {
                removeActivity(i);
            }
        }
    }

    /**
     * 判断堆栈中是否包含有某个activity
     *
     * @param activityClass
     * @return
     */
    public synchronized boolean containsActivity(Class activityClass) {
        ListIterator<Activity> activityList = activityStack.listIterator();
        while (activityList.hasNext()) {
            Activity activity = activityList.next();
            if (activity.getClass().getSimpleName().equals(activityClass.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断传入的Activity是否在栈顶
     *
     * @param activityClass
     * @return
     */
    public synchronized boolean isCurrentOfLaset(Class<?> activityClass) {
        int size = activityStack.size();
        if (size > 0) {
            return activityStack.get(activityStack.size() - 1).getClass().equals(activityClass);
        } else {
            return true;
        }
    }

    /**
     * 获取索引的Activity
     *
     * @param index
     * @return
     */
    public Class getActivity(int index) {
        return activityStack.get(index).getClass();
    }

    /**
     * 从堆栈中获取指定名称的Activity
     *
     * @param activityClass
     * @return
     */
    public synchronized Activity getActivity(Class activityClass) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(activityClass)) {
                return activityStack.get(i);
            }
        }
        return null;
    }

    /**
     * 获取堆栈的长度
     *
     * @return
     */
    public int getLength() {
        return activityStack.size();
    }
}
