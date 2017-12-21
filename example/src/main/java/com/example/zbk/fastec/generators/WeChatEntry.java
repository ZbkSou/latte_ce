package com.example.zbk.fastec.generators;

import com.example.annotations.EntryGenerator;
import com.example.latte.wechat.template.WXEntryTemplate;

/**
 * User: bkzhou
 * Date: 2017/12/21
 * Description:
 */
@EntryGenerator(
    packageName = "com.example.zbk.fastec",
    entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
