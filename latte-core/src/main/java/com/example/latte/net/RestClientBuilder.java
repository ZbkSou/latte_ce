package com.example.latte.net;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ZBK on 2017-11-19.
 * RestClient的建造者
 * RestClient.builder()
 * .url("")
 * .params("","")
 * .success(new ISuccess() {
 *
 * @Override public void onSuccess(String response) {
 * <p>
 * }
 * })
 * .failure(new IFailure() {
 * @Override public void onFailure() {
 * <p>
 * }
 * })
 * .error(new IError() {
 * @Override public void onError(int code, String msg) {
 * <p>
 * }
 * })
 * .build()
 * .get/post/put/detele
 */

public class RestClientBuilder {
    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> map) {
        PARAMS.putAll(map);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        this.PARAMS.put(key, value);
        return this;
    }

    /**
     * 穿入原始数据
     *
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody);
    }
}
