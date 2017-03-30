package com.hoofee.everything.main.bindingadapter;

import android.widget.ListView;

import com.hoofee.everything.main.activity.base.BaseActivity;
import com.hoofee.everything.main.activity.base.BaseFragment;
import com.hoofee.everything.main.adapterviewlist.base.ListViewItemAdapter;
import com.hoofee.everything.main.viewmodel.base.BaseViewModel;

import java.util.List;

/**
 * Created by hufei on 2017/2/28.
 */

public class ListViewBindAdapter {

    @android.databinding.BindingAdapter(value = {"itemList", "itemLayout", "activity", "fragment", "viewModel"}, requireAll = false)
    public static void bindItemLayout(ListView view, List itemlist, int itemlayoutid,
                                      BaseActivity activity, BaseFragment fragment, BaseViewModel viewModel) {
        if (view.getAdapter() == null) {
            ListViewItemAdapter adapter = new ListViewItemAdapter(view.getContext(), itemlist, itemlayoutid);
            adapter.setFragment(fragment);
            adapter.setActivity(activity);
            adapter.setViewModel(viewModel);
            view.setAdapter(adapter);
        } else {
            ((ListViewItemAdapter) view.getAdapter()).notifyDataSetChanged();
        }
    }
}
