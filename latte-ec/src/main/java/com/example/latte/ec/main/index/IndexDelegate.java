package com.example.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte.delegate.bottom.BottomItemDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.ui.recyler.MultipleFields;
import com.example.latte.ui.recyler.MultipleItemEntity;
import com.example.latte.ui.refresh.RefreshHandler;
import com.example.latte.util.logger.LatteLogger;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: bkzhou
 * Date: 2018/1/3
 * Description:
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView rvIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout srlIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconIndexScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText etSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView iconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar tbIndex;

    private RefreshHandler mRefreshHandler = null;
    /**
     * 初始化下拉
     */
    private void  initRefreshLayout(){
        srlIndex.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
        srlIndex.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
//        mRefreshHandler.firstPage("index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(srlIndex);
        RestClient.builder()
            .url("index")
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                   final IndexDataConverter converter = new IndexDataConverter();
                    converter.setJsonData(response);
                    final ArrayList<MultipleItemEntity> list =  converter.convert();
                    final String image_url = list.get(1).getField(MultipleFields.IMAGE_URL);
                    LatteLogger.d(image_url);
                }
            })
            .build()
            .get();
    }


}
