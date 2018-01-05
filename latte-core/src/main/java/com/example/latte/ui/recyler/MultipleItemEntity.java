package com.example.latte.ui.recyler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * User: bkzhou
 * Date: 2018/1/5
 * Description:
 */
public class MultipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
        new SoftReference<LinkedHashMap<Object, Object>>(MULIPLE_FIELDS, ITEM_QUENE);

    public MultipleItemEntity(LinkedHashMap<Object,Object>fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }


    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }


    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }
    public final <T> T getField(Object key){
        return (T)FIELDS_REFERENCE.get().get(key);
    }
    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }
    public final MultiItemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }
}
