package com.example.latte.ui.recyler;

import java.util.ArrayList;

/**
 * User: bkzhou
 * Date: 2018/1/5
 * Description:
 */
public abstract class DataConveter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConveter setJsonData(String jsonData){
        this.mJsonData = jsonData;
        return this;
    }
    public String getJsonData(){
        if(mJsonData ==null ||mJsonData.isEmpty()){
            throw  new NullPointerException("DATA IS NULL");
        }
        return mJsonData;

    }


}
