package com.example.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by ZBK on 2017-10-30.
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

  private static WeakHashMap<String, Object> getConfigurator() {
    return Configurator.getInstance().getLatteConfigs();
  }


}
