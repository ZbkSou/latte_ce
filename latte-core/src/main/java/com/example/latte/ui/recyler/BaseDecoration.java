package com.example.latte.ui.recyler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * User: bkzhou
 * Date: 2018/1/9
 * Description:
 */
public class BaseDecoration extends DividerItemDecoration{
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }
    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color,size);
    }
}
