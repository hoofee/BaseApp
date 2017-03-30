package com.hoofee.everything.main.bindingadapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hoofee.everything.R;
import com.hoofee.everything.main.activity.base.BaseActivity;
import com.hoofee.everything.main.activity.base.BaseFragment;
import com.hoofee.everything.main.adapterviewlist.base.RecyclerItemAdapter;
import com.hoofee.everything.main.app.AppContext;
import com.hoofee.everything.main.utils.ObjectUtil;
import com.hoofee.everything.main.viewmodel.base.BaseViewModel;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.List;

/**
 * Created by hufei on 2017/2/28.
 */

public class RecyclerViewBindAdapter {

    @android.databinding.BindingAdapter(value = {"itemList", "itemLayout", "emptyView", "flag", "activity", "fragment", "viewModel"}, requireAll = false)
    public static void bindRecycleItemLayout(final RecyclerView view,
                                             final List itemList, int itemLayout, int emptyView, String flag,
                                             BaseActivity activity, BaseFragment fragment, BaseViewModel viewModel) {
        if (view.getAdapter() == null) {
            RecyclerItemAdapter adapter = new RecyclerItemAdapter(view.getContext(), itemList, itemLayout);
            adapter.setActivity(activity);
            adapter.setFragment(fragment);
            adapter.setViewModel(viewModel);
            adapter.setFlag(flag);
            view.setAdapter(adapter);
        } else {
            view.getAdapter().notifyDataSetChanged();
        }
        View rootView = view.getRootView();
        final View _emptyView = rootView.findViewById(emptyView);
        if (_emptyView != null) {
            AppContext.mainHandler.postDelayed(() -> {
                if (view.getAdapter() != null && ObjectUtil.isEmpty(itemList)) {
                    if (view.getParent() instanceof SwipeRefreshLayout) {
                        ((View) view.getParent()).setVisibility(View.VISIBLE);
                    }
                    view.setVisibility(View.VISIBLE);
                    _emptyView.setVisibility(View.GONE);
                }
            }, 800);
        }
    }

    @android.databinding.BindingAdapter({"rvManager"})
    public static void setRecyclerViewLayoutManager(RecyclerView
                                                            view, RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            view.setLayoutManager(layoutManager);
        }
    }

    @android.databinding.BindingAdapter({"rvAddLine"})
    public static void addRecyclerViewLine(RecyclerView view, boolean isVertical) {
        if (isVertical) {
            view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(view.getContext()).colorResId(R.color.main_bg_color).build());
        } else {
            view.addItemDecoration(new VerticalDividerItemDecoration.Builder(view.getContext()).build());
        }
    }
}
