package com.example.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.latte.app.Latte;
import com.example.latte.ec.R;
import com.example.latte.ui.recyler.RgbValue;
import com.example.latte.util.logger.LatteLogger;

/**
 * User: bkzhou
 * Date: 2018/1/9
 * Description:
 */
public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar>{
    // 顶部距离
    private int  mDistanceY = 0;
    //颜色变化
    private static final int SHOW_SPEED =3;
    //定义变化颜色
    private final RgbValue RGB_VALUE= RgbValue.create(255,124,2);

    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId()== R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return  true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //增加滑动距离
        mDistanceY+= dy;
        //toolbar 的高度
        final int targetHeight = child.getBottom();
        //当滑动时,距离小于 toolbar 高度的时候调整渐变色
        LatteLogger.d(mDistanceY+"=="+targetHeight);
        if(mDistanceY>0&& mDistanceY<= targetHeight){
            final float scale =(float) mDistanceY/targetHeight;
            final float alpha = scale*255;
            child.setBackgroundColor(Color.argb((int)alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }else  if (mDistanceY>targetHeight){
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }
    }
}
