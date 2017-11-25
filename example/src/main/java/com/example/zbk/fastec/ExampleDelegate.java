package com.example.zbk.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.latte.app.ConfigType;
import com.example.latte.app.Latte;
import com.example.latte.delegate.LatteDelegate;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.ISuccess;

/**
 * User: bkzhou
 * Date: 2017/11/15
 * Description:
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Toast.makeText(getContext(), (String) Latte.getConfigurations().get(ConfigType.API_HOST.name()), Toast.LENGTH_LONG).show();
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
          .url("https://api.douban.com/v2/book/1220562")
//            .params("", "")

          .loader(getContext())
          .success(new ISuccess() {
              @Override
              public void onSuccess(String response) {
                  Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
              }
          })
          .failure(new IFailure() {
              @Override
              public void onFailure() {

              }
          })
          .error(new IError() {
              @Override
              public void onError(int code, String msg) {

              }
          })
          .build()
          .get();

        RestClient.builder()
          .url("/api/4/version/android/2.3.0")
//            .params("", "")
          .success(new ISuccess() {
              @Override
              public void onSuccess(String response) {
                  Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
              }
          })
          .failure(new IFailure() {
              @Override
              public void onFailure() {

              }
          })
          .error(new IError() {
              @Override
              public void onError(int code, String msg) {

              }
          })
          .build()
          .get();
    }

}
