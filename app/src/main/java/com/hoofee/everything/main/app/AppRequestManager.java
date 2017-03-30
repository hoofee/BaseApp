package com.hoofee.everything.main.app;

import android.util.SparseArray;

import retrofit2.Call;

/**
 * Created by hufei on 2016/9/1.
 * Http请求的队列
 */
public class AppRequestManager {

    //    private List<String> tagNameList = new ArrayList<>();
    private SparseArray<Call> callMap = new SparseArray();

    private static AppRequestManager appRequestManager;

    private AppRequestManager() {
    }

    public static AppRequestManager getInstance() {
        if (appRequestManager == null) {
            synchronized (AppRequestManager.class) {
                if (appRequestManager == null) {
                    appRequestManager = new AppRequestManager();
                }
            }
        }
        return appRequestManager;
    }

    public void addCall(String tag, Call call) {
        callMap.append(tag.hashCode(), call);
//        tagNameList.add(tag);
    }

    public Call getCall(String tag) {
        return callMap.get(tag.hashCode());
    }

    public synchronized void removeCall(String tag) {
        int key = tag.hashCode();
//        int index = callMap.indexOfKey(key);

        callMap.remove(key);
//        if (index != -1 && tagNameList.size() > index) {
//            tagNameList.remove(index);
//        }
    }

    public synchronized void removeCall(Call call){
        int index=callMap.indexOfValue(call);
        if(index>=0){
            callMap.removeAt(index);
        }
    }

    public void cancelCall(String tag) {
        Call call = getCall(tag);
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }

//    public String getTagName(Call call) {
//        int index = callMap.indexOfValue(call);
//        if (index != -1 && tagNameList.size() > index) {
//            return tagNameList.get(index);
//        }
//        return "null";
//    }

    public int size() {
        return callMap.size();
    }

    @Override
    public String toString() {
        return "AppRequestManager{" +
                "callMap=" + callMap.toString() +
                '}';
    }
}
