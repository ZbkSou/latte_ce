package com.example.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.latte.app.Latte;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.ui.recyler.DataConveter;
import com.example.latte.ui.recyler.MultipleRecylerAdapter;
import com.example.latte.util.logger.LatteLogger;

/**
 * User: bkzhou
 * Date: 2018/1/4
 * Description:刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
    BaseQuickAdapter.RequestLoadMoreListener{
    private static final String TAG = "RefreshHandler";
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecylerAdapter mAdapter = null;
    private  final DataConveter CONVERTER;
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    public RefreshHandler(SwipeRefreshLayout refresh_layout,RecyclerView recyclerView,
                          DataConveter conveter,PagingBean bean){
        this.REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = conveter;
        this.BEAN = bean;
    }
    public static  RefreshHandler create(SwipeRefreshLayout refresh_layout,RecyclerView recyclerView,
                                         DataConveter conveter){
        return new RefreshHandler(refresh_layout,recyclerView,conveter,new PagingBean());
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
        BEAN.setDelayed(1000);
        RestClient.builder()
            .url(url)
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                    final JSONObject object = JSON.parseObject(response);
                    BEAN.setTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));
                    mAdapter = MultipleRecylerAdapter.create(CONVERTER.setJsonData(response));
                    mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                    RECYCLERVIEW.setAdapter(mAdapter);
                    BEAN.addIndex();
                }
            })
            .build()
            .get();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
