package com.example.latte.ui.recyler;

import com.google.auto.value.AutoValue;

/**
 * User: bkzhou
 * Date: 2018/1/10
 * Description:
 */
@AutoValue
public  abstract class RgbValue {
    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RgbValue create(int red,int green,int blud){
        return new AutoValue_RgbValue(red,green,blud);
    }
}
