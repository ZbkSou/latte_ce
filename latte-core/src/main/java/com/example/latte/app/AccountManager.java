package com.example.latte.app;

import com.example.latte.util.storage.LattePreference;

/**
 * User: bkzhou
 * Date: 2017/12/13
 * Description:登录管理
 */
public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户登录状态,登录后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    //判断是否的登录
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSingIn();
        }

    }
}
