package com.example.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.delegate.bottom.BottomItemDelegate;
import com.example.latte.ec.R;

/**
 * User: bkzhou
 * Date: 2018/1/3
 * Description:
 */
public class IndexDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}