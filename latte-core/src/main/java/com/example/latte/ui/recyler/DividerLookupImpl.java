package com.example.latte.ui.recyler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * User: bkzhou
 * Date: 2018/1/9
 * Description:
 */
public class DividerLookupImpl implements DividerItemDecoration.DividerLookup{
    private final int COLOR;

    private final int SIZE;

    public DividerLookupImpl(int COLOR, int SIZE) {
        this.COLOR = COLOR;
        this.SIZE = SIZE;
    }
    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
            .size(SIZE)
            .color(COLOR)
            .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
            .size(SIZE)
            .color(COLOR)
            .build();
    }
}
