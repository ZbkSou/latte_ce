package com.example.latte.delegate.bottom;

/**
 * User: bkzhou
 * Date: 2018/1/2
 * Description: 底部按钮 小图标 文字 bean
 */
public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }
    public CharSequence getICON(){
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
