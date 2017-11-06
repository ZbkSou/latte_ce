package com.example.zbk.fastec;

import android.app.Application;

import com.example.latte.app.Latte;
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
      .withIcon(new FontAwesomeModule())
      .configure();
  }
}
