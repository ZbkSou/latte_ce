package com.example.zbk.fastec;

import android.app.Application;

import com.example.latte.app.Latte;
import com.example.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by ZBK on 2017-10-31.
 */

public class ExampleApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    //配置core
    Latte.init(this)
      .withApiHost("http:")
//      加入字体图标
      .withIcon(new FontAwesomeModule())
      .withIcon(new FontEcModule())
      .configure();
  }
}
