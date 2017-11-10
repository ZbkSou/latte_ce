package com.example.latte.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by ZBK on 2017-10-30.
 * Description:对外的设置类
 */

public final class Latte {

  /**
   *  Latte.init(this)
           .withApiHost("http:")
           .configure();
   * 可以这样继续配置
   * @param context
   * @return
   */
  public static Configurator init(Context context) {
    //配置context
    getConfigurator().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
    return Configurator.getInstance();
  }

  private static HashMap<String, Object> getConfigurator() {
    return Configurator.getInstance().getLatteConfigs();
  }
  public static  Context getApplication(){
    return (Context) getConfigurator().get(ConfigType.APPLICATION_CONTEXT.name());
  }


}
