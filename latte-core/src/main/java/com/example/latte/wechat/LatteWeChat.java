package com.example.latte.wechat;

import android.app.Activity;

import com.example.latte.app.ConfigType;
import com.example.latte.app.Latte;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * User: bkzhou
 * Date: 2017/12/22
 * Description:
 */
public class LatteWeChat {
    static final String APP_ID =  Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;

    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }
    private LatteWeChat(){
        final Activity activity = Latte.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }
    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * h获取IWXAPI
     * @return
     */
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }
    /**
     * 登录微信
     */
    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
