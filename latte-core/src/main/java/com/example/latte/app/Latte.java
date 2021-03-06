package com.example.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by ZBK on 2017-10-30.
 * Description:对外的设置类
 * //配置core用法
 * Latte.init(this)
 * .withApiHost("https://api.douban.com")
 * .withIcon(new FontAwesomeModule())
 * .withIcon(new FontEcModule())
 * .configure();
 */

public final class Latte {

    /**
     * Latte.init(this)
     *
     * 初始化主要保存ApplicationContext
     * @param context
     * @return
     */
    public static Configurator init(Context context) {
        //配置context
        Configurator.getInstance()
            .getLatteConfigs().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }
    /**
     *
     * @return
     */
    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    /**
     * 获取配置
     * @return
     */
    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }
    /**
     * 获取配置
     * @return
     */
    public static   <T> T  getConfiguration(Object e) {
        return getConfigurator().getConfiguration(e);
    }

    /**
     * 直接获取ApplicationContext其他配置需要通过getConfigurations
     * @return
     */
    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT);
    }
    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER);
    }

}
