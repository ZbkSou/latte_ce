package com.example.latte.net.callback;

/**
 * Created by ZBK on 2017-11-19.
 * 请求开始 结束回调
 */

public interface IRequest {
    /**
     * 网络请求开始前
     */
    void onRequestSrart();

    /**
     * 网络请求结束
     */
    void onRequestEnd();
}
