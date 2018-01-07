package com.example.latte.ui.recyler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZBK on 2018-01-07.
 */

public class MultipleRecylerAdapter extends
  BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
  implements
  BaseQuickAdapter.SpanSizeLookup {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected MultipleRecylerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecylerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecylerAdapter(data);
    }
    public static MultipleRecylerAdapter create(DataConveter conveter) {
        return new MultipleRecylerAdapter(conveter.convert());
    }
    private void init(){
        //设置不同的item布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_imgaetext);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
//        多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        final  String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()){
            case ItemType.TEXT:
                text = item.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single,text);
                break;
            case ItemType.IMAGE:
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                holder.setText(R.id.img_sigle,imageUrl);
                break;
            case ItemType.TEXT_IMAGE:
                imageUrl =item.getField(MultipleFields.IMAGE_URL);
                text =item.getField(MultipleFields.TEXT);
                break;
            case ItemType.BANNER:
                bannerImages = item.getField(MultipleFields.BANNERS);
                break;
            default:
                break;
        }

    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
