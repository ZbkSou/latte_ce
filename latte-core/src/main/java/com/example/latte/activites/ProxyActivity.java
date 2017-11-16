package com.example.latte.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.latte.R;
import com.example.latte.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * User: bkzhou
 * Date: 2017/11/8
 * Description:基础 activity 继承 fragmentation 的 supportActivity
 */
public abstract class ProxyActivity extends SupportActivity {

    /**
     * 设置根 delegare
     * @return
     */
    public abstract LatteDelegate setRootDelegare();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegare());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //资源回收
        System.gc();
        System.runFinalization();
    }
}
