package com.hoofee.everything.main.adapterviewlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoofee.everything.BR;
import com.hoofee.everything.R;

import java.util.List;


/**
 * 测试RecyclerView Adapter
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    // view type 从10开始，防止与父adapter的 view type 冲突
    private static final int VIEW_TYPE_NEWS  = 10;//新闻类型
    private static final int VIEW_TYPE_PHOTO = 11;//图册类型
    private static final int VIEW_TYPE_AD    = 12;//广告图片
    private static final int VIEW_TYPE_TITLE = 13;//分组标题

    private Context mContext;

    private List          itemList;
    private List<Integer> itemViewTypes;

    public NewsListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return itemViewTypes.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_AD:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_test, parent, false));
            case VIEW_TYPE_NEWS:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_test, parent, false));
            case VIEW_TYPE_PHOTO:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_test, parent, false));
            case VIEW_TYPE_TITLE:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_test, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(itemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemList.size() + 1;
    }


    public class ViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private T itemBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Object data, int index) {
            itemBinding.setVariable(BR.item, data);
            itemBinding.setVariable(BR.index, index);
        }
    }

}