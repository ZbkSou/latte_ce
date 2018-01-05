package com.example.latte.ec.main;

import android.graphics.Color;

import com.example.latte.delegate.bottom.BaseBottomDelegate;
import com.example.latte.delegate.bottom.BottomItemDelegate;
import com.example.latte.delegate.bottom.BottomTabBean;
import com.example.latte.delegate.bottom.ItemBuilder;
import com.example.latte.ec.main.index.IndexDelegate;
import com.example.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * User: bkzhou
 * Date: 2018/1/3
 * Description:电商首页
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
//        设置底部
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new SortDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new SortDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new SortDelegate());
        return builder.addItems(items).build();
    }


    @Override
    public int setIdexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
