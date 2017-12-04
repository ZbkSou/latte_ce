package com.example.latte.util.timer;


import java.util.TimerTask;

/**
 * User: bkzhou
 * Date: 2017/12/1
 * Description:
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }
    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }

    }
}
