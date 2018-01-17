package com.example.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.latte.delegate.LatteDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.ec.main.sort.SortDelegate;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.ui.recyler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * User: bkzhou
 * Date: 2018/1/10
 * Description:
 */
public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_list)
    RecyclerView mRecyclerView =null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
    private void initRecyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
            .url("sort_list")
            .loader(getContext())
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                    final List<MultipleItemEntity> data =
                        new VerticalListDataConverter().setJsonData(response).convert();
                    final SortDelegate delegate  =getParentDelegate();
                    final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                    mRecyclerView.setAdapter(adapter);
                }
            })
            .build()
            .get();
    }
}
