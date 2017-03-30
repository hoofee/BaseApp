package com.hoofee.everything.main.adapterviewlist.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoofee.everything.BR;
import com.hoofee.everything.main.activity.base.BaseActivity;
import com.hoofee.everything.main.activity.base.BaseFragment;
import com.hoofee.everything.main.viewmodel.base.BaseViewModel;

import java.util.List;


public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder> {

    private Context mContext;

    private List   dataSources;
    private int    itemLayout;
    private String flag;

    private BaseFragment  fragment;
    private BaseActivity  activity;
    private BaseViewModel viewModel;

    public RecyclerItemAdapter(Context _context, List _data, int _itemLayout) {
        mContext = _context;
        dataSources = _data;
        itemLayout = _itemLayout;
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

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(itemLayout, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (dataSources.size() > 0) {
            if (i >= dataSources.size()) {
                i = dataSources.size() - 1;
            }
            viewHolder.bind(dataSources.get(i), i);
        }
    }

    @Override
    public int getItemCount() {
        if (dataSources != null) {
            return dataSources.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }

        public void bind(@NonNull Object data, int _index) {
            if (fragment != null) {
                binding.setVariable(BR.fragment, fragment);
            }
            if (activity != null) {
                binding.setVariable(BR.activity, activity);
            }
            if (viewModel != null) {
                binding.setVariable(BR.viewModel, viewModel);
            }
            if (flag != null) {
                binding.setVariable(BR.flag, flag);
            }

            try {
                binding.setVariable(BR.index, _index);
                binding.setVariable(BR.item, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //binding.executePendingBindings();
        }
    }
}
