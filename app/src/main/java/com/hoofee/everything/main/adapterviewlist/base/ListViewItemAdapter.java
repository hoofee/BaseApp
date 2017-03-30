package com.hoofee.everything.main.adapterviewlist.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hoofee.everything.BR;
import com.hoofee.everything.main.activity.base.BaseActivity;
import com.hoofee.everything.main.activity.base.BaseFragment;
import com.hoofee.everything.main.viewmodel.base.BaseViewModel;

import java.util.List;


/**
 * Created by hufei on 2017/2/28.
 */
public class ListViewItemAdapter extends BaseAdapter {

    private Context mContext;

    private List dataSources;
    private int  itemLayout;

    private String        flag;
    private BaseFragment  fragment;
    private BaseActivity  activity;
    private BaseViewModel viewModel;

    public ListViewItemAdapter(Context _context, List _data, int _itemLayout) {
        mContext = _context;
        dataSources = _data;
        itemLayout = _itemLayout;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        return dataSources.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            viewHolder.viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), itemLayout, parent, false);
            convertView = viewHolder.viewDataBinding.getRoot();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.viewDataBinding.setVariable(BR.item, position);
        viewHolder.viewDataBinding.setVariable(BR.item, dataSources.get(position));
        if (fragment != null) {
            viewHolder.viewDataBinding.setVariable(BR.fragment, fragment);
        }
        if (activity != null) {
            viewHolder.viewDataBinding.setVariable(BR.activity, activity);
        }
        if (viewModel != null) {
            viewHolder.viewDataBinding.setVariable(BR.viewModel, viewModel);
        }
        if (flag != null) {
            viewHolder.viewDataBinding.setVariable(BR.flag, flag);
        }

        return viewHolder.viewDataBinding.getRoot();
    }

    class ViewHolder {
        ViewDataBinding viewDataBinding;
    }

}
