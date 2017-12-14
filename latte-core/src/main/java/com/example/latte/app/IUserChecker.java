package com.example.latte.app;

/**
 * User: bkzhou
 * Date: 2017/12/13
 * Description:是否登录回调
 */
public interface IUserChecker {
    void onSignIn();
    void onNotSingIn();

}
