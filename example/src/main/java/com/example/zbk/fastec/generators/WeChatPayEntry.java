package com.example.zbk.fastec.generators;

import com.example.annotations.PayEntryGenerator;
import com.example.latte.wechat.template.WXPayEntryTemplate;

/**
 * User: bkzhou
 * Date: 2017/12/21
 * Description:
 */
@PayEntryGenerator(
    packageName = "com.example.zbk.fastec",
    payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
