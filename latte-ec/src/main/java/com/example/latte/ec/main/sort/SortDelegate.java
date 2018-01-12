package com.example.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.delegate.bottom.BottomItemDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.main.sort.contect.ContentDelegate;
import com.example.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * User: bkzhou
 * Date: 2018/1/4
 * Description:
 */
public class SortDelegate  extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
