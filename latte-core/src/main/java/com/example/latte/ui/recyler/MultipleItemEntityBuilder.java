package com.example.latte.ui.recyler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * User: bkzhou
 * Date: 2018/1/5
 * Description:MultipleItemEntity建造者
 */
public class MultipleItemEntityBuilder {
    private static  final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();
    public MultipleItemEntityBuilder(){
        //先清空数据
        FIELDS.clear();
    }
    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }
    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key, value);
        return this;
    }
    public final MultipleItemEntityBuilder setFields( WeakHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }

    public  final MultipleItemEntity build(){
      return new MultipleItemEntity(FIELDS);
    }
}
