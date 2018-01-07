package com.example.latte.ui.recyler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte.net.interceptors.BaseInterceptor;

/**
 * Created by ZBK on 2018-01-07.
 */

public class MultipleViewHolder extends BaseViewHolder{
    private MultipleViewHolder(View view) {
        super(view);
    }
    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
