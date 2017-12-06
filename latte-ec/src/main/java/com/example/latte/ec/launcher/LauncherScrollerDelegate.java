package com.example.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.latte.delegate.LatteDelegate;
import com.example.latte.ec.R;

import java.util.ArrayList;

/**
 * User: bkzhou
 * Date: 2017/12/6
 * Description:
 */
public class LauncherScrollerDelegate extends LatteDelegate{
    private ConvenientBanner<Integer> convenientBanner =null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        convenientBanner.setPages();

    }
    @Override
    public Object setLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
