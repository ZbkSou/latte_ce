package com.example.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.main.sort.SortDelegate;
import com.example.latte.ui.recyler.ItemType;
import com.example.latte.ui.recyler.MultipleFields;
import com.example.latte.ui.recyler.MultipleItemEntity;
import com.example.latte.ui.recyler.MultipleRecylerAdapter;
import com.example.latte.ui.recyler.MultipleViewHolder;

import java.util.List;

/**
 * User: bkzhou
 * Date: 2018/1/16
 * Description:
 */
public class SortRecyclerAdapter extends MultipleRecylerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE =delegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                if(!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }
}
