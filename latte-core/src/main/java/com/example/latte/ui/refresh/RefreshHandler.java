package com.example.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.latte.app.Latte;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.util.logger.LatteLogger;

/**
 * User: bkzhou
 * Date: 2018/1/4
 * Description:刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "RefreshHandler";
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    public RefreshHandler(SwipeRefreshLayout refresh_layout){
        this.REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }
    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }
    @Override
    public void onRefresh() {
        refresh();
    }
    public void firstPage(String  url){
        RestClient.builder()
            .url(url)
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
//                    LatteLogger.json(TAG,response);
                }
            })
            .build()
            .get();
    }
}
