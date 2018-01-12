package com.example.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.ui.recyler.DataConveter;
import com.example.latte.ui.recyler.ItemType;
import com.example.latte.ui.recyler.MultipleFields;
import com.example.latte.ui.recyler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * User: bkzhou
 * Date: 2018/1/11
 * Description:
 */
public class VerticalListDataConverter extends DataConveter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
            .getJSONObject("data")
            .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int Id = data.getInteger("goods_id");
            final String goodsName = data.getString("goods_name");
            final String goodsThumb = data.getString("goods_thumb");
            //获取内容
           final MultipleItemEntity entity = MultipleItemEntity.builder()
               .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
               .setField(MultipleFields.ID,Id)
               .setField(MultipleFields.TEXT,goodsName)
               //标记选中
               .setField(MultipleFields.TAG,false)
               .build();
            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG,true);
        }
        return dataList;
    }
}
