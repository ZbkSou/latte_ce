package com.example.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte.app.AccountManager;
import com.example.latte.app.IUserChecker;
import com.example.latte.app.Latte;
import com.example.latte.delegate.LatteDelegate;
import com.example.latte.ec.R;
import com.example.latte.ui.launcher.ILauncherListener;
import com.example.latte.ui.launcher.LauncherHolderCreator;
import com.example.latte.ui.launcher.OnLauncherFinishTag;
import com.example.latte.ui.launcher.ScrollLauncherTag;
import com.example.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * User: bkzhou
 * Date: 2017/12/6
 * Description:
 */
public class LauncherScrollerDelegate extends LatteDelegate implements OnItemClickListener{
    private ConvenientBanner<Integer> convenientBanner =null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        convenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
        .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//指示点位置
            .setOnItemClickListener(this)
//            .startTurning(5000);
            .setCanLoop( false);//循环


    }
    @Override
    public Object setLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return convenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if(position ==INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSingIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }

    }
}
