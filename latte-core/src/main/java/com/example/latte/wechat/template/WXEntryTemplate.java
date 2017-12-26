package com.example.latte.wechat.template;

import com.example.latte.activites.ProxyActivity;
import com.example.latte.delegate.LatteDelegate;
import com.example.latte.wechat.BaseWXEntryActivity;

/**
 * User: bkzhou
 * Date: 2017/12/21
 * Description:微信登录分享结果处理模板
 */
public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        //分享完成后
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {

    }
}
