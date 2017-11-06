package com.example.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by ZBK on 2017-11-06.
 */

public class FontEcModule implements IconFontDescriptor{
  @Override
  public String ttfFileName() {
    return "iconfont.ttf";
  }

  @Override
  public Icon[] characters() {
    return EcIcons.values();
  }
}
