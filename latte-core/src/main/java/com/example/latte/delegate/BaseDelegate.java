package com.example.latte.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte.activites.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * User: bkzhou
 * Date: 2017/11/8
 * Description: 继承SwipeBackFragment 支持滑动后退
 */
public abstract class BaseDelegate extends SwipeBackFragment{

    public abstract  Object setLayout();

    private Unbinder mUnbinder = null;

    /**
     * 设置 view
     * @param savedInstanceState
     * @param rootView
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View rootView ;
        if(setLayout() instanceof  Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if(setLayout() instanceof View){
            rootView = (View)setLayout();
        }else {
            throw  new ClassCastException("setLayout() type must be int or view");
        }
        mUnbinder  = ButterKnife.bind(this,rootView);
        onBindView(savedInstanceState,rootView);
        return rootView;
    }
    public final ProxyActivity getProxyActicity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        if(mUnbinder !=null){
            mUnbinder.unbind();
        }
    }
}
