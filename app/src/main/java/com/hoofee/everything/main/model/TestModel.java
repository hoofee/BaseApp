package com.hoofee.everything.main.model;

import android.databinding.Bindable;

import com.hoofee.everything.BR;
import com.hoofee.everything.main.model.base.BaseModel;


/**
 * Created by hufei on 2016/8/31.
 * 测试参照Model
 */
public class TestModel extends BaseModel {

    private String userName;

    @Bindable
    private String userAge;

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
        notifyPropertyChanged(BR.userAge);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
