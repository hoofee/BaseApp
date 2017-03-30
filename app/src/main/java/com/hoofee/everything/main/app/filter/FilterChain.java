package com.hoofee.everything.main.app.filter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hufei on 2016/8/30.
 * 拦截器链
 */
public class FilterChain {

    private List<IFilterSkip> filterSkipList;
    private int index = 0;

    private Context mContext;
    private String className;
    private Intent intent;

    public FilterChain(Intent intent, Context mContext, String className) {
        filterSkipList = new ArrayList<>();

        this.intent = intent;
        this.mContext = mContext;
        this.className = className;
    }

    public FilterChain addFilter(IFilterSkip filterSkip) {
        filterSkipList.add(filterSkip);
        return this;
    }

    public boolean doFilters() {
        if (filterSkipList.size() <= 0) return true;
        boolean isPass = true;
        for (IFilterSkip filter : filterSkipList) {
            if (!filter.doFilter(mContext, className, intent)) {
                isPass = false;
                break;
            }
            index++;
        }
        return isPass;
    }

    public int currentFilterIndex() {
        return index;
    }
}
