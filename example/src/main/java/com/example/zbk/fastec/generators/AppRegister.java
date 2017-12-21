package com.example.zbk.fastec.generators;

import com.example.annotations.AppRegisterGenerator;
import com.example.latte.wechat.template.AppRegisterTemplate;

/**
 * User: bkzhou
 * Date: 2017/12/21
 * Description:
 */
@AppRegisterGenerator(
    packageName = "com.example.zbk.fastec",
    registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
