package com.example.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by ZBK on 2017-11-06.
 */

public enum  EcIcons implements Icon {

  icon_fenxiang('\ue624'),
  icon_fanhui('\ue625'),
  icon_guanbi('\ue626'),
  icon_huanyihuan('\ue623');



  private char character;

  EcIcons(char character){
    this.character = character;
  }

  @Override
  public String key() {
    return name().replace('_', '-');
  }

  @Override
  public char character() {
    return character;
  }
}
